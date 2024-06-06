package repositories.product;

import domain.product.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CDRepository extends JpaRepository<CD, Integer> {

    List<CD> findByArtistAndPriceLessThan(String artist, double price);
    List<CD> findByArtist(String artist);

    @Query("select cd from CD cd where cd.artist = :artist and cd.price > :amount")
    List<CD> findCDsByArtistAndPriceGreaterThan(@Param("artist") String artist, @Param("amount") double amount);

    @Query(value = "SELECT * FROM cd INNER JOIN product ON cd.id = product.id WHERE cd.artist = 'U2'", nativeQuery = true)
    List<CD> findCDsByArtistU2();
}
