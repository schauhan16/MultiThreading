package coms.shailendra.reentrantlock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int count = 0;

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            this.count++;
        }
    }

    private void firstThread() throws InterruptedException {
        lock.lock();
        System.out.println("Waiting...");
        condition.await();
        System.out.println("Woken up!");
        try {
            increment();
        }
        finally {
            lock.unlock();
        }
    }

    private void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();

        System.out.println("Press return key!!!");
        
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();

        System.out.println("Got return key!");

        condition.signal();
        try {
            increment();
        }
        finally {
            lock.unlock();
        }
    }
    
    private void finished() {
        System.out.println("Count is: " + this.count);
    }

    public static void main(String[] args) throws InterruptedException{
        ReentrantLockExample example = new ReentrantLockExample();
        Thread t1 = new Thread(new Runnable(){

          public void run() {
            try {
              example.firstThread();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });

        Thread t2 = new Thread(new Runnable(){

          public void run() {
            try {
              example.secondThread();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        
        example.finished();
    }

}