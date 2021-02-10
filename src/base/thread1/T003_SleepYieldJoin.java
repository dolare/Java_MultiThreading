package base.thread1;

public class T003_SleepYieldJoin {
    static void testSleep() {
        new Thread( () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A->" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A->" + i);
                if (i % 10 == 0) Thread.yield();
            }
        }).start();
    }

    static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A->" + i);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join(); // t2 join t1, so t2 will continue run after t1 finished
                System.out.println("T2"); // wait for t1 end
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        // testSleep();
        // testYield();
        // testJoin();
    }
}

// question: how can we have t2 run after t1 and have t3 run after t2 -> using join
