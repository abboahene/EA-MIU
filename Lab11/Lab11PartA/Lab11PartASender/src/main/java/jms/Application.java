package jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;


@EnableJms
@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	JmsTemplate jmsTemplate;

	public static void main(String[] args)  {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		context.close();
	}

	@Override
	public void run(String... args) throws Exception {
		Message msg = new Message("*", 4);
		//convert message to JSON string
		ObjectMapper objectMapper = new ObjectMapper();
		String msgAsString = objectMapper.writeValueAsString(msg);

		System.out.println("Sending a JMS message:" + msgAsString);
		jmsTemplate.convertAndSend("calcQueue",msgAsString);
	}

}
