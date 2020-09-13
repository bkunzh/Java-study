package com.bkunzh.proxy_study.calculator;

import com.bkunzh.proxy_study.calculator.proxy.ProxyUtil;
import com.bkunzh.proxy_study.calculator.service.Calculator;
import com.bkunzh.proxy_study.calculator.service.MyCalculator;
import com.bkunzh.proxy_study.calculator.service.MyInterface;
import com.bkunzh.proxy_study.calculator.service.SecondCalculator;
import org.junit.Test;

public class MyTest {
    @Test
    public void test01() throws NoSuchMethodException {

        MyCalculator myCalculator = new MyCalculator();
        System.out.println(myCalculator.add(1, 2));
        System.out.println(myCalculator.div(1, 1));

        // jdk动态代理只能转接口
//        Calculator calculator = (MyCalculator) ProxyUtil.getProxy(new MyCalculator());
        Calculator calculator = (Calculator) ProxyUtil.getProxy(new MyCalculator());
        System.out.println(calculator.add(1, 1));
        calculator.sub(1,1);
        calculator.mul(1,1);
        calculator.div(1,0);
        System.out.println(calculator.getClass());
        System.out.println("------------------");
        calculator.show(100);

        System.out.println("--------");
        Calculator calculator2 = new SecondCalculator();
        Calculator cal2 = (Calculator) ProxyUtil.getProxy(calculator2);
        cal2.add(1, 2);

        System.out.println("-----");
        // 只能转接口
//        SecondCalculator cal3 = (SecondCalculator) ProxyUtil.getProxy(calculator2);
        MyInterface in3 = (MyInterface) ProxyUtil.getProxy(calculator2);
        in3.f(9);
    }
}
