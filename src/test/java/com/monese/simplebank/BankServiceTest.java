package com.monese.simplebank;

import com.monese.simplebank.model.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {

    @InjectMocks
    BankService bankService;
    @Mock
    AccountRepository accountRepository;
    @Mock
    TransactionRepository transactionRepository;

    @Test
    void createTransaction() {
        Account debit = anAccount(BigDecimal.valueOf(6));
        Mockito.when(accountRepository.findById("123")).thenReturn(Optional.of(debit));

        Throwable thrown = Assertions.catchThrowable(() -> bankService.createTransaction(
                "123",
                "credit",
                BigDecimal.TEN)
        );

        Assertions.assertThat(thrown)
                .hasMessage("Not enough funds on debit 123 account");
    }

    private Account anAccount(BigDecimal balance) {
        Account account = new Account();
        account.setBalance(balance);
        return account;
    }

}
