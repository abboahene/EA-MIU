package repositories;

import domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o.status from Order o where o.status = 'closed'")
    List<String> findByStatus();

    @Query("select o.orderNumber from Order o where o.customer.address.city = :city")
    List<String> findOrderNumbersByCustomerCity(@Param("city") String city);

}
