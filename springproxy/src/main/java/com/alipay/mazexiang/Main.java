/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mazexiang
 * @version $Id: Main.java, v 0.1 2018年12月18日 20:32 mazexiang Exp $
 */
public class Main {
    public static void main(String[] args) {


        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"aop.xml"});
        Object sellBean = context.getBean("sellBean");

        IShopping iShopping = (ShoppingImplB )sellBean;
        iShopping.buyAnything("香蕉");

        Object buyBean1 = context.getBean("buyBean");


        System.out.println(buyBean1);

        //IShopping shopping = (ShoppingImplA) buyBean1; // 会报错

        IShopping shopping = (IShopping) buyBean1;


        shopping.buyAnything("sdf");


    }
}