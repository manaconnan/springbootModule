/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang;

/**
 *
 * @author mazexiang
 * @version $Id: ShoppingImplB.java, v 0.1 2018Äê12ÔÂ18ÈÕ 20:22 mazexiang Exp $
 */

public class ShoppingImplB implements IShopping {
    private Customer customer;
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public String buySomething(String type) {
        System.out.println(this.getCustomer().getName()+" bye "+type+" success");
        return null;
    }

    public String buyAnything(String type) {
        System.out.println(this.getCustomer().getName()+" bye "+type+" success");
        return null;

    }
    public String sellAnything(String type) {
        System.out.println(this.getCustomer().getName()+" sell "+type+" success");
        return null;
    }
    public String sellSomething(String type) {
        System.out.println(this.getCustomer().getName()+" sell "+type+" success");
        return null;
    }

}