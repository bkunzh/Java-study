package com.bkunzh.proxy_study.cglib;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserService {
    public String say1(String s) {
        log.debug("say1():" + s);
        return "say1:" + s;
    }

    public final void finalSay(String s) {
        log.debug("findSay():" + s);
    }
}
