/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.lambda;

/**
 *
 * @author mazexiang
 * @version $Id: Main.java, v 0.1 2019��05��31�� 16:32 mazexiang Exp $
 */
public class Main {

    public static void main(String[] args) {
        //��дcreate��������
        PersonFactory<Person> personFactory = Person::new;

        // ���������ͬ���ϸ����
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