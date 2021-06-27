package com.example.lambdaandstreams;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        // Given a list of integers, print square of even integers

        long startTime = System.currentTimeMillis();
        List<Integer> numbers = Arrays.asList(36,1,2,3,4,5,6,7,8,9,10,11,12);
//        Collections.reverse(numbers);

        List<Integer> result = new ArrayList<>();

//        for(Integer num : numbers) {
//            if(num%2==0) {
//                result.add(num*num);
//            }
//        }
//        long endTime = System.currentTimeMillis();

        System.out.println(result);

        Function<Person, String> function = (p1) -> p1.name;

        // streams
//        numbers.stream()
//                .filter(integer -> integer%2==0)
////                .map(abc -> String.valueOf(abc*abc))
//                .collect(Collectors.toList())
//                .forEach(i->System.out.println(i));

//        System.out.println(result);

        numbers.stream()
                .filter(integer -> {
                    System.out.println(integer);
                    return integer % 6 == 0;
                })
                .findFirst();

        System.out.println("!!!!");

        numbers.parallelStream()
                .filter(integer -> {
                    System.out.println(integer);
                    return integer % 6 == 0;
                })
                .findFirst();


//        int[] arr = new int[]{1,2,34,4,5};
//
//        Arrays.stream(arr)




    }
}
