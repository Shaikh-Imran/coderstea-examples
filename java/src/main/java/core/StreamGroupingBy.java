package core;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class StreamGroupingBy {
  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(1, 1, 2, 2, 3, 4, 5, 6, 7, 8, 8, 9, 0);

    // Simplest groupingBy
    System.out.println("Simplest groupingBy");
    Map<String, List<Integer>> oddEvenNumbers = list.stream()
            //grouped with EVEN or ODD
            .collect(groupingBy(n -> n % 2 == 0 ? "EVEN" : "ODD"));
    System.out.println("Even Numbers are : " + oddEvenNumbers.get("EVEN"));
    System.out.println("ODD Numbers are : " + oddEvenNumbers.get("ODD"));

    // group into sets
    System.out.println("group into sets");
    Map<String, Set<Integer>> oddEvenWithSet = list.stream()
            .collect(groupingBy(n -> n % 2 == 0 ? "EVEN" : "ODD", toSet()));
    System.out.println("Even Numbers are : " + oddEvenWithSet.get("EVEN"));
    System.out.println("ODD Numbers are : " + oddEvenWithSet.get("ODD"));

    // sum the numbers
    System.out.println("sum the numbers ");
    Map<String, Integer> sumOddOrEvenSquares = list.stream()
            // lets convert to square
            .map(n -> n * n)
            //grouped with EVEN or ODD
            .collect(groupingBy(n -> n % 2 == 0 ? "EVEN" : "ODD"
                    , Collectors.summingInt(Integer::intValue)
            ));
    System.out.println("Even Numbers squared sum: " + sumOddOrEvenSquares.get("EVEN"));
    System.out.println("ODD Numbers squared sum: " + sumOddOrEvenSquares.get("ODD"));

    // lets take average
    System.out.println("Group Average of the numbers");
    Map<String, Double> averageOfOddEven = list.stream()
            //grouped with EVEN or ODD
            .collect(groupingBy(n -> n % 2 == 0 ? "EVEN" : "ODD"
                    , Collectors.averagingInt(Integer::intValue)
            ));
    System.out.println("Even Numbers average: " + averageOfOddEven.get("EVEN"));
    System.out.println("ODD Numbers average: " + averageOfOddEven.get("ODD"));

    // group again
    System.out.println("Group again with some greater than 5");
    Map<String, Map<String, Set<Integer>>> oddEvenAndCompareTo5 = list.stream()
            .collect(groupingBy(n -> n % 2 == 0 ? "EVEN" : "ODD",
                    groupingBy(n -> n > 5 ? "GT5" : "LT5", toSet())
            ));
    Map<String, Set<Integer>> evenNumbers = oddEvenAndCompareTo5.get("EVEN");
    Map<String, Set<Integer>> oddNumbers = oddEvenAndCompareTo5.get("ODD");

    System.out.println("Even numbers greater than 5: " + evenNumbers.get("GT5"));
    System.out.println("Odd numbers greater than 5: " + oddNumbers.get("GT5"));

    System.out.println("Even numbers Less than 5: " + oddNumbers.get("GT5"));
    System.out.println("Odd numbers Less than or equal to 5: " + oddNumbers.get("LT5"));

  }
}
