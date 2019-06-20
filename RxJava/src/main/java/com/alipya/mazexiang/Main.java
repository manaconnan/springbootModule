/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alipya.mazexiang;

import io.reactivex.Observable;

/**
 *
 * @author mazexiang
 * @version $Id: Main.java, v 0.1 2019Äê06ÔÂ17ÈÕ 14:49 mazexiang Exp $
 */
public class Main {

    public static void main(String[] args) {
        Observable<Integer> observable = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
        });
        observable.subscribe(System.out::println);

        System.out.println("========");
        Observable.range(5, 10).subscribe(i -> System.out.println("num: " + i));

    }
}