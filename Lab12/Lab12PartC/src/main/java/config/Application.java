package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@EnableConfigurationProperties(ConfigValues.class)
public class Application implements CommandLineRunner {

	@Autowired
	ConfigValues configValues;

	public static void main(String[] args)  {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		context.close();
	}

	@Override
	public void run(String... args) throws Exception {
		configValues.print();
	}

}
