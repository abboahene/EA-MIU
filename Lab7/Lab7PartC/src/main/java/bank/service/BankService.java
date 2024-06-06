package bank.service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.domain.Tracerecord;
import bank.integration.EmailSender;
import bank.repository.AccountRepository;
import bank.repository.CustomerRepository;
import bank.repository.TracerecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TracerecordRepository tracerecordRepository;

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private TracerecordService tracerecordService;


	@Transactional
	public void createCustomerAndAccount(int customerId, String customerName, String emailAddress, String AccountNumber){
		try {
			Account account = new Account(AccountNumber);
			accountRepository.save(account);
			Customer customer = new Customer(customerId, customerName);
			customer.setAccount(account);
			customerRepository.saveCustomer(customer);
			emailSender.sendEmail(emailAddress, "Welcome " + customerName);
			tracerecordService.save(new Tracerecord("Customer "+ customerName +" created with account " + AccountNumber));
		}catch (RuntimeException e){
			System.out.println("We could not create your account "+ AccountNumber);
			tracerecordService.save(new Tracerecord("Could not create customer "+ customerName +"with account " + AccountNumber));
			throw e;
		}
	}

}
