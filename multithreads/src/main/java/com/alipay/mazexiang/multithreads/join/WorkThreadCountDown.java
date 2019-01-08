/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.multithreads.join;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

/**
 *
 * @author mazexiang
 * @version $Id: WorkThreadCountDown.java, v 0.1 2018��12��24�� 14:14 mazexiang Exp $
 */
public class WorkThreadCountDown implements Runnable {

    private CountDownLatch countDownLatch;

    public WorkThreadCountDown(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread(). getName() + "run start.");
            //ģ�����������ִ�е�ʱ��
            sleep(1000);
            System.out.println(Thread.currentThread(). getName() + "run finished.");
            countDownLatch.countDown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}