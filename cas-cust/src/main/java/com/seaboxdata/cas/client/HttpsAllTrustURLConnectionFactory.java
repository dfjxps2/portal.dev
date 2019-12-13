package com.seaboxdata.cas.client;

import org.jasig.cas.client.ssl.HttpURLConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;

/**
 * 该方法使用https方式链接，但是不校验服务器的证书。
 * 复制HttpsURLConnectionFactory类，除了buildHttpURLConnection方法被重写，其他均复制
 */
public class HttpsAllTrustURLConnectionFactory implements HttpURLConnectionFactory {


    private static final Logger LOGGER = LoggerFactory.getLogger(HttpsAllTrustURLConnectionFactory.class);
    private HostnameVerifier hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
    private Properties sslConfiguration = new Properties();


    public HttpsAllTrustURLConnectionFactory(){
    }

    public HttpsAllTrustURLConnectionFactory(HostnameVerifier verifier, Properties config){
        this.hostnameVerifier = verifier;
        this.sslConfiguration = config;
    }

    public HttpURLConnection buildHttpURLConnection(URLConnection conn) {
        if (conn instanceof HttpsURLConnection) {
            HttpsURLConnection httpsConnection = (HttpsURLConnection)conn;
            SSLSocketFactory socketFactory = this.createAllTrustSSLSocketFactory();
            if (socketFactory != null) {
                httpsConnection.setSSLSocketFactory(socketFactory);
            }
            if (this.hostnameVerifier != null) {
                httpsConnection.setHostnameVerifier(new HostnameVerifier() {
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                });
            }
        }

        return (HttpURLConnection)conn;
    }

    private SSLSocketFactory createAllTrustSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance(this.sslConfiguration.getProperty("protocol", "SSL"));

            TrustManager[] allTrustManagers = new TrustManager[]{
                    new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException { }
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException { }
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            sslContext.init(null, allTrustManagers, (SecureRandom)null);

            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            return sslSocketFactory;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            HttpsAllTrustURLConnectionFactory that = (HttpsAllTrustURLConnectionFactory)o;
            if (!this.hostnameVerifier.equals(that.hostnameVerifier)) {
                return false;
            } else {
                return this.sslConfiguration.equals(that.sslConfiguration);
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.hostnameVerifier.hashCode();
        result = 31 * result + this.sslConfiguration.hashCode();
        return result;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        if (this.hostnameVerifier == HttpsURLConnection.getDefaultHostnameVerifier()) {
            out.writeObject((Object)null);
        } else {
            out.writeObject(this.hostnameVerifier);
        }

        out.writeObject(this.sslConfiguration);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        Object internalHostNameVerifier = in.readObject();
        if (internalHostNameVerifier == null) {
            this.hostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
        } else {
            this.hostnameVerifier = (HostnameVerifier)internalHostNameVerifier;
        }

        this.sslConfiguration = (Properties)in.readObject();
    }

    public HostnameVerifier getHostnameVerifier() {
        return hostnameVerifier;
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
    }

    public Properties getSslConfiguration() {
        return sslConfiguration;
    }

    public void setSslConfiguration(Properties sslConfiguration) {
        this.sslConfiguration = sslConfiguration;
    }
}
