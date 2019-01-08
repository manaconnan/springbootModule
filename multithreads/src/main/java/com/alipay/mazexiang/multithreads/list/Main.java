/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.multithreads.list;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author mazexiang
 * @version $Id: Main.java, v 0.1 2018年12月27日 14:09 mazexiang Exp $
 */
public class Main {
    private static ExecutorService service = Executors.newFixedThreadPool(2);
    private static int count = 0;
    protected static class Task implements Runnable{
        private List<String> stringList = new ArrayList<>();

        private CountDownLatch countDownLatch ;
        public Task (List<String> list,CountDownLatch countDownLatch){
            this.stringList .addAll( list);
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            System.out.println("~~~~~~~~~ "+stringList);
            if (stringList.size()==0){
                return;
            }
            stringList.stream().forEach(item->{
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(item);
                count++;
            });
            countDownLatch.countDown();
        }

    }

    public static void main(String[] args) throws InterruptedException {


        List<String> list =new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("xxx_"+i);
        }
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i <10 ; i++) {
            List<String> strings = list.subList(i * 10, i * 10 + 10);
            Future<?> submit = service.submit(new Task(strings, countDownLatch));

        }

        System.out.println("任务提交完成");

        countDownLatch.await();
        System.out.println("count ==>"+count);
    }
}