package bank.crons;

import bank.dto.AccountDTOs;
import bank.integration.logging.Logger;
import bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankStatementPrinter {
    @Autowired
    AccountService accountService;

    @Scheduled(fixedRate = 5000)
    public void printAccounts(){
        AccountDTOs accountDTOList = accountService.getAllAccounts();
        System.out.println(accountDTOList);
    }
}
