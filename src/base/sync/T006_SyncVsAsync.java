package base.sync;

public class T006_SyncVsAsync {
    public synchronized void s1() {
        System.out.println(Thread.currentThread().getName() + " s1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " s1 ended");
    }

    public void s2 () {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " s2 start");
    }

    public static void main(String[] args) {
        T006_SyncVsAsync test = new T006_SyncVsAsync();

        new Thread(test::s1, "s1").start();
        new Thread(test::s2, "s2").start();
    }
}
