/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mazexiang
 * @version $Id: webController.java, v 0.1 2018年09月05日 19:33 mazexiang Exp $
 */
@RestController
@RequestMapping("/user/*")
public class webController {

    private static Logger logger = LoggerFactory.getLogger(webController.class);
    @Value("${my.name}")
    private String name;

    @GetMapping("/helloweb")
    public String helloWeb(){

        logger.info("hello web called~");
        return "hello web ~~"+name;
    }
}