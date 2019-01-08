/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.multithreads.join;

import static java.lang.Thread.sleep;

/**
 *
 * @author mazexiang
 * @version $Id: WorkThread.java, v 0.1 2018��12��24�� 14:20 mazexiang Exp $
 */
public class WorkThread  implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread(). getName() + "run start.");
            //ģ�����������ִ�е�ʱ��
            sleep(1000);
            System.out.println(Thread.currentThread(). getName() + "run finished.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}