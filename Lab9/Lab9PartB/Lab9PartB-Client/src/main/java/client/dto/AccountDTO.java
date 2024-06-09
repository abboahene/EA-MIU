package client.dto;

import java.util.ArrayList;
import java.util.Collection;

public class AccountDTO {

    private long accountNumber;
    private CustomerDTO customer;

    private Collection<AccountEntryDTO> entryList;

    public AccountDTO() {}

    public AccountDTO(long accountNumber, CustomerDTO customer) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.entryList = new ArrayList<>();
    }

    public AccountDTO(long accountNumber, CustomerDTO customer, Collection<AccountEntryDTO> entryList) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.entryList = entryList;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public double getBalance() {
        double balance=0;
        for (AccountEntryDTO entry : entryList) {
            balance+=entry.getAmount();
        }
        return balance;
    }


    public Collection<AccountEntryDTO> getEntryList() {
        return entryList;
    }

    public void setEntryList(Collection<AccountEntryDTO> entryList) {
        this.entryList = entryList;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountNumber=" + accountNumber +
                ", customer=" + customer +
                ", entryList=" + entryList +
                '}';
    }
}
