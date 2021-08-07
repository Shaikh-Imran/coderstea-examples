package com.coderstea.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class JUnit5ExampleTest {
  @Test
  void yourFirstTest() {
    int a = 5;
    int b = 10;
    assertEquals(15, a + b);
  }

  @Test
  void thisTestWillRun() {
    assertEquals(15, 12 + 3);
  }

  void thisWillNotRun() {
    assertEquals(15, 12 + 3);
  }

  @Test
  void assertUsingLambda() {
    assertTrue(5 > 1, () -> "This message will be lazily loaded");
  }

  @BeforeAll
  static void itWillRunBeforeAllTests() {
    System.out.println("This will run exactly once before all test cases");
  }

  @BeforeEach
  void itWillRunBeforeEachTests() {
    System.out.println("This will run before each test cases");
  }

  @AfterAll
  static void itWillRunAfterAllTests() {
    System.out.println("This will run exactly once after all test cases");
  }

  @AfterEach
  void itWillRunAfterEachTests() {
    System.out.println("This will run after each test cases");
  }

  @Test
  @DisplayName("Test Divide ✨🎠. It should return 1 if we pass 0 as divider.")
  void divideTest_WhenDividerIs0_ShouldReturn1() {
    assertEquals(1, divide(11, 0));
    assertEquals(2, divide(16, 8));
  }

  int divide(int number, int divider) {
    if (divider == 0) return 1;
    return number / divider;
  }

  @Test
  void testEnvVariable() {
    System.out.println("Checking if CODERSTEA variable is set in system environment");

    assumeTrue(System.getenv().containsKey("CODERSTEA"),
            "You must set CODERSTEA in your system env variable.");
    System.out.println("This won't run until you set CODERSTEA in you system");
  }
}