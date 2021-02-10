package base.thread1;

import java.util.concurrent.TimeUnit;

public class T012_Volatile {
/*
* 1. 保证线程可见性
* 2. 禁止指令重排序
*
* 1. Heap is shared by threads, if we have a varaible like running in heap,
* threads copy running's value to their own memory block, so each thread can't see the value change from other varaible.
*
* Adding volatile can make this variable visible to all threads.
*
* 2. sometimes, java rerange the commands in order to avoid CPU idle
* if we have volatile, it will tell java not to rerrange the order of code running.
*
* Eg: DCL - double checked locking with 单例 singleton
*
* */
    public volatile boolean running = true; // changeable

    void m() {
        System.out.println("m start");
        while (running) {

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        T012_Volatile test = new T012_Volatile();

        new Thread(test::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test.running = false;
    }
}
