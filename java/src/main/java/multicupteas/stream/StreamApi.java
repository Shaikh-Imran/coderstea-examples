package multicupteas.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApi {
    static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    public static void main(String[] args) {
        //start the stream
        list.stream()
                // multiplying each number by 2
                .map(n -> n * 2)
                //taking forward only even numbers
                .filter(n -> n % 2 == 0)
                //printing each even number
                .forEach(n -> System.out.println(n));

        map();
        filter();
        collect();
        terminalOperation();
    }

    private static void terminalOperation() {
        long evenAndLt5Count = list.stream()
                // even numbers less than 5s
                .filter(n -> n < 5 && n % 2 == 0)
                .count();
        System.out.println(evenAndLt5Count);

        list.stream()
                // square of each number
                .map(n -> n * n)
                .forEach(n -> System.out.print(" " + n));

    }

    static void map() {
        list.stream()
                // 1. multiplying each number by 2
                .map(n -> n * 2)
                // 2. Converting to string.
                .map(n -> "CodersTea.com-Post No : " + n)
                //3. Now you will be working on the
                //stream of String
                //4. we add another string to the previous
                .map(string -> string + ". Another String")
                //print the end result
                .forEach(System.out::println);
    }

    private static void filter() {
        long count = list.stream()
                // 1. Need only even numbers
                .filter(n -> n % 2 == 0)
                // 2. Checking if the Number is
                // greater than 5
                .filter(n -> n > 5)
                //3. multiplying the number by 5
                // just to show how we can use multiple
                // Intermediate operation together
                .map(n -> n * 5)
                // 4. Any number less than 50
                .filter(n -> n < 50)
                // Counting how much elements
                // survived teh pipeline
                .count();
        System.out.println("Total " + count + " numbers survived the storm");

    }

    private static void collect() {
        List<Integer> processedList = list.stream()
                // multiplying by 5
                .map(n -> n * 5)
                // numbers less than 30
                .filter(n -> n < 30)
                //add  +2
                .map(n -> n + 2)
                // give the list of processed numbers
                .collect(Collectors.toList());
        System.out.println("processed List is \n " + processedList);
    }

}
