package com.shailendra.threadcreation;

public class ThreadCreationByAnonClass{
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable(){
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Hello " + i);
                    try{
                        Thread.sleep(100);
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });

        t1.start();
    }
}