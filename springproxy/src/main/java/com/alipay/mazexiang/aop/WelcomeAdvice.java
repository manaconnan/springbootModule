/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.aop;


import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 *
 * @author mazexiang
 * @version $Id: WelcomeAdvice.java, v 0.1 2018Äê12ÔÂ18ÈÕ 20:23 mazexiang Exp $
 */
public class WelcomeAdvice implements MethodBeforeAdvice {

    public void before(Method method, Object[] args, Object obj)
            throws Throwable {

        System.out.println("Hello welcome to buy ");


    }

}
