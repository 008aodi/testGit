package com.baizhi.test;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import org.junit.Test;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSA {
    public static final String RSA_ALGORITHM = "RSA";
    public static String SIGNATURE_ALGORITHM = "SHA1withRSA";
    public static String RSA_PUBLIC_KEY = "RSAPublicKey";
    public static String RSA_PRIVATE_KEY = "RSAPrivateKey";

    /**
     *
     * @param binaryData
     * @return
     */
    public static String encodeBase64(byte[] binaryData) {
        return Base64.encode(binaryData);
    }

    public static byte[] decodeBase64(String encoded) throws Base64DecodingException {
        return Base64.decode(encoded);
    }
    /**
     * 获取RSA算法私钥、公钥
     */
    public static Map<String, Object> getKey() throws NoSuchAlgorithmException {
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        keyPairGen.initialize(1024);//1024代表密钥二进制位数
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        keyMap.put(RSA_PUBLIC_KEY, publicKey);
        keyMap.put(RSA_PRIVATE_KEY, privateKey);
        return keyMap;
    }
    /**
     * 获取公钥
     */
    public static String getPublicKey(Map<String, Object> map) {
        Key key = (Key) map.get(RSA_PUBLIC_KEY);
        return encodeBase64(key.getEncoded());
    }

    /**
     * 获取私钥
     */
    public static String getPrivateKey(Map<String, Object> map) {
        Key key = (Key) map.get(RSA_PRIVATE_KEY);
        return encodeBase64(key.getEncoded());
    }

    /**
     * 使用私钥对数据进行加密
     */
    public static byte[] encryptPrivateKey(byte[] binaryData, String privateKey) throws Exception {
        byte[] keyBytes = decodeBase64(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        Key priKey = keyFactory.generatePrivate(keySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, priKey);
        return cipher.doFinal(binaryData);
    }
    /**
     * 使用公钥对数据进行加密
     */
    public static byte[] encryptPublicKey(byte[] binaryData, String publicKey) throws Exception {
        byte[] keyBytes = decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        Key pubKey = keyFactory.generatePublic(keySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(binaryData);
    }
    /**
     * 使用私钥对数据进行解密
     */
    public static byte[] decryptPrivateKey(byte[] binaryData, String privateKey) throws Exception {
        byte[] keyBytes = decodeBase64(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        Key priKey = keyFactory.generatePrivate(keySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return cipher.doFinal(binaryData);
    }
    /**
     * 使用公钥对数据进行解密
     */
    public static byte[] decryptPublicKey(byte[] binaryData, String publicKey) throws Exception {
        byte[] keyBytes = decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        Key pubKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return cipher.doFinal(binaryData);
    }
    /**
     * 使用私钥对数据进行签名
     */
    public static String sign(byte[] binaryData, String privateKey) throws Exception {
        byte[] keyBytes = decodeBase64(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PrivateKey priKey = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(binaryData);
        return encodeBase64(signature.sign());
    }
    /**
     * 使用公钥对数据签名进行验证
     */
    public static boolean verify(byte[] binaryData, String publicKey, String sign) throws Exception {
        byte[] keyBytes = decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(binaryData);
        return signature.verify(decodeBase64(sign));
    }
@Test
    public void testRSA() throws Exception {
            String data = "zhangpeng";
    Map<String, Object> key = RSA.getKey();
    String publicKey = RSA.getPublicKey(key);
    String privateKey = RSA.getPrivateKey(key);
    //公钥加密数据
    byte[] bytes = RSA.decryptPublicKey(RSA.decodeBase64(data), publicKey);
    String s = RSA.encodeBase64(bytes);
    System.out.println(s+"加密后的数据");
    //私钥解密
    byte[] bytes1 = RSA.decryptPrivateKey(RSA.decodeBase64(s),privateKey);
    String s1 = RSA.encodeBase64(bytes1);
    System.out.println(s1+"解密后的数据");
}
}

