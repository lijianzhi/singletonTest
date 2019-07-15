package com.lxg.springboot.test.singleton;

/**
 * @program: springboot-security-oauth2
 * @description: 懒汉线程安全
 * 是否延迟加载：是
 * 是否线程安全：是 效率低下
 * @author: lijianzhi
 * @create: 2019-07-15 09:55
 **/
public class SingletonLazySyschronize {
    private static SingletonLazySyschronize instance;

    private SingletonLazySyschronize(){}

    public static SingletonLazySyschronize getInstance()
    {
        synchronized (SingletonLazySyschronize.class)
        {
            if (instance == null)
            {
                instance = new SingletonLazySyschronize();
            }
        }
        return instance;
    }
}
