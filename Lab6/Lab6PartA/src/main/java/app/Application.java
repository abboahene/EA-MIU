package app;

import domain.Address;
import domain.Customer;
import domain.Order;
import domain.OrderLine;
import domain.product.CD;
import domain.product.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.AddressRepository;
import repositories.CustomerRepository;
import repositories.OrderRepository;
import repositories.product.CDRepository;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class Application implements CommandLineRunner{
	
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CDRepository cdRepository;
	@Autowired
	AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		Product product = new Product();
		product.setName("Hibernate 3");
		product.setDescription("Good book on Hibernate");
		product.setPrice(35.50);
		OrderLine ol1 = new OrderLine(2, product);

		Product product2 = new Product();
		product2.setName("The best of Queen");
		product2.setDescription("Album from 1995");
		product2.setPrice(12.98);
		OrderLine ol2 = new OrderLine(4, product2);

		CD product3= new CD();
		product3.setName("Beat it");
		product3.setDescription("Album from 1995");
		product3.setPrice(13.98);
		product3.setArtist("MJ");
		OrderLine ol3 = new OrderLine(7, product3);

		Order o1 = new Order("234743", "12/10/06", "open");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);
		o1.addOrderLine(ol3);

		Order o2 = new Order("234743", "12/10/06", "open");
		o1.addOrderLine(ol1);
		o1.addOrderLine(ol2);
		o1.addOrderLine(ol3);

		Customer c1 = new Customer("Frank", "Brown", "Mainstreet 1",
				"New york", "43221", "USA");
		c1.addOrder(o1);
		o1.setCustomer(c1);


		Customer c2 = new Customer("Alfred", "Brown", "Mainstreet 1",
				"Denver", "43221", "USA");
		c1.addOrder(o2);
		o1.setCustomer(c2);

		orderRepository.save(o1);
		orderRepository.save(o2);

		// order
		System.out.println(orderRepository.findByStatus());
		System.out.println(orderRepository.findOrderNumbersByCustomerCity("Denver"));

		// customer
		System.out.println(customerRepository.findByCountry());
		System.out.println(customerRepository.findCustomersInAmsterdam());

		// cd
		System.out.println(cdRepository.findByArtist("MJ"));
		System.out.println(cdRepository.findByArtistAndPriceLessThan("U2", 10.0));
		System.out.println(cdRepository.findCDsByArtistAndPriceGreaterThan("MJ", 10.0));
		System.out.println(cdRepository.findCDsByArtistU2());

		// address
		System.out.println(addressRepository.findAddressesInAmsterdam());

		System.out.println(orderRepository.findByStatus());
		System.out.println(orderRepository.findOrderNumbersByCustomerCity("Denver"));
//		printOrder(o1);


	}

	public static void printOrder(Order order) {
		System.out.println("Order with orderNumber: " + order.getOrderNumber());
		System.out.println("Order date: " + order.getDate());
		System.out.println("Order status: " + order.getStatus());
		Customer cust = order.getCustomer();
		System.out.println("Customer: " + cust.getFirstName() + " "
				+ cust.getLastName());
		for (OrderLine orderline : order.getOrderLines()) {
			System.out.println("Order line: quantity= "
					+ orderline.getQuantity());
			Product product = orderline.getProduct();
			System.out.println("Product: " + product.getName() + " "
					+ product.getDescription() + " " + product.getPrice());
		}
	}
}
