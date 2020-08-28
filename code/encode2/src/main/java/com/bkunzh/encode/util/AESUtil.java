package com.bkunzh.encode.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.net.util.Base64;
import java.nio.charset.Charset;

/**
 * AES工具类
 */
public class AESUtil {

    // 密钥
    public static String DEFAULT_KEY = "AD42F6697B035B7580E4FEF93BE20BAD"; //长度必须为16、24、32位，即128bit、192bit、256bit
    public static String DEFAULT_IV = "AD42F6697B035B75";
    private static String DEFAULT_CHARSET = "utf-8";
    // 偏移量
    private static int OFFSET = 16;
    private static String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static String ALGORITHM = "AES";

    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param key 加密密码
     * @param iv
     * @return
     */
    public static String encrypt(String content, String key, String iv) {
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(Charset.forName(DEFAULT_CHARSET)), ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes(), 0, OFFSET);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            byte[] byteContent = content.getBytes(DEFAULT_CHARSET);
            cipher.init(Cipher.ENCRYPT_MODE, skey, ivSpec);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return new Base64().encodeToString(result); // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES（256）解密
     *
     * @param content 待解密内容
     * @param key 解密密钥
     * @param iv
     * @return 解密之后
     * @throws Exception
     */
    public static String decrypt(String content, String key, String iv) {
        try {

            SecretKeySpec skey = new SecretKeySpec(key.getBytes(Charset.forName(DEFAULT_CHARSET)), ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes(Charset.forName(DEFAULT_CHARSET)), 0, OFFSET);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, skey, ivSpec);// 初始化
            byte[] result = cipher.doFinal(new Base64().decode(content));
            return new String(result, Charset.forName(DEFAULT_CHARSET)); // 解密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     *
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        return encrypt(content, DEFAULT_KEY, DEFAULT_IV);
    }

    /**
     * 解密
     *
     * @param content
     * @return
     */
    public static String decrypt(String content) {
        return decrypt(content, DEFAULT_KEY, DEFAULT_IV);
    }
}