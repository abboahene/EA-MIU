package customers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository("customerRepository")
@Profile("prod")
public class CustomerRepositoryImpl implements CustomerRepository{

	private Logger logger;

	CustomerRepositoryImpl(Logger logger){
		this.logger = logger;
	}

	public void save(Customer customer) {
		// simple sleep
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("CustomerRepository: saving customer "+customer.getName());
		logger.log("Customer is saved in the DB: "+ customer.getName() );
	}

}
