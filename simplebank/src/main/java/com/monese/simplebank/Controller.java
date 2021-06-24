package com.monese.simplebank;

import com.monese.simplebank.model.Account;
import com.monese.simplebank.model.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class Controller {

    private final BankService bankService;

    public Controller(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/account/number/{number}")
    public Account getAccount(@PathVariable String number) {
        return bankService.getAccount(number);
    }

    @GetMapping("/account/all")
    public List<Account> getAccounts() {
        return bankService.getAccounts();
    }

    @GetMapping("/transaction/account/{number}")
    public List<Transaction> getTransactions(@PathVariable String number) {
        return bankService.getTransactionsOnAccount(number);
    }

    @PostMapping("/transaction/create")
    public Transaction createTransaction(
            @RequestParam String debitNumber,
            @RequestParam String creditNumber,
            @RequestParam BigDecimal amount
            ) {
        return bankService.createTransaction(debitNumber, creditNumber, amount);
    }

}
