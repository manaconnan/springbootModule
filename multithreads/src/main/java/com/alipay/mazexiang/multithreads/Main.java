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
 * @version $Id: Main.java, v 0.1 2018��12��19�� 20:49 mazexiang Exp $
 */
public class Main {
    public static void main(String[] args) throws Exception {

        LinkedBlockingQueue queue = new LinkedBlockingQueue(5);
        //�����б�����ʱ�ľܾ�����
        RejectedExecutionHandler handler = new RejectedExecutionHandler(){

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("�ܾ������� ==>"+r.toString());
            }
        };
        ExecutorService service = new ThreadPoolExecutor(1,1,10, TimeUnit.SECONDS,queue,handler);


        //�̳߳ؾ�����Ҫ��Executors��������Ϊ���ĵȴ����в����Ƶ�
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
            // ģ������
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
            // ģ������
            list.stream().forEach(System.out::println);
        }
    }

}