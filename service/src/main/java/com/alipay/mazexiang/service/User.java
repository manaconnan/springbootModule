/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.service;

import lombok.Data;

/**
 *
 * @author mazexiang
 * @version $Id: User.java, v 0.1 2018Äê09ÔÂ14ÈÕ 17:15 mazexiang Exp $
 */
@Data
public class User {
    private String id;
    private String userName;
    private String password;
    public User(String userName,String password){
        this.userName = userName;
        this.password = password;
    }
}