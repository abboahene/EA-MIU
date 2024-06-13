package bank.service.kafka;

import kafka.dto.CreateAccountDTO;
import bank.service.AccountService;
import kafka.dto.DepositWithdrawDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    @Autowired
    AccountService accountService;
    @KafkaListener(topics = {"createAccount"})
    public void createAccount(CreateAccountDTO payload) {
        accountService.createAccount(payload.getAccountNumber(), payload.getCustomerName());
    }
    @KafkaListener(topics = {"deposit"})
    public void deposit(DepositWithdrawDTO payload) {
        accountService.deposit(payload.getAccountNumber(), payload.getAmount());
    }
    @KafkaListener(topics = {"withdraw"})
    public void withdraw(DepositWithdrawDTO payload) {
        accountService.withdraw(payload.getAccountNumber(), payload.getAmount());
    }
}