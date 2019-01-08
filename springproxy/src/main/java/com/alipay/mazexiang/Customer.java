/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang;

import lombok.Data;

/**
 *
 * @author mazexiang
 * @version $Id: Customer.java, v 0.1 2018Äê12ÔÂ18ÈÕ 20:19 mazexiang Exp $
 */
@Data
public class Customer {
    private String name;

    private int age;
    public Customer(){}

    public Customer(String name,int age){
        this.age = age;
        this.name = name;
    }
}