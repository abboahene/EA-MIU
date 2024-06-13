package accounts.repository;

import accounts.domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        // Clear the repository before each test
        accountRepository.deleteAll();

        // Add some test data
        accountRepository.save(new Account("488932", 1000.0, "John Doe1"));
        accountRepository.save(new Account("328723", 2000.0, "John Doe2"));
        accountRepository.save(new Account("748837", 3000.0, "John Doe"));
    }

    @Test
    public void testFindByAccountHolder() {
        Account account = accountRepository.findByAccountHolder("John Doe1");
        assertThat(account.getAccountHolder()).isEqualTo("John Doe1");
    }

    @Test
    public void testFindByBalanceGreaterThan() {
        // Test finding accounts with balance greater than a specified amount
        double balance = 1500.0;
        List<Account> accounts = accountRepository.findByBalanceGreaterThan(balance);
        assertThat(accounts).hasSize(2);
        assertThat(accounts).extracting(Account::getAccountHolder)
                .containsExactlyInAnyOrder("John Doe2", "John Doe");
    }

    @Test
    public void testFindByBalanceGreaterThanNotFound() {
        // Test finding accounts with balance greater than a specified amount when no accounts match
        double balance = 4000.0;
        List<Account> accounts = accountRepository.findByBalanceGreaterThan(balance);
        assertThat(accounts).isEmpty();
    }

    @Test
    public void testFindByAccountHolderContaining() {
        // Test finding accounts with account holder names containing a specified string
        String partialName = "Doe";
        List<Account> accounts = accountRepository.findByAccountHolderContaining(partialName);
        assertThat(accounts).hasSize(3);
        assertThat(accounts).extracting(Account::getAccountHolder)
                .containsExactlyInAnyOrder("John Doe1", "John Doe2", "John Doe");
    }

    @Test
    public void testFindByAccountHolderContainingNotFound() {
        // Test finding accounts with account holder names containing a specified string when no accounts match
        String partialName = "NonExisting";
        List<Account> accounts = accountRepository.findByAccountHolderContaining(partialName);
        assertThat(accounts).isEmpty();
    }
}