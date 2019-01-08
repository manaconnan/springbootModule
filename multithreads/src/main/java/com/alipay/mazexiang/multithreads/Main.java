/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.multithreads;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author mazexiang
 * @version $Id: Main.java, v 0.1 2018年12月19日 20:49 mazexiang Exp $
 */
public class Main {
    public static void main(String[] args) throws Exception {

        LinkedBlockingQueue queue = new LinkedBlockingQueue(5);
        //当队列被打满时的拒绝策略
        RejectedExecutionHandler handler = new RejectedExecutionHandler(){

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("拒绝了任务 ==>"+r.toString());
            }
        };
        ExecutorService service = new ThreadPoolExecutor(1,1,10, TimeUnit.SECONDS,queue,handler);


        //线程池尽量不要用Executors创建。因为他的等待队列不限制的
        ExecutorService service2 = Executors.newFixedThreadPool(5);

        List<String> list = new ArrayList<>();
        for (int i = 0; i <51 ; i++) {

            list.add("==>"+i);
            if (i %5 ==0){
                service.execute(new MyTask01(list));
                list.clear();
            }
            //do other thing

        }
        Thread.sleep(10000);
    }

    @Right
    public static class MyTask01 implements Runnable {

        private  List<String> list = new ArrayList<>();
        public MyTask01(List<String> list){
            this.list.addAll(list);
        }
        public void run() {
            System.out.println("~~~~~~~~~~~~~~~~~");
            // 模拟消费
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.stream().forEach(System.out::println);
        }
    }

    @Wrong
    public static class MyTask02 implements Runnable {

        private  List<String> list = new ArrayList<>();
        public MyTask02(List<String> list){
            this.list=list;
        }
        public void run() {
            System.out.println("~~~~~~~~~~~~~~~~~");
            // 模拟消费
            list.stream().forEach(System.out::println);
        }
    }

}