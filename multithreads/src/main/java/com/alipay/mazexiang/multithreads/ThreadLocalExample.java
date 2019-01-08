/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.multithreads;

/**
 *
 * @author mazexiang
 * @version $Id: ThreadLocalExample.java, v 0.1 2018Äê12ÔÂ19ÈÕ 20:57 mazexiang Exp $
 */
public class ThreadLocalExample {
    public static class MyRunnable implements Runnable {
        private ThreadLocal threadLocal = new ThreadLocal();
        @Override
        public void run() {
            threadLocal.set((int) (Math.random() * 100D));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(threadLocal.get());
        }
    }
    public static void main(String[] args) {
        MyRunnable sharedRunnableInstance = new MyRunnable();
        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);
        thread1.start();
        thread2.start();

    }

}