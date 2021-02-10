package base.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T016AtomicInteger {
    /*
    *
    * Using AtomicInteger can help you keep the count value thrad safe without sychronize or lock a method
    *
    * Compare And Set (CAS) this is how AtomicInteger is safe,
    * cas(v, expected, newValue). <- unsafe class - allocate and free memory directly
    *
    * ABA 问题， for premitive variable, there is no difference,
    * for Object, we can add version attribute
    *
    * Atomic is faster than Synchronized
    * */
    volatile int count = 0;
    AtomicInteger countAtomic = new AtomicInteger(0);
    public void m() {
        System.out.println("thread " + Thread.currentThread().getName() + " start!");
        for (int i = 0; i < 10000; i++) {
            count++;
            countAtomic.addAndGet(1);
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public static void main(String[] args) {
        T016AtomicInteger test = new T016AtomicInteger();
        List<Thread> threadList = new ArrayList();
        for(int i = 0; i < 10; i++) {
            Thread thread = new Thread(test::m, "thread-" + i);
            threadList.add(thread);
        }

        threadList.forEach((o) -> {
            o.start();
        });

        threadList.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("count: " +test.count);
        System.out.println("countAtomic: " + test.countAtomic);
    }
}
