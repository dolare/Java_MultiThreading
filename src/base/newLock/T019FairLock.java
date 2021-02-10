package base.newLock;

import java.util.concurrent.locks.ReentrantLock;

public class T019FairLock extends Thread{
    private static ReentrantLock lock = new ReentrantLock(true);
    // when we use fair lock, it can have thread 1 got lock before next thread 0 got lock.
    // unfair lock -> thread 0 will get all locks first and then thread 1 will get lock
    // synchronized only have unfair lock

    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " got lock");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T019FairLock test = new T019FairLock();
        T019FairLock test1 = new T019FairLock();
        test.start();
        test1.start();
    }
}
