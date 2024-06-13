package bank.service;

import bank.adapters.AccountAdapter;
import bank.domain.Account;
import bank.domain.Customer;
import bank.dto.AccountDTO;
import bank.dto.AccountDTOs;
import bank.integration.jms.JMSSender;
import bank.integration.logging.Logger;
import bank.repository.AccountRepository;
import bank.service.events.AccountChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	private final AccountRepository accountRepository;
	private final CurrencyConverter currencyConverter;
	private final JMSSender jmsSender;
	private final Logger logger;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository, CurrencyConverter currencyConverter, JMSSender jmsSender, Logger logger) {
		this.accountRepository = accountRepository;
		this.currencyConverter= currencyConverter;
		this.jmsSender =  jmsSender;
		this.logger = logger;
	}

	public AccountDTO createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountRepository.save(account);
		logger.log("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return AccountAdapter.accountToAccountDTO(account);
	}

	public void deposit(long accountNumber, double amount) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		account.deposit(amount);
		accountRepository.save(account);
		String message = "deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount;
		logger.log(message);
		publisher.publishEvent(new AccountChangeEvent(message, accountNumber, amount));
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public AccountDTO getAccount(long accountNumber) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		return AccountAdapter.accountToAccountDTO(account);
	}

	public AccountDTOs getAllAccounts() {
		return AccountAdapter.AccountsToAccountDTOs(accountRepository.findAll());
	}

	public void withdraw(long accountNumber, double amount) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		account.withdraw(amount);
		accountRepository.save(account);
		String message = "withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount;
		logger.log(message);
		publisher.publishEvent(new AccountChangeEvent(message, accountNumber, amount));
	}

	public void depositEuros(long accountNumber, double amount) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountRepository.save(account);
		String message = "depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount;
		logger.log(message);
		publisher.publishEvent(new AccountChangeEvent(message, accountNumber, amount));
		if (amountDollars > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
	}

	public void withdrawEuros(long accountNumber, double amount) {
		Account account = accountRepository.findByAccountNumber(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountRepository.save(account);
		String message = "withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount;
		logger.log(message);
		publisher.publishEvent(new AccountChangeEvent(message, accountNumber, amount));

	}

	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
		Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		String message = "transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description;
		logger.log(message);
		publisher.publishEvent(new AccountChangeEvent(message, fromAccountNumber, amount));
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
		}
	}

}
