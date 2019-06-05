package com.cashman;

import java.util.HashMap;
import java.util.Map;

/** Created by @author Sankash on 6/3/2019 */
public enum Currency {
  TWENTY(20),
  FIFTY(50);
  private static final Map<Integer, Currency> intToTypeMap = new HashMap<>();

  static {
    for (Currency type : Currency.values()) {
      intToTypeMap.put(type.value, type);
    }
  }

  private int value;

  Currency(int value) {
    this.value = value;
  }

  public static Currency fromInt(int i) {
    return intToTypeMap.get(i);
  }

  public int getValue() {
    return value;
  }
}
