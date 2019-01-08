/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.staticproxy;

/**
 *
 * @author mazexiang
 * @version $Id: PersonDaoImpl.java, v 0.1 2019Äê01ÔÂ07ÈÕ 14:28 mazexiang Exp $
 */
public class PersonDaoImpl implements PersonDao {
    @Override
    public void savePerson() {
        System.out.println("save person");
    }
}