package com.bkunzhang.proxy;

/**
 * @author bkunzhang
 * @date 2019/9/19
 */
public class UserServiceImpl implements UserService {
    @Override
    public void save(int a) {
        System.out.println("模拟保存:" + a);
    }

    @Override
    public void delete(int a) {
        System.out.println("模拟删除:" + a);
    }
}
