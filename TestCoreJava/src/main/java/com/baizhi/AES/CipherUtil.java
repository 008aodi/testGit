package com.baizhi.AES;

import java.security.Key;
import java.security.KeyFactory;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CipherUtil {
    private static final Logger log = LoggerFactory.getLogger(CipherUtil.class);
    public static final String AES = "AES";
    public static final String DES = "DES";
    public static final String RSA = "RSA";
    private static final int RSA_LIMIT = 200;
    protected Key key;
    protected String charset;

    private CipherUtil() {
    }

    public static CipherUtil build(String type, String password, String charset) {
        if ("AES".equalsIgnoreCase(type)) {
            return CipherUtil.CipherAES.buildAES(password, charset);
        } else if ("DES".equalsIgnoreCase(type)) {
            return CipherUtil.CipherDES.buildDES(password, charset);
        } else {
            return "RSA".equalsIgnoreCase(type) ? CipherUtil.CipherRSA.buildRSA(password, charset) : null;
        }
    }

    public abstract String encryptString(String var1);

    public abstract String decryptString(String var1);

    private static class CipherRSA extends CipherUtil {
        KeyFactory keyFactory;

        private CipherRSA() {
            super();
        }

        private static CipherUtil buildRSA(String password, String charset) {
            CipherRSA cipherRSA = new CipherUtil.CipherRSA();

            try {
                byte[] bytes = Base64.decodeBase64(password.getBytes(charset));
                cipherRSA.keyFactory = KeyFactory.getInstance("RSA");
                Key key = cipherRSA.loadPublicKey(bytes);
                if (key == null) {
                    key = cipherRSA.loadPrivateKey(bytes);
                    if (key == null) {
                        return null;
                    }
                }

                cipherRSA.key = key;
                cipherRSA.charset = charset;
                return cipherRSA;
            } catch (Exception var5) {
                CipherUtil.log.info("buildRSA:" + var5);
                return null;
            }
        }

        private Key loadPublicKey(byte[] bytes) {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);

            try {
                return this.keyFactory.generatePublic(keySpec);
            } catch (InvalidKeySpecException var4) {
               CipherUtil.log.info("loadPublicKey:" + var4);
                return null;
            }
        }

        private Key loadPrivateKey(byte[] bytes) {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);

            try {
                return this.keyFactory.generatePrivate(keySpec);
            } catch (InvalidKeySpecException var4) {
                CipherUtil.log.info("loadPrivateKey:" + var4);
                return null;
            }
        }

        public String encryptString(String str) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(1, this.key);
                if (str.length() > 200) {
                    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
                    keyGen.init(256);
                    byte[] aesKey = keyGen.generateKey().getEncoded();
                    byte[] bytes = cipher.doFinal(aesKey);
                    String key = new String(Base64.encodeBase64URLSafe(bytes), this.charset);
                    CipherUtil cipherAES =CipherUtil.CipherAES.buildAES(new String(Base64.encodeBase64URLSafe(aesKey), this.charset), this.charset);
                    String data = cipherAES.encryptString(str);
                    return key + "." + data;
                } else {
                    byte[] bytes = cipher.doFinal(str.getBytes(this.charset));
                    return (new String(Base64.encodeBase64URLSafe(bytes), this.charset)).trim();
                }
            } catch (Exception var9) {
                CipherUtil.log.info("encryptString:" + var9);
                return null;
            }
        }

        public String decryptString(String str) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(2, this.key);
                int index;
                byte[] result;
                if ((index = str.indexOf(46)) != -1) {
                    result = cipher.doFinal(Base64.decodeBase64(str.substring(0, index).getBytes(this.charset)));
                    String aesKeyStr = new String(Base64.encodeBase64URLSafe(result), this.charset);
                    CipherUtil cipherAES = CipherUtil.CipherAES.buildAES(aesKeyStr, this.charset);
                    return cipherAES.decryptString(str.substring(index + 1));
                } else {
                    result = cipher.doFinal(Base64.decodeBase64(str.getBytes(this.charset)));
                    return (new String(result, this.charset)).trim();
                }
            } catch (Exception var7) {
                CipherUtil.log.info("decryptString:" + var7);
                return null;
            }
        }
    }

    private static class CipherDES extends CipherUtil {
        private SecureRandom sr;

        private CipherDES() {
            super();
        }

        private static CipherUtil buildDES(String password, String charset) {
            CipherUtil.CipherDES cipherDES = new CipherUtil.CipherDES();

            try {
                DESKeySpec dks = new DESKeySpec(password.getBytes(charset));
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                cipherDES.sr = new SecureRandom();
                cipherDES.key = keyFactory.generateSecret(dks);
                cipherDES.charset = charset;
            } catch (Exception var5) {
                CipherUtil.log.info("buildDES:" + var5);
            }

            return cipherDES;
        }

        public String encryptString(String str) {
            try {
                Cipher cipher = Cipher.getInstance("DES");
                cipher.init(1, this.key, this.sr);
                byte[] bytes = cipher.doFinal(str.getBytes(this.charset));
                return (new String(Base64.encodeBase64URLSafe(bytes), this.charset)).trim();
            } catch (Exception var4) {
                CipherUtil.log.info("encryptString:" + var4);
                return null;
            }
        }

        public String decryptString(String str) {
            try {
                Cipher cipher = Cipher.getInstance("DES");
                cipher.init(2, this.key, this.sr);
                byte[] result = cipher.doFinal(Base64.decodeBase64(str.getBytes(this.charset)));
                return (new String(result, this.charset)).trim();
            } catch (Exception var4) {
               CipherUtil.log.info("decryptString:" + var4);
                return null;
            }
        }
    }

    private static class CipherAES extends CipherUtil {
        private CipherAES() {
            super();
        }

        private static CipherUtil buildAES(String password, String charset) {
            CipherUtil.CipherAES cipherAES = new CipherUtil.CipherAES();

            try {
                CipherUtil.log.info("构造aes,charset:{},password:{}", charset, password);
                KeyGenerator kGen = KeyGenerator.getInstance("AES");
                SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
                secureRandom.setSeed(password.getBytes());
                kGen.init(128, secureRandom);
                SecretKey secretKey = kGen.generateKey();
                byte[] enCodeFormat = secretKey.getEncoded();
                cipherAES.key = new SecretKeySpec(enCodeFormat, "AES");
                cipherAES.charset = charset;
            } catch (Exception var7) {
                CipherUtil.log.info("buildAES:" + var7);
            }

            return cipherAES;
        }

        private String encryptAES(String s) {
            try {
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(1, this.key);
                byte[] byteContent = s.getBytes(this.charset);
                byte[] bytes = cipher.doFinal(byteContent);
                return (new String(Base64.encodeBase64URLSafe(bytes), this.charset)).trim();
            } catch (Exception var5) {
                CipherUtil.log.info("encryptAES:" + var5);
                return null;
            }
        }

        private String decryptAES(String s) {
            try {
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(2, this.key);
                byte[] data = s.getBytes(this.charset);
                byte[] result = cipher.doFinal(Base64.decodeBase64(data));
                return (new String(result, this.charset)).trim();
            } catch (Exception var5) {
                CipherUtil.log.info("decryptAES:" + var5);
                return null;
            }
        }

        public String encryptString(String str) {
            return this.encryptAES(str);
        }

        public String decryptString(String str) {
            return this.decryptAES(str);
        }
    }
}
