/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.multithreads.join;

import static java.lang.Thread.sleep;

/**
 *
 * @author mazexiang
 * @version $Id: WorkThread.java, v 0.1 2018年12月24日 14:20 mazexiang Exp $
 */
public class WorkThread  implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread(). getName() + "run start.");
            //模拟完成子任务执行的时间
            sleep(1000);
            System.out.println(Thread.currentThread(). getName() + "run finished.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}