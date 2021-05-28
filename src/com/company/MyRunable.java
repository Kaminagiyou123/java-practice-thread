package com.company;

import static com.company.ThreadColor.ANSI_RED;

public class MyRunable implements Runnable{

    @Override
    public void run() {
        System.out.println(ANSI_RED+"Hello from myRunable class");
    }
}
