/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.service;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author mazexiang
 * @version $Id: TestBean3.java, v 0.1 2018Äê10ÔÂ18ÈÕ 10:50 mazexiang Exp $
 */
@Slf4j
public class TestBean3 {
    private String name;

    public String sayHello(){
        log.info("testBean3 sayHello called");
        return "TestBean3 sayHello...";
    }
}