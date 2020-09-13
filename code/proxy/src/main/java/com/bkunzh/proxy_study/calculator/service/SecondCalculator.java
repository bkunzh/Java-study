package com.bkunzh.proxy_study.calculator.service;

public class SecondCalculator implements Calculator, MyInterface {
    @Override
    public Integer add(Integer i, Integer j) throws NoSuchMethodException {
        return null;
    }

    @Override
    public Integer sub(Integer i, Integer j) throws NoSuchMethodException {
        return null;
    }

    @Override
    public Integer mul(Integer i, Integer j) throws NoSuchMethodException {
        return null;
    }

    @Override
    public Integer div(Integer i, Integer j) throws NoSuchMethodException {
        return null;
    }

    @Override
    public Integer show(Integer i) {
        return null;
    }

    @Override
    public int f(int a) {
        System.out.println("f()");
        return a;
    }
}
