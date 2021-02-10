package base.concurrency;

import java.util.concurrent.Exchanger;

public class T023Exchanger {

    /*
    *
    * there are two slots for exchanger, 只能两个线程之间交换*/
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }).start();

        new Thread(() -> {
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }).start();

//        new Thread(() -> {
//            String s = "T3";
//            try {
//                s = exchanger.exchange(s);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + " " + s);
//        }).start();
    }
}
