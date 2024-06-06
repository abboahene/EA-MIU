package repository;

import domain.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Query("UPDATE Book b SET b.locationCode = CONCAT('BB', b.locationCode)")
    void updateLocationCodes();

    @Modifying
    @Query("DELETE FROM Book b WHERE b.publicationYear < :year")
    void deleteOldBooks(@Param("year") int year);
}
