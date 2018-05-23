package com.quick.core.util.User;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/13.
 */
public class UserUtil {
    public static final String DEFAULTPASS = "123456";
    /**
     * 格式化日期,年月日
     *
     * @return
     */
    public static Date formatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Date formatDateByDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Timestamp转化为String
     *
     * @return
     */
    public static String getStringByObject(Object o)
    {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (o == null || o.equals("")) {
            return "";
        }
        String date = null;
        date = sDateFormat.format(o);
        return date;
    }

    /**
     * Timestamp转化为String(yyyy-MM-dd)
     *
     * @return
     */
    public static String getStringByString(Object o)
    {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (o == null || o.equals("")) {
            return "";
        }
        String date = null;
        try {
            date = sDateFormat.format(sDateFormat.parse((String)o));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 生成加密后的默认密码
     *
     * @return
     */
    public static String getDefaultPass() {
        return string2MD5(DEFAULTPASS);
    }

    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }
}
