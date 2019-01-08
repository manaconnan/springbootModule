/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author mazexiang
 * @version $Id: TestConfiguration.java, v 0.1 2018��09��11�� 17:20 mazexiang Exp $
 */

@Configuration
public class TestConfiguration {
    public TestConfiguration(){
        System.out.println("spring����������ʼ��������");
    }

    //@Beanע��ע��bean,ͬʱ����ָ����ʼ�������ٷ���
    //@Bean(name="testNean",initMethod="start",destroyMethod="cleanUp")
    @Bean
    @Scope("prototype")
    public TestBean2 testBean2() {
        return new TestBean2();
    }



    @Bean
    public TestBean3 testBean3(){
        return new TestBean3();
    }
}