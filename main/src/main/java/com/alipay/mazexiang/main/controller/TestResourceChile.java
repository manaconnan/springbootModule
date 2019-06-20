/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.main.controller;

import com.alipay.mazexiang.service.TestBean;
import com.alipay.mazexiang.service.TestBean2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 *
 * @author mazexiang
 * @version $Id: TestResourceChile.java, v 0.1 2019Äê03ÔÂ01ÈÕ 11:11 mazexiang Exp $
 */
public class TestResourceChile extends BaseResource {

    @Resource
    private TestBean testBean;

    @Autowired
    private TestBean2 testBean2;

    public TestResourceChile(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public String test(String msg){
        return  testBean.sayHello(msg);
    }

    public String test2(){
        return  testBean2.sayHello();
    }

}