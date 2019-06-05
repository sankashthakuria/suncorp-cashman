package com.cashman;

import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powermock.reflect.Whitebox;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by @author Sankash on 6/5/2019
 */
class DriverTest {

  private Driver driver;

  @BeforeEach
  void setup() {
    driver = new Driver();
  }

  @Test
  public void isValid_method_should_return_true_when_key_exists_in_given_array() throws Exception {
    boolean result = Whitebox.invokeMethod(driver, "isValid", new int[] {1, 2, 3, 4, 5},5);
    assertTrue(result);
  }

  @Test
  public void isValid_method_should_return_throws_exception_when_key_not_in_given_array() throws Exception {
    assertThrows(InputMismatchException.class,()-> Whitebox.invokeMethod(driver, "isValid", new int[] {1, 2, 3, 4, 5},9));
  }

  @Test
  public void test_add_currency() {
    String input = "1 20 1 y 4 q";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Scanner scanner = new Scanner(System.in);
    driver = new Driver(scanner);
    driver.start();
    AtmMachine atmMachine = Whitebox.getInternalState(driver, "atmMachine");
    assertEquals(20,atmMachine.getBalance());
  }

  @Test
  public void test_remove_currency() {
    String input = "1 20 1 y 2 20 1 4 q";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Scanner scanner = new Scanner(System.in);
    driver = new Driver(scanner);
    driver.start();
    AtmMachine atmMachine = Whitebox.getInternalState(driver, "atmMachine");
    assertEquals(0,atmMachine.getBalance());
  }

  @Test
  public void test_withdraw_currency() {
    String input = "1 20 5 y 3 20 4 q";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Scanner scanner = new Scanner(System.in);
    driver = new Driver(scanner);
    driver.start();
    AtmMachine atmMachine = Whitebox.getInternalState(driver, "atmMachine");
    assertEquals(80,atmMachine.getBalance());
  }

  @Test
  public void test_denomination_balance() {
    String input = "1 20 5 y 3 20 5 q";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Scanner scanner = new Scanner(System.in);
    driver = new Driver(scanner);
    driver.start();
    AtmMachine atmMachine = Whitebox.getInternalState(driver, "atmMachine");
    assertEquals(4,atmMachine.getCash().get(Currency.TWENTY));
    assertEquals(0,atmMachine.getCash().get(Currency.FIFTY));
  }
}