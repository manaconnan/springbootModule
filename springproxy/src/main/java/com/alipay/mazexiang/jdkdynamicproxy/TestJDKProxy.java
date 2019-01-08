/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.jdkdynamicproxy;

import com.alipay.mazexiang.staticproxy.PersonDao;
import com.alipay.mazexiang.staticproxy.PersonDaoImpl;
import com.alipay.mazexiang.staticproxy.Transaction;

import java.lang.reflect.Proxy;

/**
 *
 * @author mazexiang
 * @version $Id: TestJDKProxy.java, v 0.1 2019年01月07日 14:33 mazexiang Exp $
 */
public class TestJDKProxy {
    public static void main(String[] args) {
        /**
         * 1、创建一个目标对象
         * 2、创建一个事务
         * 3、创建一个拦截器
         * 4、动态产生一个代理对象
         */
        Object target = new PersonDaoImpl();
        Transaction transaction = new Transaction();
        Interceptor interceptor = new Interceptor(target, transaction);
        /**
         * 参数一：设置代码使用的类加载器，一般采用跟目标类相同的类加载器
         * 参数二：设置代理类实现的接口，跟目标类使用相同的接口
         * 参数三：设置回调对象，当代理对象的方法被调用时，会调用该参数指定对象的invoke方法
         */
        PersonDao personDao = (PersonDao) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                interceptor);
        personDao.savePerson();
    }
}