package com.monese.simplebank;

import com.monese.simplebank.model.Account;
import com.monese.simplebank.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BankServiceIntegrationTest extends TestBase {

    @Resource
    BankService bankService;

    @Resource
    MockMvc mockMvc;

    @Resource
    AccountRepository accountRepository;

    @Resource
    TransactionRepository transactionRepository;

    @Test
    void getAccounts() {
        List<Account> accounts = bankService.getAccounts();

        assertThat(accounts).flatExtracting(Account::getNumber)
                .containsExactly("3141", "1415", "2718");
    }

    @Test
    void createTransaction() throws Exception {
        assertThat(transactionRepository.findAll()).isEmpty();

        mockMvc.perform(post("/transaction/create")
                .param("debitNumber", "3141")
                .param("creditNumber", "1415")
                .param("amount", "31.51"))
                .andExpect(status().isOk());

        Transaction transaction = transactionRepository.findAll().iterator().next();
        assertThat(transaction.getAmount()).isEqualByComparingTo(BigDecimal.valueOf(31.51));
        assertThat(accountRepository.findAll())
                .extracting(Account::getNumber, Account::getBalance)
                .contains(
                        tuple("1415", new BigDecimal("11.51")),
                        tuple("3141", new BigDecimal("268.49"))
                );
    }
}
