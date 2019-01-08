/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.enums;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONType;

/**
 *
 * @author mazexiang
 * @version $Id: Color.java, v 0.1 2018年12月24日 21:31 mazexiang Exp $
 */

public @JSONType(serializeEnumAsJavaBean = true)
enum Color {
    RED("red", "红色"),
    BLUE("blue", "蓝色"),
    YELLOW("yellow", "黄色");

    private String code;
    private String name;

    Color(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        // {"code":"red","name":"红色"} 序列化成功
        String s = JSON.toJSONString(Color.RED);
        System.out.println(s);


        Color color = JSON.parseObject(s, Color.class);
        System.out.println(color.name);
    }
}