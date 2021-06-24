package com.monese.simplebank;

import com.monese.simplebank.model.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class BankServiceIntegrationTest extends TestBase {

    @Resource
    BankService bankService;

    @Test
    void getAccounts() {
        List<Account> accounts = bankService.getAccounts();

        Assertions.assertThat(accounts).flatExtracting(Account::getNumber)
                .containsExactly("3141", "1415", "2718");
    }

}
