package com.example.superb;

import com.example.superb.model.TransferBalance;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

    private BalanceRepository balanceRepository = new BalanceRepository();
    private BankService bankService = new BankService(balanceRepository);

    @Test
    void getBalance() {
       final BigDecimal balance =  bankService.getBalance(1L);
       assertEquals(balance, BigDecimal.TEN);
    }

    @Test
    void addMoney() {
        bankService.addMoney(1L, BigDecimal.ONE);
        assertEquals(balanceRepository.getBalanceForId(1L), new BigDecimal(11));
    }

    @Test
    void makeTransfer() {
        bankService.addMoney(1L, BigDecimal.TEN);
        bankService.addMoney(2L, BigDecimal.ZERO);
        TransferBalance transferBalance = new TransferBalance();
        transferBalance.setTo(2L);
        transferBalance.setFrom(1L);
        transferBalance.setAmount(BigDecimal.ONE);
        bankService.makeTransfer(transferBalance);
        assertEquals(balanceRepository.getBalanceForId(2L), BigDecimal.ONE);
    }
}