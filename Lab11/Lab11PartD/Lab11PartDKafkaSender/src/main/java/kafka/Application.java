package kafka;

import kafka.dto.CreateAccountDTO;
import kafka.dto.DepositWithdrawDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class Application implements CommandLineRunner {

    @Autowired
    Sender sender;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sender.createAccount(new CreateAccountDTO(329892, "John Doe"));
        System.out.println("Account created");
        sender.deposit(new DepositWithdrawDTO(329892, 1000.00));
        System.out.println("Funds deposited");
        sender.withdraw(new DepositWithdrawDTO(329892, 300.00));
        System.out.println("Funds withdrawn");
    }

}
