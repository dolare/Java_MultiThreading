package base.thread1;

import java.util.concurrent.TimeUnit;
/*
Question: how to read a balance or always get updated value in multithreading programming?

* If we need synchronized keyword for method getBalance?
* It depends on our business logic, if we allow dirty read.
*
* Case1: without synchronized for method getBalance, if you read it and another thread is modifying the value,
* the balance you get is the old/original balance.
* Case2: with synchrionized for method getBalance, if you read it and another thread is modifying the value,
* you will get the updated value.
*
* Note: with synchronized, the performance will be much worse.
* */
public class T007_Account_WriteRead_Balance {
    String name;
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;

    }

    public synchronized double getBalance() {
        return this.balance;
    }

    public static void main(String[] args) {
        T007_Account_WriteRead_Balance account = new T007_Account_WriteRead_Balance();
        new Thread(() -> account.set("dolare", 100.00)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Balance: " + account.getBalance());

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Balance: " + account.getBalance());
    }
}
