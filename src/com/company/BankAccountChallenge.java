package com.company;

public class BankAccountChallenge {
    static BankAccount jointBankAccount= new BankAccount(1000, "1234");
    public static void main(String[] args) {
        Thread t1= new Thread(new Runnable(){

            @Override
            public void run() {
                jointBankAccount.deposit(300);
                jointBankAccount.withdraw(50);
            }
        });

        Thread t2= new Thread(new Runnable(){

            @Override
            public void run() {
                jointBankAccount.deposit(203.75);
                jointBankAccount.withdraw(100);
            }
        });

        t1.start();
        t2.start();

    }
}
