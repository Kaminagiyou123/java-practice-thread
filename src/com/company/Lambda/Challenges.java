package com.company.Lambda;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

public class Challenges {
    public static void main(String[] args) {
//        new Thread(() -> {
//            String s = "Let's split this up into an array";
//            Arrays.stream(s.split(" ")).forEach(System.out::println);
//
//        }).start();

        Function<String, String>lambdaFunction = s -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(s.charAt(i));
                }
            }
            return returnVal.toString();
        };
        System.out.println(everySecondCharacter(lambdaFunction,"1234567890"));

        System.out.println(lambdaFunction.apply("1234567890"));

        Supplier<String> iLoveJava=()-> "I love Java";
        String supplierResult= iLoveJava.get();
        System.out.println(supplierResult);


    }

    public static String everySecondCharacter(Function<String,String> func,String s){
        return func.apply(s);
    }
}

