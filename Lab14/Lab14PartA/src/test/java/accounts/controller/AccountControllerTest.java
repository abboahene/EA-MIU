package accounts.controller;


import accounts.TestConfig;
import accounts.service.AccountResponse;
import accounts.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
@Import(TestConfig.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void testGetAccount_existingAccount() throws Exception {
        AccountResponse accountResponse = new AccountResponse("12345", 1000.0, "John Doe");

        when(accountService.getAccount("12345")).thenReturn(accountResponse);

        mockMvc.perform(get("/account/12345"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"accountNumber\":\"12345\",\"balance\":1000.0,\"accountHolder\":\"John Doe\"}"));

        Mockito.verify(accountService, Mockito.times(1)).getAccount("12345");
    }

    @Test
    public void testGetAccount_nonExistingAccount() throws Exception {
        when(accountService.getAccount("12345")).thenReturn(null);

        mockMvc.perform(get("/account/12345"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Account with account number 12345 is not available"));

        Mockito.verify(accountService, Mockito.times(1)).getAccount("12345");
    }

    @Test
    public void testCreateAccount() throws Exception {
        doNothing().when(accountService).createAccount(anyString(), anyDouble(), anyString());

        mockMvc.perform(post("/createaccount/12345/1000.0/John Doe"))
                .andExpect(status().isOk())
                .andExpect(content().string("Account with account number 12345 is created"));

        Mockito.verify(accountService, Mockito.times(1)).createAccount("12345", 1000.0, "John Doe");
    }
}
