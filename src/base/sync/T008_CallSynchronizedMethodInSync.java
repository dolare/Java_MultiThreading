package base.sync;

import java.util.concurrent.TimeUnit;

/*
* We allow m1 call m2 when they are both synchronized in one thread, 可重入 ReentrantLock
*
* */
public class T008_CallSynchronizedMethodInSync {
    synchronized void m1 () {
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 End");
    }
    synchronized void m2 () {
        System.out.println("m2 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 End");
    }

    public static void main(String[] args) {
        T008_CallSynchronizedMethodInSync test = new T008_CallSynchronizedMethodInSync();
        Thread t1 = new Thread(() -> {
            test.m1();
        });
        t1.start();
    }

}
