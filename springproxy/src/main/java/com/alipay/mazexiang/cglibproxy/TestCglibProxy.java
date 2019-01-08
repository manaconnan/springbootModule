/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.cglibproxy;

import com.alipay.mazexiang.staticproxy.PersonDaoImpl;
import com.alipay.mazexiang.staticproxy.Transaction;

/**
 *
 * @author mazexiang
 * @version $Id: TestCglibProxy.java, v 0.1 2019Äê01ÔÂ07ÈÕ 14:38 mazexiang Exp $
 */
public class TestCglibProxy {
    public static void main(String[] args) {
        Object target = new PersonDaoImpl();
        Transaction transaction = new Transaction();
        Interceptor interceptor = new Interceptor(target, transaction);

        PersonDaoImpl personDaoImpl = (PersonDaoImpl) interceptor.createProxy();
        personDaoImpl.savePerson();
    }
}