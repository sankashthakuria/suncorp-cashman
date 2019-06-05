package com.cashman;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Created by @author Sankash on 6/3/2019 */
public class App {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    Runnable task = () -> new Driver().start();
    executorService.execute(task);
    executorService.shutdown();
  }
}
