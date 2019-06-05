package com.cashman;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/** Created by @author Sankash on 6/4/2019 */
public class Driver {

  private Scanner scanner;
  private AtmMachine atmMachine;

  public Driver(Scanner scanner){
    this.scanner = scanner;
    this.atmMachine = new AtmMachine();
  }

  public Driver() {
    this.scanner = new Scanner(System.in);
    this.atmMachine = new AtmMachine();
  }

  private int parseUserInput(MenuOptions m) throws InputMismatchException {
    int temp = Integer.parseInt(scanner.next());
    isValid(m.myArray, temp);
    return temp;
  }

  private boolean isValid(final int[] arr, final int key) throws InputMismatchException {
    boolean valid = Arrays.stream(arr).anyMatch(i -> i == key);
    if (!valid) throw new InputMismatchException("Choice must be -> " + Arrays.toString(arr));
    return true;
  }

  private int showMainMenu() {
    System.out.println(
        "Enter a choice\n"
            + "1 -> Add cash\n"
            + "2 -> Remove cash\n"
            + "3 -> Withdraw cash\n"
            + "4 -> Get cash balance\n"
            + "5 -> Get denominations balance\n");
    return parseUserInput(MenuOptions.MAIN);
  }

  private void showAddCashMenu() {
    System.out.println("Add denomination\n" + "TWENTY -> 20\n" + "FIFTY -> 50\n");
    System.out.println("Enter denomination\n");
    int denomination = parseUserInput(MenuOptions.ADD);
    System.out.println("Enter the number of notes for the chosen denomination");
    int denominationCount = Integer.parseInt(scanner.next());
    Currency currency = Currency.fromInt(denomination);
    atmMachine.addCurrency(currency, denominationCount);
  }

  private void showRemoveCashMenu() {
    System.out.println("Remove denomination\n" + "TWENTY -> 20\n" + "FIFTY -> 50\n");
    System.out.println("Enter denomination\n");
    int denomination = parseUserInput(MenuOptions.REMOVE);
    System.out.println("Enter the number of notes for the chosen denomination");
    int denominationCount = Integer.parseInt(scanner.next());
    try {
      atmMachine.removeCurrency(Currency.fromInt(denomination), denominationCount);
    } catch (AtmException e) {
      System.out.println(e.getMessage());
    }
  }

  private void showGetBalanceCashMenu() {
    System.out.println("Get Balance\n");
    int balance = atmMachine.getBalance();
    System.out.println("Total balance left in the ATM is " + balance);
  }

  private void showWithdrawCashMenu() {
    System.out.println("Withdraw Cash\n");
    int withdrawAmount = Integer.parseInt(scanner.next());
    List<Integer> notesDispensed;
    try {
      notesDispensed = atmMachine.withdraw(withdrawAmount);
      System.out.println(
          "The ATM dispensed the following notes for your request " + notesDispensed);
    } catch (AtmException e) {
      System.out.println(e.getMessage());
    }
  }

  private boolean isQuit() {
    System.out.println(
        "Enter (Y/y) to go back to main menu - Press any other character to quit.....");
    return (!scanner.next().equalsIgnoreCase("Y"));
  }

  private void showCurrentDenominations() {
    atmMachine
        .getCash()
        .entrySet()
        .forEach(e -> System.out.println("Demonination:" + e.getKey() + " Count:" + e.getValue()));
  }

  private void launch() {
    int userSelection = showMainMenu();
    switch (userSelection) {
      case 1:
        showAddCashMenu();
        break;
      case 2:
        showRemoveCashMenu();
        break;
      case 3:
        showWithdrawCashMenu();
        break;
      case 4:
        showGetBalanceCashMenu();
        break;
      case 5:
        showCurrentDenominations();
        break;
    }
  }

  public void start() {
    while (true) {
      try {
        launch();
        if (isQuit()) break;
      } catch (InputMismatchException | IllegalArgumentException e) {
        System.out.println("Invalid choice -  resuming from beginning. " + e.getMessage());
      }
    }
  }

  private enum MenuOptions {
    MAIN(new int[] {1, 2, 3, 4, 5}),
    ADD(new int[] {20, 50}),
    REMOVE(new int[] {20, 50});

    private final int[] myArray;

    MenuOptions(int[] myArray) {
      this.myArray = myArray;
    }
  }
}
