package com.company.Lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {
        List<String> someBingoNumbers = Arrays.asList(
                "N40", "N36", "B12", "B6", "G53", "G49", "G30", "G50", "g34",
                "I26", "I17", "I29",
                "O71");
        List<String> gNumbers = new ArrayList<>();

        someBingoNumbers.forEach(number -> {
            if (number.toUpperCase().startsWith("G")) {
                gNumbers.add(number);
//                System.out.println(number);
            }
        });

//        gNumbers.sort((String s1,String s2)->s1.compareTo(s2));
//        gNumbers.forEach(n-> System.out.println(n));

        someBingoNumbers.stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);

        Stream<String> ioNumberStream = Stream.of("I26", "I17", "21", "71");
        Stream<String> inNumberStream = Stream.of("N40", "N36", "B12", "B6", "G53", "G49", "G30", "G50", "g34",
                "I26", "I17", "I29",
                "O71");
        Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream);
        System.out.println("=====");

        System.out.println(concatStream.distinct().peek(System.out::println).count());

        Employee john= new Employee("John Doe", 30);
        Employee jack= new Employee("Jack Deer", 24);
        Employee jane= new Employee("Jane Hill", 40);
        Employee snow= new Employee("Snow White", 20);

        Department hr= new Department("Human Resources");
        hr.addEmployees(jane);
        hr.addEmployees(jack);
        hr.addEmployees(snow);

        Department accounting= new Department("Accounting");
        accounting.addEmployees(john);

        List<Department> departments=new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);

        departments.stream().flatMap(d->d.getEmployees().stream())
                .forEach(System.out::println);

        List<String> sortedGNumbers=someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s->s.startsWith("G"))
                .sorted()
                .collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
        for (String s:sortedGNumbers){
            System.out.println(s);
        }
        departments.stream().flatMap(deparment->deparment.getEmployees().stream()).reduce((e1,e2)->e1.getAge()<e2.getAge()?e1:e2)
                .ifPresent(System.out::println);

        Stream.of("ABC","AC","A","CCC")
                .filter(s-> s.length()<3)
                .count();



    }
}