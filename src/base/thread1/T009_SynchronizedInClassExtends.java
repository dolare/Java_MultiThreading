package base.thread1;

import java.util.concurrent.TimeUnit;

/*
for same object, lock is the same lock, lock is object based. that's why we can do synchronized(this)
Parent and children share same lock
*/
public class T009_SynchronizedInClassExtends {
    synchronized void m () {
        System.out.println("m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        new TT().m();
    }
}

class TT extends T009_SynchronizedInClassExtends {
    @Override
    synchronized void m() {
        System.out.println("child m sart");
        super.m();
        System.out.println("child m end");
    }
}
