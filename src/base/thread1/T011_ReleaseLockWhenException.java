package base.thread1;

import java.util.concurrent.TimeUnit;

/*
*Lock will be released when exception happen,
* t2 can access the object/method to modify count value, because the lock is released in the thread t1.
*
* Because while(true)， if there is no exception, t2 won't execute in this program
* */
public class T011_ReleaseLockWhenException {
    int count = 0;
    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start");
        while (true) {
            count++;

            System.out.println(Thread.currentThread().getName() + " count = " + count);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count == 5) {
                int i = 1/0; // lock will be released from here
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        T011_ReleaseLockWhenException test = new T011_ReleaseLockWhenException();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                test.m();
            }
        };

        new Thread(r, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r, "t2").start();
    }
}
