package com.chtj.util.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/8/23.
 * MD5加密
 */

public class MD5Utils {
    public static String ecodeTwice(String str) {//MD5两次
        return ecode(ecode(str));
    }

    public static String ecode(String passwd) {

        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] digest = instance.digest(passwd.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                int i = b & 0xff;
                String hexString = Integer.toHexString(i);
                if (hexString.length() < 2) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

}
