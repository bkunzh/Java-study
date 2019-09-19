package com.bkunzhang.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author bkunzhang
 * @date 2019/9/19
 */
public class UserServiceHandler implements InvocationHandler {

    private UserService userService;

    public UserServiceHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("模拟开启事务...");
        //调用实际业务类的方法
        Object rs = method.invoke(userService, args);
        System.out.println("模拟提交事务...");
        return rs;
    }
}
