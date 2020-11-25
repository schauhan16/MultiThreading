package com.shailendra.waitnotify;

import java.util.Scanner;

class Processor {

    public void producer() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread running....");
            wait();
            System.out.println("Resumed");
        }
    }

    public void consumer() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        
        synchronized (this) {
            System.out.println("Press return key....");
            scanner.nextLine();
            System.out.println("Thread released!!!");
            notify();
            Thread.sleep(5000);
        }
    }
}

public class WaitNotifyExample {
    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();
        Thread t1 = new Thread(new Runnable(){

          public void run() {
            try {
              processor.producer();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });

        Thread t2 = new Thread(new Runnable(){

          public void run() {
            try {
              processor.consumer();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}