package base.concurrency;

import java.util.concurrent.Semaphore;

public class T022Semaphore {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(1);
        Semaphore s1 = new Semaphore(2, true);
        // how many threads allows to execute at same time
        // 可用于限流， traffic limilation-----Traffic shaping (also known as packet shaping)

        new Thread(() -> {
            try {
                s.acquire(); // permit - 1
                System.out.println("T1 starting");
                Thread.sleep(200);
                System.out.println("T1 Running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release(); // permit + 1
            }
        }).start();

        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("T2 starting");
                Thread.sleep(200);
                System.out.println("T2 Running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();
    }
}
