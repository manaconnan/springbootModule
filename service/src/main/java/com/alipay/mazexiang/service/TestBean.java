/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @author mazexiang
 * @version $Id: TestBean.java, v 0.1 2018Äê09ÔÂ11ÈÕ 17:18 mazexiang Exp $
 */

@Component
@Data
@Slf4j
public class TestBean {

    private String name;

    public String sayHello(){

        log.info("TestBean initial_");

        return "TestBean sayHello...";
    }

}