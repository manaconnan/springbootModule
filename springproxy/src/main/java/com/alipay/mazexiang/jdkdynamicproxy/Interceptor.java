/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.jdkdynamicproxy;

/**
 *
 * @author mazexiang
 * @version $Id: Interceptor.java, v 0.1 2019��01��07�� 14:32 mazexiang Exp $
 */
import com.alipay.mazexiang.staticproxy.Transaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ������
 *         1��Ŀ���ർ�����
 *         2�����ﵼ�����
 *         3��invoke��ɣ��������񡢵���Ŀ�����ķ����������ύ
 *
 * @author qjc
 */
public class Interceptor implements InvocationHandler {

    private Object      target; // Ŀ����
    private Transaction transaction;

    public Interceptor(Object target, Transaction transaction) {
        this.target = target;
        this.transaction = transaction;
    }

    /**
     * @param proxy Ŀ�����Ĵ�����ʵ��
     * @param method ��Ӧ���ڴ���ʵ���ϵ��ýӿڷ�����Methodʵ��
     * @param args ���뵽����ʵ���Ϸ�������ֵ�Ķ�������
     * @return �����ķ���ֵ��û�з���ֵ��null
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        String methodName = method.getName();
        if ("savePerson".equals(methodName)
            || "deletePerson".equals(methodName)
            || "updatePerson".equals(methodName)) {

            this.transaction.beginTransaction(); // ��������
            method.invoke(target); // ����Ŀ�귽��
            this.transaction.commit(); // �ύ����

        } else {
            method.invoke(target);
        }
        return null;
    }
}