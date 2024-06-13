package bank.adapters;

import bank.domain.AccountEntry;
import bank.dto.AccountEntryDTO;

public class AccountEntryAdapter {
    public static AccountEntryDTO accountEntryToAccountEntryDTO(AccountEntry accountEntry) {
        return new AccountEntryDTO(
                accountEntry.getDate(),
                accountEntry.getAmount(),
                accountEntry.getDescription(),
                accountEntry.getFromAccountNumber(),
                accountEntry.getFromPersonName()
        );
    }

    public static AccountEntry accountEntryDTOToAccountEntry(AccountEntryDTO accountEntryDTO) {
        return new AccountEntry(
                accountEntryDTO.getDate(),
                accountEntryDTO.getAmount(),
                accountEntryDTO.getDescription(),
                accountEntryDTO.getFromAccountNumber(),
                accountEntryDTO.getFromPersonName()
        );
    }
}
