package client;

import client.dto.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private RestTemplate restTemplate = new RestTemplate();

	private String serverUrl = "http://localhost:8080/accounts";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		//-- create 2 accounts;
		// add Frank Brown
		restTemplate.postForLocation(serverUrl, new AccountDTO(1263862, new CustomerDTO("Frank Brown")));
		// add John Doe
		restTemplate.postForLocation(serverUrl, new AccountDTO(4253892, new CustomerDTO("John Doe")));


		//use Frank Brown account;
		restTemplate.put(serverUrl+"/deposit" , new DepositWithdrawDTO(1263862, 240));
		restTemplate.put(serverUrl+"/deposit" , new DepositWithdrawDTO(1263862, 529));
		restTemplate.put(serverUrl+"/withdraw-euros" , new DepositWithdrawDTO(1263862, 230));

		//use John Doe account;
		restTemplate.put(serverUrl+"/deposit" , new DepositWithdrawDTO(4253892, 12450));
		restTemplate.put(serverUrl+"/deposit-euros" , new DepositWithdrawDTO(4253892, 200));
		restTemplate.put(serverUrl+"/transfer-funds" , new TransferFundsDTO(4253892, 1263862, 100, "payment of invoice 10232"));


		// show balances
		AccountDTOs accountlist = restTemplate.getForObject(serverUrl, AccountDTOs.class);;
		CustomerDTO customer = null;
		for (AccountDTO account : accountlist.getAccounts()) {
			customer = account.getCustomer();
			System.out.println("Statement for Account: " + account.getAccountNumber());
			System.out.println("Account Holder: " + customer.getName());
			System.out.println("-Date-------------------------"
					+ "-Description------------------"
					+ "-Amount-------------");
			for (AccountEntryDTO entry : account.getEntryList()) {
				System.out.printf("%30s%30s%20.2f\n", entry.getDate()
						.toString(), entry.getDescription(), entry.getAmount());
			}
			System.out.println("----------------------------------------"
					+ "----------------------------------------");
			System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:",
					account.getBalance());
		}

	}

}
