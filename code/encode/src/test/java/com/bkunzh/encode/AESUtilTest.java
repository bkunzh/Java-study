package com.bkunzh.encode;

import com.bkunzh.encode.util.AESUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESUtilTest {
    static Logger logger = LoggerFactory.getLogger(AESUtilTest.class);

    @Test
    public void test() {
        String s = "加密  测试加密  测试加密  测试加密  测试加密  测试加密  测试加密  测试";
        // 加密  默认key
        logger.info("加密前：" + s);
        String encryptResultStr1 = AESUtil.encrypt(s);
        logger.info("加密后：" + encryptResultStr1);
        // 解密
        logger.info("解密后：" + AESUtil.decrypt(encryptResultStr1));

        AESUtil.DEFAULT_KEY = "1230485203699875";
        // 加密
        logger.info("加密前：" + s);
        String encryptResultStr = AESUtil.encrypt(s);
        logger.info("加密后：" + encryptResultStr);
        // 解密
        logger.info("解密后：" + AESUtil.decrypt(encryptResultStr));

        logger.info("---------------------------------------------");

        logger.info("加密后：" + AESUtil.encrypt("test", AESUtil.DEFAULT_KEY, AESUtil.DEFAULT_IV));
        logger.info("解密后：" + AESUtil.decrypt(AESUtil.encrypt("test", AESUtil.DEFAULT_KEY, AESUtil.DEFAULT_IV), AESUtil.DEFAULT_KEY, AESUtil.DEFAULT_IV));
        String mykey = "0123456789050123";
        logger.info("32位密钥加密测试：" + AESUtil.encrypt("当我们把密钥定为大于128时（即192或256）时",mykey, AESUtil.DEFAULT_IV));
        logger.info("32位密钥解密测试：" + AESUtil.decrypt(AESUtil.encrypt("当我们把密钥定为大于128时（即192或256）时", mykey, AESUtil.DEFAULT_IV), mykey, AESUtil.DEFAULT_IV));

    }
}