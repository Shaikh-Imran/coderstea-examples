package mulicupteas.reflectionapi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TheMagicOfReflectionApi {

    public static void main(String[] args) throws Exception {
        obtainTheClassObject();
        constructor();
        _Field();
        _Method();
    }

    static void obtainTheClassObject() {
        Class<Customer> viaClassName = Customer.class;
        System.out.println("Via class name " + viaClassName.getName());

        Class<? extends Customer> viaObject = new Customer().getClass();
        System.out.println("Via object " + viaObject.getName());

    }

    static void constructor() throws Exception {
        Constructor<Customer> customerConstructor = Customer.class.getConstructor();

        Customer cu = customerConstructor.newInstance();
        System.out.println("No parameterized constructors name will be null: " + cu.getName());

        Constructor<Customer> parameterizedConstructor = Customer.class
                .getDeclaredConstructor(String.class, int.class);
        System.out.println("Count of parameters : " + parameterizedConstructor.getParameterCount());

        Customer detailedCustomer = parameterizedConstructor.newInstance("Coders Tea", 1);
        System.out.println("Lets here it for the : " + detailedCustomer);
    }

    static void _Field() throws Exception {
        Customer customer = new Customer("Field", 2);

        Field nameField = customer.getClass().getDeclaredField("name");
        boolean isNameAccessible = nameField.isAccessible();
        nameField.setAccessible(true);

        Field idField = customer.getClass().getDeclaredField("id");
        boolean isIdAccessible = idField.isAccessible();
        idField.setAccessible(true);

        System.out.println("Name is : " + nameField.get(customer));
        System.out.println("id is " + idField.getInt(customer));

        nameField.set(customer, "New Name");
        System.out.println("new Name" +
                " is : " + nameField.get(customer));

        //make sure to reset accessibility
        nameField.setAccessible(isNameAccessible);
        idField.setAccessible(isIdAccessible);

        //to access all the fields
        for (Field field : Customer.class.getDeclaredFields()) {
            boolean isFieldAccessible = field.isAccessible();
            System.out.println("Field being access is : " + field.getName() + " with value : " + field.get(customer));

            field.setAccessible(isFieldAccessible);
        }

    }

    static void _Method() throws Exception {
        Customer customer = new Customer();
        Method setIdMethod = customer.getClass().getMethod("setId");
        setIdMethod.invoke(customer, 2);
        System.out.println("Parameters count is " + setIdMethod.getParameterCount());

        Method getIdMethod = customer.getClass().getMethod("getId");
        int id = (int) getIdMethod.invoke(customer);
        System.out.println("id is " + id);

    }
}

class Customer {
    private String name;
    private int id;

    public Customer(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Customer() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}