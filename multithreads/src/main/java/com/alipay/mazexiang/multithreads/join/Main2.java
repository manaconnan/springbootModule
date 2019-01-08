/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.multithreads.join;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author mazexiang
 * @version $Id: Main2.java, v 0.1 2018��12��24�� 14:15 mazexiang Exp $
 */
public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(2);

        //�����������߳�ִ��������
        WorkThreadCountDown workThreadCountDown1 = new WorkThreadCountDown(countDownLatch);
        WorkThreadCountDown workThreadCountDown2 = new WorkThreadCountDown(countDownLatch);
        service.execute(workThreadCountDown1);
        service.execute(workThreadCountDown2);
        countDownLatch.getCount();
        countDownLatch.await();
        //���к����ҵ��
        System.out.println("run next process.");
    }
}