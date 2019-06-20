/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 *
 * @author mazexiang
 * @version $Id: TestBean.java, v 0.1 2018Äê09ÔÂ11ÈÕ 17:18 mazexiang Exp $
 */

@Component
@Data
@Slf4j
@RequestScope
public class TestBean {

    private String name;

    private StringBuilder stringBuilder = new StringBuilder();

    public String sayHello(String msg){


        log.info("TestBean initial_");
        stringBuilder.append(msg);

        return "TestBean say "+stringBuilder.toString();
    }

}