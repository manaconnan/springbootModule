/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.main.controller;

import com.alipay.mazexiang.service.TestBean;
import com.alipay.mazexiang.service.TestBean2;
import com.alipay.mazexiang.service.TestBean3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySources;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

/**
 *
 * @author mazexiang
 * @version $Id: MyController.java, v 0.1 2018Äê09ÔÂ05ÈÕ 19:22 mazexiang Exp $
 */
@RestController
@RequestMapping("/user/*")
@Slf4j
public class MyController {

    @Value("${my.name}")
    private String name;

    @Autowired
    private TestBean testBean;

    @Autowired
    private TestBean2 testBean2;

    @Autowired
    private TestBean3 testBean3;

    @Autowired
    private Environment environment;

    @GetMapping("/hello")
    public String hello(){
        return "hello world~~"+name;
    }

    @GetMapping("/testBean")
    public String testBean(){
        return "hello ++"+testBean.sayHello();
    }
    @GetMapping("/testBean2")
    public String testBean2(){

        return "hello ++"+testBean2.sayHello();
    }

    @GetMapping("/testBean3")
    public String testBean3(){
        log.info("testBean3 "+ testBean3);
        return testBean3.sayHello();
    }

    @Autowired
    PropertySources propertySources;
    @GetMapping("/getProperties")


    public String getProperties(){

        String property = environment.getProperty("my.name");

        MyConfigurationPropertySourcesPropertySource configurationProperties =(MyConfigurationPropertySourcesPropertySource) propertySources.get("configurationProperties").getSource();

        Iterable<ConfigurationPropertySource> source = configurationProperties.getSource();
        Iterator<ConfigurationPropertySource> iterator = source.iterator();
        while (iterator.hasNext()){
            ConfigurationPropertySource next = iterator.next();
        }
        String[] activeProfiles = environment.getActiveProfiles();

        return property;
    }
}