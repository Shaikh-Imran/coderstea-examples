package designpattern;

public class SingletonObject {
  private static SingletonObject singletonObject;
  private static int count = 0;

  // private constructor
  private SingletonObject() {
  }

  public static SingletonObject getInstance() {
    // if the object already is already created, we will return that.
    if (singletonObject != null) {
      return singletonObject;
    }
    // create a new object as its not created already
    singletonObject = new SingletonObject();
    return singletonObject;
  }

  // increment count by 1 and return the count
  public int incrementAndGetCount() {
    count += 1;
    return count;
  }

  public static void main(String[] args) {
    int testCount = 0;
    SingletonObject instance1 = SingletonObject.getInstance();
    testCount = instance1.incrementAndGetCount(); // count=1
    testCount = instance1.incrementAndGetCount(); // count=2

    System.out.println("The testCount should be 2 " + (testCount == 2));

    int testCount2 = 0;
    // this will return the same object as instance1
    SingletonObject instance2 = SingletonObject.getInstance();
    testCount2 = instance2.incrementAndGetCount(); // count=3
    testCount2 = instance2.incrementAndGetCount(); // count=4

    System.out.println("The testCount2 should be 4 " + (testCount2 == 4));
  }
}

