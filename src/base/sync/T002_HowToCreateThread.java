package base.sync;

public class T002_HowToCreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello myThread");
        }
    }
    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello myRun");
        }
    }

    public static void main(String[] args) {
        new MyThread().start(); // approach 1
        new Thread(new MyRun()).start(); // approach 2
        new Thread(() -> {
            System.out.println("hello lambda"); // approach 3
        }).start();
    }
}
