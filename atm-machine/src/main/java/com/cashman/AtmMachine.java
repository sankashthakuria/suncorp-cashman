package com.cashman;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Created by @author Sankash on 6/3/2019 */
public class AtmMachine {

  private TreeMap<Currency, Integer> cash = new TreeMap<>();
  private int total;

  public AtmMachine() {
    Stream.of(Currency.values()).forEach(p -> cash.put(p, 0));
    total = 0;
  }

  public TreeMap<Currency, Integer> getCash() {
    return cash;
  }

  public void addCurrency(Currency currency, int number) {
    Integer integer = cash.get(currency);
    cash.put(currency, integer + number);
    total += currency.getValue() * number;
  }

  public void removeCurrency(Currency currency, int number) throws AtmException {
    Integer integer = cash.get(currency);
    if (integer >= number) {
      cash.put(currency, integer - number);
      total -= currency.getValue() * number;
    } else {
      throw new AtmException(
          "Cannot remove "
              + currency
              + " since there are "
              + integer
              + " notes of "
              + currency
              + " avaialbe in the ATM");
    }
  }

  public int getBalance() {
    return total;
  }

  public List<Integer> withdraw(int amount) throws AtmException {
    if (amount > total)
      throw new AtmException(
          "Cannot dispense" + amount + " since it exceeds total cash available in the ATM");
    List<Integer> dispensedNotes = new ArrayList<>();
    LinkedList<Integer> notes = new LinkedList<>();
    int[] currencyDenominations =
        Arrays.stream(cash.keySet().toArray(new Currency[0]))
            .mapToInt(Currency::getValue)
            .toArray();
    int[] denominationCount =
        Arrays.stream(cash.values().toArray(new Integer[0])).mapToInt(Integer::intValue).toArray();

    computeNoteCombinations(
        0, amount, currencyDenominations, denominationCount, dispensedNotes, notes);

    if (dispensedNotes.isEmpty()) {
      throw new AtmException("Amount cannot be dispensed");
    }

    updateCashAfterDisbursal(dispensedNotes);
    return dispensedNotes;
  }

  private void updateCashAfterDisbursal(List<Integer> dispensedNotes) {
    Map<Currency, Long> dispensedCurrency =
        dispensedNotes.stream()
            .map(Currency::fromInt)
            .collect(Collectors.groupingBy((Function.identity()), Collectors.counting()));
    dispensedCurrency.forEach(
        (key, value) -> {
          try {
            removeCurrency(key, Math.toIntExact(value));
          } catch (AtmException e) {
            throw new RuntimeException(e);
          }
        });
  }

  private void computeNoteCombinations(
      int pos,
      int change,
      int[] values,
      int[] available,
      List<Integer> dispensedNotes,
      LinkedList<Integer> notes) {
    if (change == 0) {
      if (dispensedNotes.isEmpty() || dispensedNotes.size() < notes.size()) {
        dispensedNotes.clear();
        dispensedNotes.addAll(notes);
      }
    } else if (change < 0) {
      return;
    }

    for (int i = pos; i < values.length && values[i] <= change; i++) {
      if (available[i] > 0) {
        available[i]--;
        notes.addLast(values[i]);
        computeNoteCombinations(i, change - values[i], values, available, dispensedNotes, notes);
        notes.removeLast();
        available[i]++;
      }
    }
  }
}
