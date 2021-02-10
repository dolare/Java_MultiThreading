package base.sync;

import java.util.ArrayList;
import java.util.List;
public class T014VolatileCantReplaceSynchronize {
    /*
    * the count evently is supposed to be 100000,
    * But without synchonized keyword to lock the m function, the count is not what we expect
    * even if we set count as volatile, volatile can make count varaible visible for all threads
    * */
    volatile int count = 0;

    public synchronized void m() {
        System.out.println("thread " + Thread.currentThread().getName() + " start!");
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        T014VolatileCantReplaceSynchronize test = new T014VolatileCantReplaceSynchronize();
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
    }
}
