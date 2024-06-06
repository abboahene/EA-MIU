package repositories;

import domain.Address;
import domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(value = "SELECT * FROM address a WHERE a.city = 'Amsterdam'", nativeQuery = true)
    List<Address> findAddressesInAmsterdam();
}
