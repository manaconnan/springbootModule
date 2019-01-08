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
 * CGLIB���� ������
 * @author qjc
 */
public class Interceptor  implements MethodInterceptor {

    private Object      target; // �����Ŀ����
    private Transaction transaction;

    public Interceptor(Object target, Transaction transaction) {
        this.target = target;
        this.transaction = transaction;
    }

    /**
     * ����Ŀ�����Ĵ������
     *
     * @return
     */
    public Object createProxy() {
        // ������ǿ
        Enhancer enhancer = new Enhancer(); // �����������ɴ������
        enhancer.setCallback(this); // ����Ϊ������
        enhancer.setSuperclass(target.getClass());// ���ø���
        return enhancer.create(); // �����������
    }

    /**
     * @param obj Ŀ�����������ʵ��
     * @param method ����ʵ���� ���ø��෽����Methodʵ��
     * @param args ���뵽����ʵ���Ϸ�������ֵ�Ķ�������
     * @param methodProxy ʹ�������ø���ķ���
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