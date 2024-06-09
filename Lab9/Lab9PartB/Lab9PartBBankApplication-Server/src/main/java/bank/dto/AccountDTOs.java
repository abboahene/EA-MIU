package bank.dto;

import java.util.Collection;

public class AccountDTOs {

    private Collection<AccountDTO> accounts;

    public AccountDTOs(Collection<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public Collection<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<AccountDTO> accounts) {
        this.accounts = accounts;
    }
}
