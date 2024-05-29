package customers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "customers")
public class AppConfig {

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
