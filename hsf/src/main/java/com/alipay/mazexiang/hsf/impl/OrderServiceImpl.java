/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.hsf.impl;

import com.alipay.mazexiang.hsf.OrderModel;
import com.alipay.mazexiang.hsf.OrderService;

/**
 *
 * @author mazexiang
 * @version $Id: OrderServiceImpl.java, v 0.1 2019Äê03ÔÂ14ÈÕ 21:05 mazexiang Exp $
 */
public class OrderServiceImpl implements OrderService {
    public OrderModel getOrderById(String id) {
        return new OrderModel(id,"test");
    }
}