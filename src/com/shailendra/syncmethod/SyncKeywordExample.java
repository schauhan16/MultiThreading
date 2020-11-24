package com.shailendra.syncmethod;

public class SyncKeywordExample {
    private int count = 0;

    private synchronized void increment() {
        this.count++;
    }

    private void doWork() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println(count);
    }
    public static void main(String[] args) {
        SyncKeywordExample syncKeywordExample = new SyncKeywordExample();
        syncKeywordExample.doWork();
    }
    
}