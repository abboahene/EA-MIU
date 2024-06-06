package repositories;

import domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    List<Customer> findByCountry();
    @Query("select c.firstName, c.lastName from Customer c where c.address.city = 'Amsterdam'")
    List<Object[]> findCustomersInAmsterdam();
}
