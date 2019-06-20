/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipay.mazexiang.lambda.parallestream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 *
 * @author mazexiang
 * @version $Id: Main.java, v 0.1 2019年06月03日 10:49 mazexiang Exp $
 */
public class Main {

    public static void main(String[] args) {
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

       sequentialSort(values);

        // sequential sort took: 899 ms
        parallelSort(values);

        // parallel sort took: 323 ms


    }

    private static void sequentialSort(List<String> values){
        long t0 = System.nanoTime();

        Stream<String> sorted = values.stream().sorted();
        long count = sorted.count();

        // stream 只能被消费一次
        //long count1 = sorted.count();

        System.out.println(count);
        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
    }

    private static void parallelSort(List<String> values){
        long t0 = System.nanoTime();

        long count = values.parallelStream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));

    }
}