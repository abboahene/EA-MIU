package bank.repository;

import bank.domain.Account;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    Account findByAccountNumber(long accountNumber);
}
