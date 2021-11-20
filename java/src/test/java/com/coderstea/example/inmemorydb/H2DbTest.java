package com.coderstea.example.inmemorydb;

import howto.SimpleDatabaseOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class H2DbTest {

  @Test
  void getCountOfUsersFromDb() {
    SimpleDatabaseOperation obj = new SimpleDatabaseOperation();
    String jdbcUrl = "jdbc:h2:~/db;MODE=Mysql;INIT=RUNSCRIPT FROM 'classpath:employee.sql'";

    int actualEmpCount = obj.getCountOfUsersFromDb(jdbcUrl, "", "");
    Assertions.assertEquals(2, actualEmpCount);
  }
}