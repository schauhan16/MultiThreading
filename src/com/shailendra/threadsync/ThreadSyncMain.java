package com.shailendra.threadsync;

import java.util.Scanner;

class Runner implements Runnable {

    /**
     * Since running is a propetry of this class and can be modified in another thread.
     * Some system makes a cache of such property, resulting in an inconsitent behaviour.
     * Adding volatile will mark the compiler to not cache and keep the behaviour consistent.
     */
    public volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Hello World");
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
    
    public void shutdown() {
        this.running = false;
    }
}

public class ThreadSyncMain {
    public static void main(String[] args) {
        Runner runner = new Runner();
        Thread t1 = new Thread(runner);
        t1.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();

        runner.shutdown();
    }
}