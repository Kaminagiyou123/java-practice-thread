package com.company;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;


import static com.company.ProducerConsumer3.EOF;

public class ProducerConsumer3 {
    public static final String EOF="EOF";
    public static void main(String[] args) {
        ArrayBlockingQueue <String> b= new ArrayBlockingQueue<String>(6);

        ExecutorService executorService= Executors.newFixedThreadPool(5);
        MyProducer2 producer= new MyProducer2(b,ThreadColor.ANSI_GREEN);
        MyConsumer2 consumer1=new MyConsumer2(b,ThreadColor.ANSI_PURPLE);
        MyConsumer2 consumer2= new MyConsumer2(b, ThreadColor.ANSI_CYAN);
        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        Future<String> future= executorService.submit(new Callable<String>(){

            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_GREEN+"I'am being printed for the Callable");
                return "This is the callable result";
            }
        });
        try{
            System.out.println(future.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

    }
}

class MyProducer2 implements Runnable{
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyProducer2(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer =  buffer;
        this.color = color;

    }
    @Override
    public void run() {
        Random random= new Random();
        String[] nums={"1","2","3","4","5"};
        for (String num:nums){
                try {
                    System.out.println(color + "adding " + num);
                    buffer.put(num);
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println(color + "Adding EOF and Exiting");

            buffer.put("EOF");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyConsumer2 implements Runnable{
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyConsumer2(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
        }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer){
            try {
                if (buffer.isEmpty()) {
                    continue;
                }
                if (buffer.peek().equals(EOF)) {
                    System.out.println(color + "Exiting");
                    break;
                } else {
                    System.out.println(color + "removed " + buffer.take());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }}
    }
}

