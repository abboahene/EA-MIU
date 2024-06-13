package bank.controller;

import bank.dto.AccountDTO;
import bank.dto.AccountDTOs;
import bank.dto.DepositWithdrawDTO;
import bank.dto.TransferFundsDTO;
import bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO body){
        AccountDTO account = accountService.createAccount(body.getAccountNumber(), body.getCustomer().getName());
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("/deposit")
    public ResponseEntity<AccountDTO> deposit(@RequestBody DepositWithdrawDTO body){
        accountService.deposit(body.getAccountNumber(), body.getAmount());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/deposit-euros")
    public ResponseEntity<AccountDTO> depositEuros(@RequestBody DepositWithdrawDTO body){
        accountService.depositEuros(body.getAccountNumber(), body.getAmount());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/withdraw-euros")
    public ResponseEntity<AccountDTO> withdrawEuros(@RequestBody DepositWithdrawDTO body){
        accountService.withdrawEuros(body.getAccountNumber(), body.getAmount());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/transfer-funds")
    public ResponseEntity<AccountDTO> transferFunds(@RequestBody TransferFundsDTO body){
        accountService.transferFunds(body.getFromAccountNumber(),
                body.getToAccountNumber(),
                body.getAmount(),
                body.getDescription());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<AccountDTOs> getAllAccounts(){
        AccountDTOs accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

}
