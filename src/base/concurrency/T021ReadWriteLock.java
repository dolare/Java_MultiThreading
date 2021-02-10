package base.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T021ReadWriteLock {
    static Lock lock = new ReentrantLock();
    private static int value;

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v) {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("write over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        /*
        * 1. when we use regular reentrantLock, write will wait for all read finished, because the same lock will only release
        * after all read completed.
        * 2. when use read Lock and write lock, multi threads can read/access the same lock
        * 3. write lock will not execute when there is read or other write operation.
        * */
        for (int i = 0; i < 18; i++) {
            // new Thread(() -> read(lock)).start();
            new Thread(() -> read(readLock)).start();
        }
        for (int i = 0; i < 2; i++) {
            int n = i;
            // new Thread(() -> write(lock, n)).start();
            new Thread(() -> write(writeLock, n)).start();
        }
    }
}
