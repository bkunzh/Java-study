package com.bkunzh.proxy_study.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class CglibTest {
    @Test
    public void cglibTest() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new MyInterceptor());
        UserService userService = (UserService) enhancer.create();
        log.debug("-----1");
       userService.say1("11");
        log.debug("-----2");
        userService.finalSay("22");
        log.debug(userService.getClass().getName());
        log.debug(userService.getClass().getSuperclass().getName());
        log.debug("##" + Arrays.toString(userService.getClass().getInterfaces()));
    }

    static class MyInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            log.debug("{} {} args: {}", o.getClass().getSuperclass().getSimpleName(), method.getName(), Arrays.toString(objects));
            return methodProxy.invokeSuper(o, objects);
        }
    }
}
