/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.cglibproxy;

import com.alipay.mazexiang.staticproxy.Transaction;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB代理 拦截器
 * @author qjc
 */
public class Interceptor  implements MethodInterceptor {

    private Object      target; // 代理的目标类
    private Transaction transaction;

    public Interceptor(Object target, Transaction transaction) {
        this.target = target;
        this.transaction = transaction;
    }

    /**
     * 创建目标对象的代理对象
     *
     * @return
     */
    public Object createProxy() {
        // 代码增强
        Enhancer enhancer = new Enhancer(); // 该类用于生成代理对象
        enhancer.setCallback(this); // 参数为拦截器
        enhancer.setSuperclass(target.getClass());// 设置父类
        return enhancer.create(); // 创建代理对象
    }

    /**
     * @param obj 目标对象代理类的实例
     * @param method 代理实例上 调用父类方法的Method实例
     * @param args 传入到代理实例上方法参数值的对象数组
     * @param methodProxy 使用它调用父类的方法
     * @return
     * @throws Throwable
     */
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        this.transaction.beginTransaction();

        method.invoke(target);

        this.transaction.commit();

        return null;
    }
}