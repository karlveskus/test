package com.monese.simplebank;

import com.monese.simplebank.model.Account;
import com.monese.simplebank.model.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BankService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account getAccount(String number) {
        return accountRepository.findById(number).orElse(null);
    }

    public List<Account> getAccounts() {
        // TODO should order by account number (descending)
        return accountRepository.findAll();
    }

    public List<Transaction> getTransactionsOnAccount(String number) {
        Account account = new Account();
        account.setNumber(number);
        return transactionRepository.findTransactionByCreditEqualsOrDebitEquals(account, account);
    }

    public Transaction createTransaction(String debitNumber, String creditNumber, BigDecimal amount) {
        // TODO add validations?
        Account debit = getAccount(debitNumber);
        Account credit = getAccount(creditNumber);
        Transaction transaction = new Transaction();
        transaction.setDebit(debit);
        transaction.setCredit(credit);
        transaction.setAmount(amount);
        // TODO currency should be passed as a parameter
        transaction.setCurrency("GBP");
        // TODO should update balances
        return transactionRepository.save(transaction);
    }
}
