package com.shailendra.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Runner {
    private Account account1 =  new Account();
    private Account account2 = new Account();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    /**
     * 
     * @param firstLock
     * @param secondLock
     * @throws InterruptedException
     * 
     * This method will take care of the sequencing of lock. It will return once both the locks are aquired.
     * Consumer of this method will not be resposible for the sequence of lock acquisition.
     */
    private void accquireLock(Lock firstLock, Lock secondLock) throws InterruptedException {
        boolean gotFirstLock = false;
        boolean gotSecondLock = false;
        while (true) {
            try {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            } finally {
                if (gotFirstLock && gotSecondLock) {
                    return;
                } else if (gotFirstLock) {
                    firstLock.unlock();
                } else if (gotSecondLock) {
                    secondLock.unlock();
                }
            }
            Thread.sleep(1);
        }
    }

    public void firstThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            accquireLock(lock1, lock2);
            try {
                Account.transfer(account1, account2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            accquireLock(lock2, lock1);
            try {
                Account.transfer(account2, account1, random.nextInt(100));
            } finally {
                lock2.unlock();
                lock1.unlock();
            }
        }
    }

    public void finished() {
        System.out.println("Accout 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());
        System.out.println("Total balance: " + (account1.getBalance() + account2.getBalance()));
    }
    
}
public class DeadlockExample {
    public static void main(String[] args) throws InterruptedException{
        Runner runner = new Runner();
        Thread t1 = new Thread(new Runnable(){

          public void run() {
            try {
              runner.firstThread();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });

        Thread t2 = new Thread(new Runnable(){

          public void run() {
            try {
              runner.secondThread();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        
        runner.finished();
    }
}