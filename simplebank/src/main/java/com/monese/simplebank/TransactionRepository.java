package com.monese.simplebank;

import com.monese.simplebank.model.Account;
import com.monese.simplebank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findTransactionByCreditEqualsOrDebitEquals(Account credit, Account debit);

}
