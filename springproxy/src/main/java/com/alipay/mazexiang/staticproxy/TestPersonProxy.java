/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.staticproxy;

/**
 *
 * @author mazexiang
 * @version $Id: TestPersonProxy.java, v 0.1 2019Äê01ÔÂ07ÈÕ 14:30 mazexiang Exp $
 */
public class TestPersonProxy {
    public static void main(String[] args) {
        PersonDao personDao = new PersonDaoImpl();
        Transaction transaction = new Transaction();
        PersonDaoProxy proxy = new PersonDaoProxy(personDao, transaction);

        proxy.savePerson();
    }
}