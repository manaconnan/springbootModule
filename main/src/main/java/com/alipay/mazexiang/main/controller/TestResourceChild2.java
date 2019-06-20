/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.main.controller;

import com.alipay.mazexiang.service.TestBean3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author mazexiang
 * @version $Id: TestResourceChild2.java, v 0.1 2019Äê03ÔÂ01ÈÕ 11:29 mazexiang Exp $
 */
public  class TestResourceChild2 extends BaseResource {

    @Autowired
    private TestBean3 testBean3;

    public TestResourceChild2(ApplicationContext applicationContext) {
        super(applicationContext);
    }


}