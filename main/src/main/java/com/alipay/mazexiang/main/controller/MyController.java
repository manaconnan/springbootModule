/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.mazexiang.service.TestBean;
import com.alipay.mazexiang.service.TestBean2;
import com.alipay.mazexiang.service.TestBean3;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySources;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author mazexiang
 * @version $Id: MyController.java, v 0.1 2018年09月05日 19:22 mazexiang Exp $
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
    public String testBean(String msg){
        return "hello ++"+testBean.sayHello(msg);
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

    @Resource
    ApplicationContext applicationContext;
    @GetMapping("/testNew")
    public String testNew(String msg){
        //TestResourceChile testResource = new TestResourceChile(applicationContext);

        BaseResource baseResource = new BaseResource();
        String test = baseResource.test(msg);

        return test;
    }

    @GetMapping(""
                + "")
    public JSONObject getJsonObject(){
        JSONObject jsonObject = new JSONObject();

        Map<String ,String > head = new HashMap<>();
        head.put("version","2");
        head.put("provider","CAWP");
        jsonObject.put("head",head);

        Map<String,String> data = new HashMap<>();
        data.put("str","-1001-1001各类|个号呀|上帝啊|话说\r\n-10011|2|3|4|5\r\n-1001客户数|运行\r\n-1001中期票据|中票\r\n-1001政金债|政策性金融债\r\n-1001PPN"
                       + "|定向工具\r\n-1001招行|招商银行\r\n-1001大笑|开心心|嘻嘻|哈哈\r\nmzx|马泽---祥|穆冉");
        jsonObject.put("data",data);
        return jsonObject;

    }
    //@Autowired
    //PropertySources propertySources;
    //@GetMapping("/getProperties")
    //public String getProperties(){
    //
    //    String property = environment.getProperty("my.name");
    //
    //    MyConfigurationPropertySourcesPropertySource configurationProperties =(MyConfigurationPropertySourcesPropertySource) propertySources.get("configurationProperties").getSource();
    //
    //    Iterable<ConfigurationPropertySource> source = configurationProperties.getSource();
    //    Iterator<ConfigurationPropertySource> iterator = source.iterator();
    //    while (iterator.hasNext()){
    //        ConfigurationPropertySource next = iterator.next();
    //    }
    //    String[] activeProfiles = environment.getActiveProfiles();
    //
    //    return property;
    //}
}