/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.staticproxy;

/**
 *
 * @author mazexiang
 * @version $Id: PersonDaoProxy.java, v 0.1 2019年01月07日 14:29 mazexiang Exp $
 */
/**
 * 静态代理类
 */
public class PersonDaoProxy implements PersonDao{

    PersonDao personDao;
    Transaction transaction;

    public PersonDaoProxy(PersonDao personDao, Transaction transaction) {
        this.personDao = personDao;
        this.transaction = transaction;
    }

    @Override
    public void savePerson() {
        this.transaction.beginTransaction();
        this.personDao.savePerson();
        this.transaction.commit();
    }
}