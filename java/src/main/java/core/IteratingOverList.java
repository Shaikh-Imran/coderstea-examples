package core;


import java.util.*;

public class IteratingOverList {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 6, 7, 8, 10);

        // basic for loop
        for (int i = 0; i < list.size(); i++) {
            int data = list.get(i);
            System.out.println("Data at index " + i + " is " + data);
        }

        // enhanced for-loop
        for (Integer data : list) {
            System.out.println("Data is " + data + " and it is already given to you in variable");
        }

        // while-loop
        int i = 0;
        while (i < list.size()) {
            int data = list.get(i);
            System.out.println("Data at index " + i + " is " + data);
            i++;
        }

        // Iterator
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("Data is " + iterator.next());
        }

        // ListIterator
        ListIterator<Integer> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            System.out.println("Data is " + listIterator.next());
            listIterator.set(listIterator.next() * 100);
        }
        System.out.println("After modifying via ListIterator ...");
        while (listIterator.hasNext()) {
            System.out.println("Data is " + listIterator.next());
        }

        // forEach
        list.forEach(e -> System.out.println("Data is " + e));
        // you can use the method reference to print the value only
        System.out.println("With method reference on for-each");
        list.forEach(System.out::println);

        // stream api
        // for each with stream
        list.stream().forEach(System.out::println);

        // using intermediate function in between
        Optional<Integer> sum = list.stream()
                .filter(e -> e % 2 == 0) // only even number
                .map(e -> e * 2) // multiplying each even number by 2
                .reduce((e, acc) -> e + acc);// summing up all the data
        System.out.println("Sum of even number is " + sum.get());

    }
}
