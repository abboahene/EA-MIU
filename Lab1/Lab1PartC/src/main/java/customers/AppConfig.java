package customers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CustomerService customerService(){
        CustomerService cs = new CustomerServiceImpl();
        cs.setEmailSender(emailSender());
        cs.setCustomerRepository(customerRepository());
        return cs;
    }

    @Bean
    public CustomerRepository customerRepository(){
        return new CustomerRepositoryImpl(logger());
    }

    @Bean
    public EmailSender emailSender(){
        return new EmailSenderImpl(logger());
    }

    @Bean
    public Logger logger(){
        return new LoggerImpl();
    }

}
