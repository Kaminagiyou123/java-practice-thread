package com.company;

import static com.company.ThreadColor.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(ANSI_PURPLE+"Hello from Thread.");
        Thread anotherThread= new AnotherThread();
        anotherThread.setName("==Another Thread==");
        //one instance
        anotherThread.start();
        //another instance

        new Thread(){
            public void run(){
                System.out.println(ANSI_GREEN+"Hello from anonymous thread 2");
            }
        }.start();

        Thread myRunableThread=new Thread(new MyRunable(){
            @Override
            public void run() {
                System.out.println(ANSI_RED+"Hello from the anonymous class's implementation of run()");
                try{
                    anotherThread.join(2000);
                    System.out.println(ANSI_RED+"Another thread terminated or timed out, so I am running again");
                } catch (InterruptedException e) {
                    System.out.println(ANSI_RED+"I couldnt wait after all. I was interrupted");

                }
            }
        });
        myRunableThread.start();
        System.out.println(ANSI_PURPLE+"Hello again from the main thread.");


    }
}
