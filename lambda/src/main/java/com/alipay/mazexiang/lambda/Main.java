/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.lambda;

/**
 *
 * @author mazexiang
 * @version $Id: Main.java, v 0.1 2019年05月31日 16:32 mazexiang Exp $
 */
public class Main {

    public static void main(String[] args) {
        //重写create方法而已
        PersonFactory<Person> personFactory = Person::new;

        // 这个方法等同于上个语句
        PersonFactory<Person> personFactory1 = new PersonFactory<Person>() {
            @Override
            public Person create(String firstName, String lastName) {
                return new Person(firstName,lastName);
            }
        };


        Person person = personFactory.create("ma", "zexiang");
        System.out.println(person);

    }
}