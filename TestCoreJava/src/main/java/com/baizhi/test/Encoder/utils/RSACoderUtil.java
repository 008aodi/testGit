package com.baizhi.test.Encoder.utils;

import com.baizhi.test.Encoder.YlzEncoderException;
import com.baizhi.test.Encoder.utils.json.ExceptionErrorListener;
import com.baizhi.test.Encoder.utils.json.JSONValidatingReader;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Cipher;

public class RSACoderUtil extends CoderUtil {
    public static final String KEY_ALGORTHM = "RSA";
    public static final String SPECIFIC_KEY_ALGORITHM = "RSA/ECB/PKCS1Padding";
    public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";

    public RSACoderUtil() {
    }

    public static String encrypt(String paramsString, String charset, String publicKey) throws Exception {
        byte[] encryptedResult = encryptByPublicKey(paramsString.getBytes(charset), publicKey, (EncryptionModeEnum)null);
        return Base64Util.byteArrayToBase64(encryptedResult);
    }

    public static String encrypt(String paramsString, String charset, String publicKey, EncryptionModeEnum encryptionType) throws Exception {
        byte[] encryptedResult = encryptByPublicKey(paramsString.getBytes(charset), publicKey, encryptionType);
        return Base64Util.byteArrayToBase64(encryptedResult);
    }

    public static String sign(String data, String charset, String privateKey) throws Exception {
        byte[] dataInBytes = data.getBytes(charset);
        String signParams = sign(dataInBytes, privateKey);
        return signParams;
    }

    public static String sign(SignTypeEnum signType, String data, String charset, String privateKey) throws Exception {
        byte[] dataInBytes = data.getBytes(charset);
        String signParams = sign(signType, dataInBytes, privateKey);
        return signParams;
    }

    public static String decrypt(String data, String key, String charset) throws Exception {
        byte[] byte64 = Base64Util.base64ToByteArray(data);
        byte[] encryptedBytes = decryptByPrivateKey(byte64, key, (EncryptionModeEnum)null);
        return new String(encryptedBytes, charset);
    }

    public static String decrypt(String data, String key, String charset, EncryptionModeEnum encryptionType) throws Exception {
        byte[] byte64 = Base64Util.base64ToByteArray(data);
        byte[] encryptedBytes = decryptByPrivateKey(byte64, key, encryptionType);
        return new String(encryptedBytes, charset);
    }

    public static byte[] decryptByPrivateKey(byte[] data, String key, EncryptionModeEnum encryptionType) throws Exception {
        byte[] decryptedData = null;
        byte[] keyBytes = decryptBASE64(key);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, privateKey);
        int maxDecryptBlockSize;
        if (encryptionType != null) {
            maxDecryptBlockSize = getMaxDecryptBlockSizeByEncryptionType(encryptionType);
        } else {
            maxDecryptBlockSize = getMaxDecryptBlockSize(keyFactory, privateKey);
        }

        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        //byte[] decryptedData;
        try {
            int dataLength = data.length;

            for(int i = 0; i < dataLength; i += maxDecryptBlockSize) {
                int decryptLength = dataLength - i < maxDecryptBlockSize ? dataLength - i : maxDecryptBlockSize;
                byte[] doFinal = cipher.doFinal(data, i, decryptLength);
                bout.write(doFinal);
            }

            decryptedData = bout.toByteArray();
        } finally {
            if (bout != null) {
                bout.close();
            }

        }

        return decryptedData;
    }

    public static byte[] encryptByPublicKey(byte[] data, String key, EncryptionModeEnum encryptionType) throws Exception {
        byte[] encryptedData = null;
        byte[] keyBytes = decryptBASE64(key);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, publicKey);
        int maxEncryptBlockSize;
        if (encryptionType != null) {
            maxEncryptBlockSize = getMaxEncryptBlockSizeByEncryptionType(encryptionType);
        } else {
            maxEncryptBlockSize = getMaxEncryptBlockSize(keyFactory, publicKey);
        }

        ByteArrayOutputStream bout = new ByteArrayOutputStream();

       // byte[] encryptedData;
        try {
            int dataLength = data.length;

            for(int i = 0; i < data.length; i += maxEncryptBlockSize) {
                int encryptLength = dataLength - i < maxEncryptBlockSize ? dataLength - i : maxEncryptBlockSize;
                byte[] doFinal = cipher.doFinal(data, i, encryptLength);
                bout.write(doFinal);
            }

            encryptedData = bout.toByteArray();
        } finally {
            if (bout != null) {
                bout.close();
            }

        }

        return encryptedData;
    }

    public static String sign(byte[] data, String privateKey) throws Exception {
        return sign(SignTypeEnum.SHA1WITHRSA, data, privateKey);
    }

    public static String sign(SignTypeEnum signType, byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = decryptBASE64(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Signature signature = Signature.getInstance(signType.getDesc());
        signature.initSign(privateKey2);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }

    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        return verify(SignTypeEnum.SHA1WITHRSA, data, publicKey, sign);
    }

    public static boolean verify(SignTypeEnum signType, byte[] data, String publicKey, String sign) throws Exception {
        byte[] keyBytes = decryptBASE64(publicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);
        Signature signature = Signature.getInstance(signType.getDesc());
        signature.initVerify(publicKey2);
        signature.update(data);
        return signature.verify(decryptBASE64(sign));
    }

    public static String decryptResponse(String fullResponse, String privateKey, String charset, EncryptionModeEnum encryptionType) throws Exception {
        String decryptedRsp = null;
        Map rootJson = parseResponseMap(fullResponse);
        Iterator it = rootJson.keySet().iterator();

        while(it.hasNext()) {
            String key = (String)it.next();
            if (key.endsWith("_response")) {
                String value = (String)rootJson.get(key);
                decryptedRsp = value;
            }
        }

        if ((Boolean)rootJson.get("encrypted")) {
            decryptedRsp = decrypt(decryptedRsp, privateKey, charset, encryptionType);
        }

        return decryptedRsp;
    }

    public static boolean verifySign(String sign, String decryptedBizResponse, String publicKey, String charset) throws Exception {
        return verifySign(SignTypeEnum.SHA1WITHRSA, sign, decryptedBizResponse, publicKey, charset);
    }

    public static boolean verifySign(SignTypeEnum signType, String sign, String decryptedBizResponse, String publicKey, String charset) throws Exception {
        boolean success = false;
        if (sign != null && sign.length() > 0) {
            success = verify(signType, decryptedBizResponse.getBytes(charset), publicKey, sign);
            if (!success) {
                throw new YlzEncoderException("验签失败: " + sign.toString());
            }
        }

        return success;
    }

    public static Map parseResponseMap(String fullResponse) throws YlzEncoderException {
        JSONValidatingReader reader = new JSONValidatingReader(new ExceptionErrorListener());
        Object rootObj = reader.read(fullResponse);
        if (rootObj instanceof Map) {
            Map rootJson = (Map)rootObj;
            return rootJson;
        } else {
            throw new YlzEncoderException("返回结果格式有误:" + fullResponse);
        }
    }

    private static int getMaxEncryptBlockSize(KeyFactory keyFactory, Key key) throws Exception {
        int maxLength = 117;

        try {
            RSAPublicKeySpec publicKeySpec = (RSAPublicKeySpec)keyFactory.getKeySpec(key, RSAPublicKeySpec.class);
            int keyLength = publicKeySpec.getModulus().bitLength();
            maxLength = keyLength / 8 - 11;
        } catch (Exception var5) {
        }

        return maxLength;
    }

    private static int getMaxEncryptBlockSizeByEncryptionType(EncryptionModeEnum encryptionType) {
        if (encryptionType == EncryptionModeEnum.RSA1024) {
            return 117;
        } else {
            return encryptionType == EncryptionModeEnum.RSA2048 ? 245 : 117;
        }
    }

    private static int getMaxDecryptBlockSize(KeyFactory keyFactory, Key key) throws Exception {
        int maxLength = 128;

        try {
            RSAPrivateKeySpec publicKeySpec = (RSAPrivateKeySpec)keyFactory.getKeySpec(key, RSAPrivateKeySpec.class);
            int keyLength = publicKeySpec.getModulus().bitLength();
            maxLength = keyLength / 8;
        } catch (Exception var5) {
        }

        return maxLength;
    }

    private static int getMaxDecryptBlockSizeByEncryptionType(EncryptionModeEnum encryptionType) {
        if (encryptionType == EncryptionModeEnum.RSA1024) {
            return 128;
        } else {
            return encryptionType == EncryptionModeEnum.RSA2048 ? 256 : 128;
        }
    }
}

