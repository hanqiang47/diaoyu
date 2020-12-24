package com.github.jingou.util;

//import org.apache.shiro.crypto.hash.Md5Hash;
//import org.apache.shiro.crypto.hash.SimpleHash;
//import org.apache.shiro.util.ByteSource;


import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Md5Util {

//    shiro方式

//    /**
//     * 加盐参数
//     */
//    public final static String HASH_ALGORIHM_NAME = "MD5";
//
//    /**
//     * 循环次数
//     */
//    public final static int HASH_ITERATIONS = 1024;
//    /**
//     * 获取随机盐值
//     * @param length
//     * @return
//     */
//    public static String getRandomSalt(int length) {
//        return getRandomString(length);
//    }
//    /**
//     *
//     * @param credentials 密码
//     * @param saltSource 密码盐
//     * @return
//     */
//    public static String md5(String credentials, String saltSource) {
//        ByteSource salt = new Md5Hash(saltSource);
//        return new SimpleHash(HASH_ALGORIHM_NAME, credentials, salt, HASH_ITERATIONS).toString();
//    }

//    java写法
    private static MessageDigest md5;//md5加密对象
    private static Base64 base64;//加密编码对象

    static{
        try {
            md5 = MessageDigest.getInstance("MD5");//获取MD5加密对象
            base64 = new Base64();//获取加密编码对象
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /*
     * 对password进行MD5加盐加密，返回加密过的password
     */
    public static String encrypt(String password,String salt){
        String encryptPassword = "";
        try {
            md5.reset();//初始化
            String passwordSalt = password + salt;
            md5.update(passwordSalt.getBytes("UTF-8"));//将加盐密码传给消息摘要对象
            byte[] bys=md5.digest();//创建消息摘要对象
            byte[] lastPassword=base64.encode(bys);//进行加密
            encryptPassword = new String(lastPassword);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptPassword;
    }

    /**
     * 获取随机位数的字符串
     *
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789+-=*;',.";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());  //从str中随机获取字符生成长度为saltLen的加密盐
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
