package com.bkunzh.proxy_study.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

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
        System.out.println("模拟开启事务..., 方法" + method.getName() + ", 参数" + Arrays.toString(args));
        //调用实际业务类的方法
        Object rs = method.invoke(userService, args);
        System.out.println("模拟提交事务..., 方法" + method.getName() + ", 返回结果" + rs);
        return rs;
    }
}
