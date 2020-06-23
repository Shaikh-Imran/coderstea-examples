package core;

import lombok.Builder;
import lombok.Data;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalClass {
    public static void main(String[] args) throws Exception {
        Optional<String> nonEmptyOptional = Optional.of("Not null");// non empty optional
        Optional<String> nullObject = Optional.ofNullable(null); // empty optional
        Optional<String> emptyOptional = Optional.empty(); //empty optional


        if (nonEmptyOptional.isPresent()) {
            System.out.println("The value " + nonEmptyOptional.get());
        }
        if (!nullObject.isPresent()) {
            System.out.println("No value is not present");
        }

        Optional<String> codersTea = Optional.of("CodersTea.com");
        nonEmptyOptional.ifPresent(site -> System.out.println("World's best website is " + site));
        Optional<String> empty = Optional.empty();
        String name = empty.orElseGet(() -> printAndReturnDefaultName()); //Default Name

        String name1 = Optional.of("CodersTea").orElseGet(() -> printAndReturnDefaultName()); // CodersTea
        String name2 = Optional.of("SuperHero").orElseGet(OptionalClass::printAndReturnDefaultName); //with method reference

        Optional.empty().orElseThrow(() -> new Exception("Provide me a Name, man"));
        Optional.ofNullable(null).orElseThrow(FileNotFoundException::new);




    }


    static String printAndReturnDefaultName() {
        System.out.println("Default Name");
        return "Default Name";
    }

}

//lombok
@Data
@Builder
class Employee{
    private String name;
    private int age;
    private boolean isManager;
    private List<Integer> tasksIds;

    public static void main(String[] args) throws Exception {
        //get the total task numbers if employee age gt 23
        Employee employee = Employee.builder()
                .age(23).name("Bob").isManager(false)
                .tasksIds(Arrays.asList(1,2,3,4))
                .build();

        int empGt20TaskCount = Optional.of(employee)
                .filter(e -> e.getAge() > 20)
                .map(Employee::getTasksIds)
                .map(List::size)
                .orElse(-1);
        System.out.println("Employee with age over 20 and its task count is " + empGt20TaskCount);


        //manager
        Employee manager = Employee.builder()
                .age(23).name("Bob").isManager(true)
                .tasksIds(Arrays.asList(1,2,3,4))
                .build();

        Optional.of(manager)
                .filter(e -> e.getAge() > 20)
                .filter(Employee::isManager)
                .map(Employee::getTasksIds)
                .map(List::size)
                .ifPresent(tasks -> System.out.println("Manger is having" + tasks + " tasks"));

        //if employee is not manager then throw error
        Optional.of(employee).filter(Employee::isManager).orElseThrow(() -> new Exception("IT must be manager"));
    }

}