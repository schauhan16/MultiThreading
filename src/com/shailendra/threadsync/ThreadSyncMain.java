package com.shailendra.threadsync;

import java.util.Scanner;

class Runner implements Runnable {
    public boolean running = true;

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