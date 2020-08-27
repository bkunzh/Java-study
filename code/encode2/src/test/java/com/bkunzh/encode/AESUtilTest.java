package com.bkunzh.encode;

import org.junit.Test;

import static org.junit.Assert.*;

public class AESUtilTest {
    @Test
    public static void main(String[] args) {
        String s = "加密测试";
        // 加密  默认key
        System.out.println("加密前：" + s);
        String encryptResultStr1 = AESUtil.encrypt(s);
        System.out.println("加密后：" + encryptResultStr1);
        // 解密
        System.out.println("解密后：" + AESUtil.decrypt(encryptResultStr1));

        AESUtil.key = "1230485203699875";
        // 加密
        System.out.println("加密前：" + s);
        String encryptResultStr = AESUtil.encrypt(s);
        System.out.println("加密后：" + encryptResultStr);
        // 解密
        System.out.println("解密后：" + AESUtil.decrypt(encryptResultStr));

        System.out.println("---------------------------------------------");

        System.out.println("加密后：" + AESUtil.encrypt("test", AESUtil.key));
        System.out.println("解密后：" + AESUtil.decrypt(AESUtil.encrypt("test", AESUtil.key), AESUtil.key));

        System.out.println("32位密钥加密测试：" + AESUtil.encrypt("当我们把密钥定为大于128时（即192或256）时","01234567890123450123456789012345"));
        System.out.println("32位密钥解密测试：" + AESUtil.decrypt(AESUtil.encrypt("当我们把密钥定为大于128时（即192或256）时", "01234567890123450123456789012345"), "01234567890123450123456789012345"));

    }
}