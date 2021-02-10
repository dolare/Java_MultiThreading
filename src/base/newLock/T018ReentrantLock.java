package base.newLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T018ReentrantLock {
    /*
    * 锁是可以重入的
    * */
    Lock lock = new ReentrantLock();

    synchronized void m1() {
        System.out.println("m1 start");
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            if (i == 2) m2();
        }
    }

     void m3() {
        System.out.println("m3 start");
        for (int i = 0; i < 10; i++) {
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(i);
            if (i == 2) m2();
        }
    }

    synchronized void m2() {
        System.out.println("m2 start");
    }

    void m4() {
        /*
        * 使用trylock进行尝试锁定，不管锁定与否，方法都将继续执行
        *
        * */
        boolean locked = false;

        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m4 locked: " + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(locked) lock.unlock();
        }
    }

    public static void main(String[] args) {
        T018ReentrantLock test = new T018ReentrantLock();
        new Thread(test::m1, "m1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(test::m2, "m2").start();
        new Thread(test::m3, "m3").start();
        new Thread(test::m4, "m4").start();
    }
}
