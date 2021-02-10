package base.thread1;

public class T005_WhyNeedLock_Synchronized {
    // two thread want to do a = a + 1,  note: need to read before do + operation
    public volatile int count = 10;
    public static int count1 = 10;
    public static Object o = new Object();
    // synchronized must lock an object
    public void test1() {
        synchronized(o) { // synchronized lock an object
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public void test2() {
        synchronized(this) {// synchronized lock an object
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public synchronized static void test3() { // use static, synchronized method, lock this class/object, synchronized(T.class)
        count1--;
        System.out.println(Thread.currentThread().getName() + " count = " + count1);
    }

    public static void main(String[] args) {
        T005_WhyNeedLock_Synchronized test = new T005_WhyNeedLock_Synchronized();

        for (int i = 0; i < 100; i++) {
            Thread t = new Thread() {
                @Override
                public synchronized void run() {
                    test.count = test.count - 1;
                }
            };
            t.start();
            System.out.println("thread: " + test.count);
            // we have to add valitile or sychornized keyword to make sure get right count number
        }
    }
}

