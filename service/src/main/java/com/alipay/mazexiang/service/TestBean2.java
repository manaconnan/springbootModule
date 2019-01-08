/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author mazexiang
 * @version $Id: TestBean2.java, v 0.1 2018Äê09ÔÂ11ÈÕ 17:26 mazexiang Exp $
 */
@Data
@Slf4j
public class TestBean2 {



    private String name;

    public String sayHello(){
        log.info("testBean2 sayHello called");
        return "TestBean2 sayHello...";
    }
}