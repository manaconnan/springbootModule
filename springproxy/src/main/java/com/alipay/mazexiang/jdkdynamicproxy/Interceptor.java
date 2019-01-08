/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.jdkdynamicproxy;

/**
 *
 * @author mazexiang
 * @version $Id: Interceptor.java, v 0.1 2019年01月07日 14:32 mazexiang Exp $
 */
import com.alipay.mazexiang.staticproxy.Transaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 拦截器
 *         1、目标类导入进来
 *         2、事物导入进来
 *         3、invoke完成：开启事务、调用目标对象的方法、事务提交
 *
 * @author qjc
 */
public class Interceptor implements InvocationHandler {

    private Object      target; // 目标类
    private Transaction transaction;

    public Interceptor(Object target, Transaction transaction) {
        this.target = target;
        this.transaction = transaction;
    }

    /**
     * @param proxy 目标对象的代理类实例
     * @param method 对应于在代理实例上调用接口方法的Method实例
     * @param args 传入到代理实例上方法参数值的对象数组
     * @return 方法的返回值，没有返回值是null
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        String methodName = method.getName();
        if ("savePerson".equals(methodName)
            || "deletePerson".equals(methodName)
            || "updatePerson".equals(methodName)) {

            this.transaction.beginTransaction(); // 开启事务
            method.invoke(target); // 调用目标方法
            this.transaction.commit(); // 提交事务

        } else {
            method.invoke(target);
        }
        return null;
    }
}