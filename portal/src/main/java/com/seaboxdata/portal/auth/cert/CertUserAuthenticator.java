package com.seaboxdata.portal.auth.cert;

import cn.org.bjca.security.SecurityEngineDeal;
import com.bjca.sso.services.beans.LoginTicket;
import com.bjca.sso.services.beans.LoginTicketManager;
import com.quick.portal.security.authority.metric.PropertiesUtil;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.exception.CredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class CertUserAuthenticator implements Authenticator<CertUserCredentials> {
    private final static Logger logger = LoggerFactory.getLogger(CertUserAuthenticator.class);

    @Override
    public void validate(CertUserCredentials credentials, WebContext context) {
        String uniqueIdStr = "";
        String uniqueId = "";

        if (credentials == null || credentials.getClientCert() == null
                || credentials.getContainerName() == null
                || credentials.getUserSignedData() == null) {
            logger.error("Credentials are required");
            return;
        }

        try {
            SecurityEngineDeal sed = SecurityEngineDeal.getInstance("SVSDefault");
            SecurityEngineDeal sm2 = SecurityEngineDeal.getInstance("SM2");

            String certPub = sed.getCertInfo(credentials.getClientCert(), 8);

            //验证服务器证书有效期
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
                    "yyyy/MM/dd");
            String curDateStr = dateFormat.format(new Date());
            Date curDate = dateFormat.parse(curDateStr);
            String servercert = sed.getServerCertificate();
            String servercertdate = sed.getCertInfo(servercert, 12);

            Date scertdate = dateFormat.parse(servercertdate);

            String type = sed.getCertInfo(credentials.getClientCert(), 31);

            logger.debug("Cert Type is {}", type);

            int retValue = 0;
            if (type.equals("RSA"))
                //验证客户端证书
                retValue = sed.validateCert(credentials.getClientCert());
            else
                retValue = sm2.validateCert(credentials.getClientCert());

            if (retValue == 1) {
                try {
                    if (type.equals("RSA"))
                        uniqueIdStr = sed.getCertInfo(credentials.getClientCert(), 17);
                    else
                        uniqueIdStr = sm2.getCertInfo(credentials.getClientCert(), 17);
                } catch (Exception e) {
                    throw new CredentialsException("Extract unique id str failed:", e);
                }

                try {
                    //获得登陆用户ID
                    if (type.equals("RSA"))
                        uniqueId = sed.getCertInfoByOid(credentials.getClientCert(), "2.16.840.1.113732.2");
                    else
                        uniqueId = sm2.getCertInfoByOid(credentials.getClientCert(), "1.2.156.112562.2.1.1.1");
                } catch (Exception e) {
                    throw new CredentialsException("Extract unique id failed:", e);
                }

                logger.info("主题通用名(uniqueIdStr)={}", uniqueIdStr);
                logger.info("证书颁发者(颁发者通用名)={}", certPub);
                logger.info("证书唯一标识(备用主题通用名)(uniqueId)={}", uniqueId);
            } else {
                String retMsg = "";
                switch (retValue) {
                    case -1:
                        retMsg = "登录证书的根不被信任";
                        break;
                    case -2:
                        retMsg = "登录证书超过有效期";
                        break;
                    case -3:
                        retMsg = "登录证书为作废证书";
                        break;
                    case -4:
                        retMsg = "登录证书被临时冻结";
                        break;
                }

                logger.error("客户端证书验证失败！" + retMsg);
                return;
            }
            //验证客户端签名
            boolean ret = false;
            if (type.equals("RSA"))
                ret = sed.verifySignedData(credentials.getClientCert(), credentials.getRandom(),
                        credentials.getUserSignedData());
            else
                ret = sm2.verifySignedData(credentials.getClientCert(), credentials.getRandom(),
                        credentials.getUserSignedData());

            if (ret) {
                logger.info("验证客户端签名成功");

                if (PropertiesUtil.getPropery("ca.skip.verification").equals("true")){
                    credentials.setUserProfile(new CertUserProfile(uniqueIdStr, uniqueId, "00000000"));
                    return;
                }

                if (!(context instanceof J2EContext)) {
                    logger.error("Require J2EContext instance to get http request.");
                    return;
                }

                J2EContext j2EContext = (J2EContext)context;
                String infoCode = PropertiesUtil.getPropery("ca.http.login.infoCode");
                String httpLoginURL = PropertiesUtil.getPropery("ca.http.login.url");

                LoginTicketManager ltm = new LoginTicketManager();
                LoginTicket lt = ltm.getLoginTicket(j2EContext.getRequest(), uniqueId, "qwewr232sd==", infoCode, httpLoginURL);

                String uniqueIdCode = lt.getUserUniqueID();

                credentials.setUserProfile(new CertUserProfile(uniqueIdStr, uniqueId, uniqueIdCode));

            } else {
                logger.error("验证客户端签名错误！");
            }

        } catch (Exception e) {
            logger.error("Cert validation failed, {}", e);
        }
    }
}
