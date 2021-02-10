package base.sync;

public class T015LockToSmalllOrBig {
    int a;
    /*in this case we only lock a = a + 1*/
    public void m1() {

        // do a lot of things

        synchronized (this) {
            a = a + 1;
        }

        // do a lot of things
    }

    /* use synchronized in higher layer when there are too many calculation we need to lock entire method*/
    public synchronized void m2() {

        a = a + 1;
        a = a + 1;
        a = a + 1;

    }
}
