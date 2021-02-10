package base.sync;

import java.util.concurrent.TimeUnit;

public class T013_Singleton_DoubleCheckLock_Volatile {
    public static void main(String[] args) {
        //
    }
}

class SingletonExample {
    private static /* volatile */SingletonExample instance;
    private SingletonExample () {};
    public static SingletonExample getInstanceNotSafe() {
        if (instance == null) { // thread not safe, it is possible multiple thread see instance == null at same time
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            instance = new SingletonExample();
        }
        return instance;
    }

    public synchronized static SingletonExample getInstanceSafe() {
        if (instance == null) { // thread safe, but sometimes we don't want to synchronize the entire method
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            instance = new SingletonExample();
        }
        return instance;
    }

    public static SingletonExample getInstanceNotSafe1() {
        if (instance == null) { // it is possible multiple threads alrady checked instance == null when the lock created
            synchronized(SingletonExample.class) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                instance = new SingletonExample();
            }
        }
        return instance;
    }

    public static SingletonExample getInstanceSafe1() {
        if (instance == null) {
            // Thread Safe, not 100% safe -> double check, if the qps is
            // if we want 100% safe, we need to use volatile keyword for instance variable to in case instruction/command reordering)
            synchronized(SingletonExample.class) {
                if (instance == null) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new SingletonExample();
                }
            }
        }
        return instance;
    }
}

class SingleTonExample1 {
    // lazy singleton,
    // weekness: need to create singleTon instance first before using it
    // advantage: simple and lazy
    private static class InstanceHolder {
        private  static final SingleTonExample1 INSTANCE = new SingleTonExample1();
    }

    private static final SingleTonExample1 INSTANCE = new SingleTonExample1();

    public static SingleTonExample1 getInstanceFromInnerClass() {
        return InstanceHolder.INSTANCE;
    }

    public static SingleTonExample1 getInstance() {
        return INSTANCE;
    }
}