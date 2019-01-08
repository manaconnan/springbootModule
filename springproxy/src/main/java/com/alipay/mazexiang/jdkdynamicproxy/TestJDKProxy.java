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
 * @version $Id: TestJDKProxy.java, v 0.1 2019��01��07�� 14:33 mazexiang Exp $
 */
public class TestJDKProxy {
    public static void main(String[] args) {
        /**
         * 1������һ��Ŀ�����
         * 2������һ������
         * 3������һ��������
         * 4����̬����һ���������
         */
        Object target = new PersonDaoImpl();
        Transaction transaction = new Transaction();
        Interceptor interceptor = new Interceptor(target, transaction);
        /**
         * ����һ�����ô���ʹ�õ����������һ����ø�Ŀ������ͬ���������
         * �����������ô�����ʵ�ֵĽӿڣ���Ŀ����ʹ����ͬ�Ľӿ�
         * �����������ûص����󣬵��������ķ���������ʱ������øò���ָ�������invoke����
         */
        PersonDao personDao = (PersonDao) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                interceptor);
        personDao.savePerson();
    }
}