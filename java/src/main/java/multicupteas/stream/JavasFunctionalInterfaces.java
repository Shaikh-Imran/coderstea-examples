package multicupteas.stream;

import java.util.function.*;

public class JavasFunctionalInterfaces {
    public static void main(String[] args) {
        fi_Consumer();
        fi_Predicate();
        fi_Function();
        fi_BiFuntion();
        fi_Supplier();
    }


    static void fi_Consumer() {
        //define the consumer which consumes the age to print it
        Consumer<Integer> printAgeConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer age) {
                System.out.println("Age is " + age);
            }
        };

        //call the method
        printAgeConsumer.accept(23);// Age is 23

        //using lambda
        Consumer<Integer> printAgeWithLamda = (age) -> System.out.println("Lamdda : age is " + age);
        //will work similar as printAgeConsumer
        printAgeWithLamda.accept(223);//Lamda : age is 223

        //chaining with andThen(Consumer)
        printAgeConsumer //1st
                .andThen(printAgeWithLamda)//2nd
                .andThen(age -> System.out.println("How old is he ? " + age))//3rd
                .accept(23);//this value will be given to each consumer
    }

    static void fi_Predicate() {
        Predicate<Integer> isEven = new Predicate<Integer>() {
            @Override
            public boolean test(Integer number) {
                return number % 2 == 0;
            }
        };
        boolean is23Even = isEven.test(23);//false

        //using lambda
        Predicate<Integer> isEvenlambda = (number) -> number % 2 == 0;
        boolean is46Even = isEvenlambda.test(46);//true

        //chaining with and()
        //returns true only of all the predicated returns true else false
        isEven // 1st - number should be even
                .and(n -> n < 10)//2nd -number should be less than 10
                .and(n -> n > 5) // 3rd should be greater than 5
                .test(6); // this nu,ber will be used in chain
        // returns true after completion
    }

    static void fi_Function() {
        Function<Integer, Integer> doubleTheNumber = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer number) {
                return number * 2;
            }
        };
        int doubleTheNumber2 = doubleTheNumber.apply(2);//4
        //using lambda
        Function<Integer, Integer> subtract2 = (number) -> number - 2;
        int subtract2From4 = doubleTheNumber.apply(4);
        System.out.println(subtract2From4);

        //chaining with andThen()
        //Unlike Predicate and Consumer which uses the same value to all the nodes,
        //Function uses the result of previous Function
        doubleTheNumber // 1st will double the number
                .andThen(subtract2) // 2nd will subtract 2 from doubled number
                .andThen(doubleTheNumber) // 3rd subtracted result will be doubled
                .apply(4); // (((4 * 2) - 2) * 2) = 12
    }

    static void fi_BiFuntion() {
        BiFunction<Integer, Integer, Integer> areaOfRectangle = new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer length, Integer breadth) {
                return length * breadth;
            }
        };

        areaOfRectangle.apply(2, 2);//4

        BiFunction<Integer, Integer, String> areaWitMessage = (lengh, breadth) -> "Area is " + lengh * breadth;

        areaWitMessage.apply(4, 3);// Area is 12

        //chaining
        areaOfRectangle // 1st area will be calculated
                .andThen(area -> area * 3) // 2nd calculated area will be multiplied by 3 as height
                .apply(2, 3);// 2 * 3 * 3 = 18
    }

    static void fi_Supplier() {
        Supplier<Double> randomSupplier = new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        };
        double randomNumber = randomSupplier.get();
        System.out.println(randomNumber);

        //lambda
        Supplier<String> codersTeaUrl = () -> "https://coderstea.com";
        System.out.println(codersTeaUrl.get());
    }
}

