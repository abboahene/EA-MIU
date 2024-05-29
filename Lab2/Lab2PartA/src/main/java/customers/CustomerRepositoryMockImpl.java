package customers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository("customerRepositoryMock")
@Profile("test")
public class CustomerRepositoryMockImpl implements CustomerRepository{

	private Logger logger;

	CustomerRepositoryMockImpl(Logger logger){
		this.logger = logger;
	}

	public void save(Customer customer) {
		// simple sleep
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("CustomerRepositoryMock: saving customer "+customer.getName());
		logger.log("Customer is saved in the Mock DB: "+ customer.getName() );
	}

}