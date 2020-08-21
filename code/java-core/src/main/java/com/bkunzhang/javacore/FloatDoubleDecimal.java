package com.bkunzhang.javacore;

public class FloatDoubleDecimal {
    public static void main(String[] args) {
        float f = 2.6f;
        System.out.println(f);

        // 浮点数，转为二进制（即浮点数是怎么二进制存储的）
        int rs1 = Float.floatToIntBits(f);
        System.out.println(Integer.toBinaryString(rs1));

        double d = 2.6;
        System.out.println(Long.toBinaryString(Double.doubleToLongBits(d)));

        float f2 = 0.5F;
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(f2)));

        System.out.println("-------");
        float ff1 = 0.05f;
        float ff2 = 0.01f;
        float ff3 = ff1 + ff2; // 0.060000002， 计算中丢失了精度
        System.out.println(ff3);
        double dd1 = 0.05;
        double dd2 = 0.01;
        double dd3 = dd1 + dd2; // 0.060000000000000005
        System.out.println(dd3);

        double dd4 = 0.06; // 0.06
        System.out.println(dd4);

        System.out.println("----------");
        System.out.println(Double.toString(1.3333));
        System.out.println(String.valueOf(1.3333));
    }
}
