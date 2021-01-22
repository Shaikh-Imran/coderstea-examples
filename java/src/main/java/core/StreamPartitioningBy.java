package core;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamPartitioningBy {
  public static void main(String[] args) {
    System.out.println("How to use partitionBy of Stream API");

    List<Integer> numberList = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    // partitioningBy even numbers
    // true sublist will be even numbers and false will be odd numbers
    Map<Boolean, List<Integer>> evenNumberMap = numberList.stream()
            .collect(Collectors.partitioningBy(num -> num % 2 == 0));
    System.out.println("The Even Numbers are " + evenNumberMap.get(true));

    // partitioningBy numbers greater than and less than 5
    Map<Boolean, List<Integer>> gt5Map = numberList.stream()
            .collect(Collectors.partitioningBy(num -> num >= 5));
    System.out.println("The Numbers Greater than or equal to 5 are " + gt5Map.get(true));
    System.out.println("The Numbers Less than 5 are " + gt5Map.get(false));

    // partitioningBy odd and even with their average
    Map<Boolean, Double> oddEvenAverage = numberList.stream()
            .collect(Collectors.partitioningBy(
                    num -> num % 2 != 0,
                    Collectors.averagingInt(Integer::intValue)
            ));
    System.out.println("Odd numbers average is " + oddEvenAverage.get(true));
    System.out.println("Even numbers average is " + oddEvenAverage.get(false));


    // partitioningBy odd even with Set
    Map<Boolean, Set<Integer>> oddEvenSet = numberList.stream()
            .collect(Collectors.partitioningBy(
                    num -> num % 2 != 0,
                    Collectors.toSet()
            ));

    // partitioningBy greater or equal to 5 and then
    // partitioningBy even and odd
    Map<Boolean, Map<Boolean, List<Integer>>> gte5EvenOddMap = numberList.stream()
            .collect(Collectors.partitioningBy(
                    num -> num >= 5,
                    Collectors.partitioningBy(
                            num -> num % 2 == 0
                            //again can add more partitioningBy...
                    )
            ));

    System.out.println("Odd numbers Greater than 5  are "
            + gte5EvenOddMap.get(true).get(false));
    System.out.println("Even numbers Greater than 5  are "
            + gte5EvenOddMap.get(true).get(true));
    System.out.println("Even numbers Less than 5  are "
            + gte5EvenOddMap.get(false).get(true));


  }
}
