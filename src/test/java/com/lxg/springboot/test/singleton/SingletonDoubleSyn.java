package com.lxg.springboot.test.singleton;

/**
 * @program: springboot-security-oauth2
 * @description: 双重校验锁 安全且延迟加载，效率高，写法复杂
 * @author: lijianzhi
 * @create: 2019-07-15 10:01
 **/
public class SingletonDoubleSyn {

   /* instance = new SingletonDemo();可以分为以下3步完成（伪代码）

    memory = allocate();    // 1.分配内存对象空间

    instance = (memory);   // 2.初始化对象

    instance = memory;     // 3.设置instance指向刚分配的内存地址，此时instance != null

    由于步骤2和步骤3不存在数据依赖关系，而且无论重排前还是重排后程序的执行结果在单线程中并没有改变，因此这种重排优化是允许的。

    memory = allocate();    // 1.分配内存对象空间

    instance = memory;     // 3.设置instance指向刚分配的内存地址，此时instance != null，但是对象初始化没有完成，就相当于这个地址空间内没有实际的值。

    instance = (memory);   // 2.初始化对象*/

    private volatile  static SingletonDoubleSyn instance;

    private SingletonDoubleSyn(){}

    public static SingletonDoubleSyn getInstance()
    {
        if (instance == null)
        {
            synchronized (SingletonDoubleSyn.class)
            {
                if (instance == null)
                {
                    instance = new SingletonDoubleSyn();
                }
            }
        }
        return instance;
    }
}
