package com.baizhi.test.Encoder;

import com.baizhi.test.Encoder.utils.RSACoderUtil;

public class YLZEncoder {
    private static String charset = "utf-8";

    public YLZEncoder() {
    }

    public static String encrypt(String data, String charset, String publicKey) throws Exception {
        String encrypt = RSACoderUtil.encrypt(data, charset, publicKey);
        return encrypt;
    }

    public static String encrypt(String data, String publicKey) throws Exception {
        String encrypt = RSACoderUtil.encrypt(data, charset, publicKey);
        return encrypt;
    }

    public static String decrypt(String data, String charset, String privateKey) throws Exception {
        String encrypt = RSACoderUtil.decrypt(data, privateKey, charset);
        return encrypt;
    }

    public static String decrypt(String data, String privateKey) throws Exception {
        String encrypt = RSACoderUtil.decrypt(data, privateKey, charset);
        return encrypt;
    }

    public static String sign(String data, String charset, String privateKey) throws Exception {
        String encrypt = RSACoderUtil.sign(data, charset, privateKey);
        return encrypt;
    }

    public static String sign(String data, String privateKey) throws Exception {
        String encrypt = RSACoderUtil.sign(data, charset, privateKey);
        return encrypt;
    }

    public static boolean verifySign(String sign, String data, String charset, String publicKey) throws Exception {
        return RSACoderUtil.verifySign(sign, data, publicKey, charset);
    }

    public static boolean verifySign(String sign, String data, String publicKey) throws Exception {
        return RSACoderUtil.verifySign(sign, data, publicKey, charset);
    }
}

