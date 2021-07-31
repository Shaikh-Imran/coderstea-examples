package core;

import java.time.*;

public class MilliSecondToLocalDateTime {
  public static void main(String[] args) {
    System.out.println("How to Convert Milliseconds to LocalDateTime in Java");
    System.out.println("By CodersTea.com");

    System.out.println("Example for LocalDateTime");
    millisecondsToAndFromLocalDateTime();

    System.out.println("Example for LocalDate");
    millisecondsToAndFromLocalDate();

  }

  private static void millisecondsToAndFromLocalDateTime() {
    System.out.println("Converting Milliseconds to LocalDateTime");
    long millis = 1614926594000L; // UTC Fri Mar 05 2021 06:43:14
    LocalDateTime dateTime = Instant.ofEpochMilli(millis)
            .atZone(ZoneId.systemDefault()) // default zone
            .toLocalDateTime(); // returns actual LocalDateTime object

    System.out.println("The Millisecond " + millis + " refers to Time " + dateTime);

    LocalDateTime dateTime1 = LocalDateTime.of(2021, 3, 5, 6, 43, 14);
    long millis1 = dateTime1.atZone(ZoneId.systemDefault()) // timezone
            .toInstant() // Instant object
            .toEpochMilli(); // milliseconds

    System.out.println("The milliseconds for date " + dateTime1 + " is " + millis1);
  }

  private static void millisecondsToAndFromLocalDate() {
    System.out.println("Converting Milliseconds to LocalDate");
    long millis = 1614926594000L; // UTC Fri Mar 05 2021 06:43:14
    LocalDate dateTime = Instant.ofEpochMilli(millis)
            .atZone(ZoneId.systemDefault()) // default zone
            .toLocalDate(); // returns actual LocalDate object

    System.out.println("The Millisecond " + millis + " refers to date " + dateTime);

    LocalDate dateTime1 = LocalDate.of(2021, 3, 5);
    long seconds = dateTime1.atStartOfDay(ZoneId.systemDefault())
            .toEpochSecond(); // returns seconds
    long millis1 = seconds * 1000; // seconds to milliseconds

    System.out.println("The milliseconds for date " + dateTime1 + " is " + millis1);
  }
}
