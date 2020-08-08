package com.joy.programmer.joyce.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;

import static java.util.regex.Pattern.compile;

public class ValidateUtil {

    /**
     * 校验密码
     *
     * @param password
     * @param encodePassword
     * @param isMd5
     * @return
     */
    public static boolean validatePassword(String password, String encodePassword, boolean isMd5) {
        if (!getInvalue(password)) {
            return false;
        }
        String calPassWord = isMd5 ? DigestUtils.md5DigestAsHex(password.getBytes()) : encodePwd(password);
        return StringUtils.equals(calPassWord, encodePassword);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String aaString = "wz123";
        //密码复杂度校验判读是否有数字和字母组成
        if (getInvalue(aaString)) {
            System.out.println("密码有数字字母做成");
        } else {
            System.out.println("密码中不包括数字和字母");

        }
        //普通加密
        System.out.println("普通加密前的密码为：" + aaString);
        String ecoString = encodePwd(aaString);
        System.out.println("普通加密后的密码为：" + ecoString);
        System.out.println("普通再次加密：" + encodePwd(ecoString));
        //MD5加密
        System.out.println("MD5加密后的值为--2--" + DigestUtils.md5Digest(aaString.getBytes()));
    }

    //密码加密函数，这个方法具有逆向性，及加密算法和解密算法相同的
    public static String encodePwd(String pwd) {
        String encodeString = "^&*^&687GIKUGUGBjhkjbhjk";
        String reString = "";
        if (pwd == null) {
            pwd = "";
        }
        for (int i = 0; i < pwd.length(); i++) {
            reString = reString + (char)(pwd.charAt(i) ^ encodeString.charAt(i));

        }
        return reString;

    }


    //密码复杂度校验
    /*    */
    public static boolean getInvalue(String pw) {
        for (int i = 0; i < pw.length(); i++) {
            if (Character.isDigit(pw.charAt(i)) && compile("(?i)[A-Za-z]").matcher(pw).find()) {
                return true;
            }
        }
        return false;
    }

}