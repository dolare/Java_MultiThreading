package base.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class T017LongAdder {
    // LongAdder is better thant AtomicInteger when high concurrency
    // LongAdder use segmental lock

    volatile int count = 0;
    AtomicInteger countAtomic = new AtomicInteger(0);
    LongAdder countAdder = new LongAdder();
    public void m() {
        System.out.println("thread " + Thread.currentThread().getName() + " start!");
        for (int i = 0; i < 10000; i++) {
            count++;
            countAtomic.addAndGet(1);
            countAdder.add(1);
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public static void main(String[] args) {
        T017LongAdder test = new T017LongAdder();
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
        System.out.println("countAdder: " + test.countAdder);
    }

}
