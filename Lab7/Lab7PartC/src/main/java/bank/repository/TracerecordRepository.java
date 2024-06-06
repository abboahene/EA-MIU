package bank.repository;


import bank.domain.Tracerecord;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface TracerecordRepository extends JpaRepository<Tracerecord, Long> {

}




