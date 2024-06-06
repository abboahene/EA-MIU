package repositories.product;

import domain.product.DVD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DVDRepository extends JpaRepository<DVD, Integer> {
}
