package com.alipay.mazexiang.main.controller; /**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author mazexiang
 * @version $Id: com.alipay.mazexiang.main.controller.MainApplication.java, v 0.1 2018Äê09ÔÂ05ÈÕ 19:17 mazexiang Exp $
 */
@ComponentScan(basePackages = {"com.alipay"})

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MainApplication.class);
        app.run( args);

    }
}