/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.staticproxy;

/**
 *
 * @author mazexiang
 * @version $Id: PersonDaoProxy.java, v 0.1 2019��01��07�� 14:29 mazexiang Exp $
 */
/**
 * ��̬������
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