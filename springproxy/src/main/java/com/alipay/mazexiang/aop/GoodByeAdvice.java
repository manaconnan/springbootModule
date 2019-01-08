/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 *
 * @author mazexiang
 * @version $Id: GoodByeAdvice.java, v 0.1 2019Äê01ÔÂ07ÈÕ 14:08 mazexiang Exp $
 */
public class GoodByeAdvice implements AfterReturningAdvice {

    public void afterReturning(@Nullable Object returnValue, Method method, Object[] args, @Nullable Object target) throws Throwable {
        System.out.println("good bye~~! ");
    }
}