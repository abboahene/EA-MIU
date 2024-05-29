package customers;

public interface CustomerService {

	void addCustomer(String name, String email, String street,String city, String zip);

	public void setCustomerRepository(CustomerRepository customerRepository);
	public void setEmailSender(EmailSender emailSender);

}
