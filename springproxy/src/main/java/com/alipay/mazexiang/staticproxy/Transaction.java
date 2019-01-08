/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.staticproxy;

/**
 *
 * @author mazexiang
 * @version $Id: Transaction.java, v 0.1 2019Äê01ÔÂ07ÈÕ 14:29 mazexiang Exp $
 */
public class Transaction {
    public void beginTransaction(){
        System.out.println("begin Transaction");
    }

    public void commit(){
        System.out.println("commit");
    }
}