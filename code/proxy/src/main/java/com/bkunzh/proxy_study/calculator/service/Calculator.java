package com.bkunzh.proxy_study.calculator.service;

public interface Calculator {
    Integer add(Integer i,Integer j) throws NoSuchMethodException;
    Integer sub(Integer i,Integer j) throws NoSuchMethodException;
    Integer mul(Integer i,Integer j) throws NoSuchMethodException;
    Integer div(Integer i,Integer j) throws NoSuchMethodException;
    Integer show(Integer i);
}
