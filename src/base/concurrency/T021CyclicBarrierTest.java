package base.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T021CyclicBarrierTest {
    // CyclicBarrier will run when the number of threads reach the number define in barrier's construction
    public static void main(String[] args) {
//        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("thread reach 20, go ahead!"));
        CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("thread reach 20, go ahead!");
            }
        });

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " started");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
