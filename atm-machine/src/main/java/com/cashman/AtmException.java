package com.cashman;

/** Created by @author Sankash on 6/4/2019 */
public class AtmException extends Exception {
  public AtmException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }

  public AtmException(String errorMessage) {
    super(errorMessage);
  }
}
