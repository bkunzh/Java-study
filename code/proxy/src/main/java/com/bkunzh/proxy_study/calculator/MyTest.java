package com.bkunzh.proxy_study.calculator;

import com.bkunzh.proxy_study.calculator.proxy.CalculatorProxy;
import com.bkunzh.proxy_study.calculator.service.Calculator;
import com.bkunzh.proxy_study.calculator.service.MyCalculator;
import org.junit.Test;

public class MyTest {
    @Test
    public void test01() throws NoSuchMethodException {

        MyCalculator myCalculator = new MyCalculator();
        System.out.println(myCalculator.add(1, 2));
        System.out.println(myCalculator.div(1, 1));

        Calculator calculator = CalculatorProxy.getProxy(new MyCalculator());
        System.out.println(calculator.add(1, 1));
        calculator.sub(1,1);
        calculator.mul(1,1);
        calculator.div(1,0);
        System.out.println(calculator.getClass());
        System.out.println("------------------");
        calculator.show(100);
    }
}
