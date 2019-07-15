package com.lxg.springboot.test.singleton;

/**
 * @program: springboot-security-oauth2
 * @description: 懒汉式
 * 延迟加载，是
 * 线程安全：否
 * @author: lijianzhi
 * @create: 2019-07-15 09:51
 **/
public class SingletonLazy {

    private static SingletonLazy instance ;

    private SingletonLazy(){}

    public static SingletonLazy getInstance()
    {
        if (instance == null)
        {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
