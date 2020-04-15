package at.technikum.sfrexercise2.balanceservice.repository;

import at.technikum.sfrexercise2.balanceservice.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
