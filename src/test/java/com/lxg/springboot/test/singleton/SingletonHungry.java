package com.lxg.springboot.test.singleton;

/**
 * @program: springboot-security-oauth2
 * @description: 饿汉式
 * 延迟加载：否
 * 线程安全：是，静态代码块在类加载是已经初始化
 * @author: lijianzhi
 * @create: 2019-07-15 09:45
 **/
public class SingletonHungry {

    private static SingletonHungry instance = new SingletonHungry();

    private SingletonHungry(){}

    public static SingletonHungry getInstance()
    {
        return instance;
    }

}
