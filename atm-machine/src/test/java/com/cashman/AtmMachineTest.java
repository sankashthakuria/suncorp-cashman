package com.cashman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
/** Created by @author Sankash on 6/5/2019 */
class AtmMachineTest {

  private AtmMachine atmMachine;

  void setupCurrency() {
    atmMachine.addCurrency(Currency.TWENTY, 8);
    atmMachine.addCurrency(Currency.FIFTY, 3);
  }

  AtmMachine setUpAtmMachine() {
    AtmMachine machine = new AtmMachine();
    machine.addCurrency(Currency.TWENTY, 8);
    machine.addCurrency(Currency.FIFTY, 3);
    return machine;
  }

  @BeforeEach
  void setup() {
    atmMachine = new AtmMachine();
  }

  @Test
  void given_TWENTY_8_FIFTY_3_when_addCurrency_called_should_set_atm_state_correctly() {
    atmMachine.addCurrency(Currency.TWENTY, 8);
    atmMachine.addCurrency(Currency.FIFTY, 3);
    assertEquals(8, atmMachine.getCash().get(Currency.TWENTY));
    assertEquals(3, atmMachine.getCash().get(Currency.FIFTY));
  }

  @Test
  void given_TWENTY_8_FIFTY_3_when_addCurrency_called_should_set_total_to_310() {
    atmMachine.addCurrency(Currency.TWENTY, 8);
    atmMachine.addCurrency(Currency.FIFTY, 3);
    assertEquals(310, atmMachine.getBalance());
  }

  @Test
  void given_TWENTY_8_FIFTY_3_when_removeCurrency_called_with_TWENTY_2_should_set_atm_state()
      throws AtmException {
    setupCurrency();
    atmMachine.removeCurrency(Currency.TWENTY, 2);
    assertEquals(6, atmMachine.getCash().get(Currency.TWENTY));
    assertEquals(3, atmMachine.getCash().get(Currency.FIFTY));
  }

  @Test
  void given_TWENTY_8_FIFTY_3_when_removeCurrency_called_with_TWENTY_1_should_set_balance_to_290()
      throws AtmException {
    setupCurrency();
    atmMachine.removeCurrency(Currency.TWENTY, 1);
    assertEquals(290, atmMachine.getBalance());
  }

  @Test
  void given_TWENTY_8_FIFTY_3_when_removeCurrency_called_with_TWENTY_9_should_throw_exception()
      throws AtmException {
    setupCurrency();
    assertThrows(AtmException.class, () -> atmMachine.removeCurrency(Currency.TWENTY, 9));
  }

  @Test
  void given_setupCurrency_when_withdraw_20_should_succeed() throws AtmException {
    assertEquals(Arrays.asList(20), setUpAtmMachine().withdraw(20));
  }

  @Test
  void given_setupCurrency_when_withdraw_40_should_succeed() throws AtmException {
    assertEquals(Arrays.asList(20, 20), setUpAtmMachine().withdraw(40));
  }

  @Test
  void given_setupCurrency_when_withdraw_50_should_succeed() throws AtmException {
    assertEquals(Arrays.asList(50), setUpAtmMachine().withdraw(50));
  }

  @Test
  void given_setupCurrency_when_withdraw_70_should_succeed() throws AtmException {
    assertEquals(Arrays.asList(20, 50), setUpAtmMachine().withdraw(70));
  }

  @Test
  void given_setupCurrency_when_withdraw_80_should_succeed() throws AtmException {
    assertEquals(Arrays.asList(20, 20, 20, 20), setUpAtmMachine().withdraw(80));
  }

  @Test
  void given_setupCurrency_when_withdraw_100_should_succeed() throws AtmException {
    assertEquals(Arrays.asList(20, 20, 20, 20, 20), setUpAtmMachine().withdraw(100));
  }

  @Test
  void given_setupCurrency_when_withdraw_150_should_succeed() throws AtmException {
    assertEquals(Arrays.asList(20, 20, 20, 20, 20, 50), setUpAtmMachine().withdraw(150));
  }

  @Test
  void given_setupCurrency_when_withdraw_60_should_succeed() throws AtmException {
    assertEquals(Arrays.asList(20, 20, 20), setUpAtmMachine().withdraw(60));
  }

  @Test
  void given_setupCurrency_when_withdraw_110_should_succeed() throws AtmException {
    assertEquals(Arrays.asList(20, 20, 20, 50), setUpAtmMachine().withdraw(110));
  }

  @Test
  void given_setupCurrency_when_withdraw_200_should_succeed() throws AtmException {
    assertEquals(Arrays.asList(20, 20, 20, 20, 20, 50, 50), setUpAtmMachine().withdraw(200));
  }

  @Test
  void given_setupCurrency_when_withdraw_30_should_throw_exception() {
    assertThrows(AtmException.class, () -> setUpAtmMachine().withdraw(30));
  }
}
