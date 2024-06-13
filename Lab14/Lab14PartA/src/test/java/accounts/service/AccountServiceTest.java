package accounts.service;

import accounts.TestConfig;
import accounts.domain.Account;
import accounts.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Import(TestConfig.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void testGetAccount_existingAccount() {
        // Given
        Account account = new Account("12345", 1000.0, "John Doe");
        when(accountRepository.findById("12345")).thenReturn(Optional.of(account));
        AccountResponse expectedResponse = new AccountResponse("12345", 1000.0, "John Doe");

        try (MockedStatic<AccountAdapter> mockedStatic = Mockito.mockStatic(AccountAdapter.class)) {
            mockedStatic.when(() -> AccountAdapter.getAccountResponse(account)).thenReturn(expectedResponse);

            // When
            AccountResponse accountResponse = accountService.getAccount("12345");

            // Then
            assertThat(accountResponse).isNotNull();
            assertThat(accountResponse.getAccountNumber()).isEqualTo("12345");
            verify(accountRepository, times(1)).findById("12345");
        }
    }

    @Test
    public void testGetAccount_nonExistingAccount() {
        // Given
        when(accountRepository.findById("12345")).thenReturn(Optional.empty());

        // When
        AccountResponse accountResponse = accountService.getAccount("12345");

        // Then
        assertThat(accountResponse).isNull();
        verify(accountRepository, times(1)).findById("12345");
    }

    @Test
    public void testCreateAccount() {
        // Given
        Account account = new Account("12345", 1000.0, "John Doe");
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // When
        accountService.createAccount("12345", 1000.0, "John Doe");

        // Then
        verify(accountRepository, times(1)).save(any(Account.class));
    }
}