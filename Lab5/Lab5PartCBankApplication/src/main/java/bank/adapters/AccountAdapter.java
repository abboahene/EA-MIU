package bank.adapters;

import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.domain.Customer;
import bank.dto.AccountDTO;
import bank.dto.AccountEntryDTO;
import bank.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static bank.adapters.CustomerAdapter.customerDTOToCustomer;
import static bank.adapters.CustomerAdapter.customerToCustomerDTO;

public class AccountAdapter {
    public static AccountDTO accountToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setCustomer(customerToCustomerDTO(account.getCustomer()));
        accountDTO.setEntryList(account.getEntryList().stream()
                .map(AccountEntryAdapter::accountEntryToAccountEntryDTO)
                .collect(Collectors.toList()));
        return accountDTO;
    }

    public static Account accountDTOToAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setCustomer(customerDTOToCustomer(accountDTO.getCustomer()));
        account.setEntryList(accountDTO.getEntryList().stream()
                .map(AccountEntryAdapter::accountEntryDTOToAccountEntry)
                .collect(Collectors.toList()));
        return account;
    }

    public static List<AccountDTO> AccountsToAccountDTOs(List<Account> accounts){
        List<AccountDTO> accountDTOs = new ArrayList<>();
        for (Account account: accounts){
            accountDTOs.add(accountToAccountDTO(account));
        }
        return accountDTOs;
    }

    public static List<Account> accountDTOsToAccounts(List<AccountDTO> accountDTOs){
        List<Account> accounts = new ArrayList<>();
        for (AccountDTO accountDTO: accountDTOs){
            accounts.add(accountDTOToAccount(accountDTO));
        }
        return accounts;
    }

}
