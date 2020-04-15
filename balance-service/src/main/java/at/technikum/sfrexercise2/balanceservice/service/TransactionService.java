package at.technikum.sfrexercise2.balanceservice.service;

import at.technikum.sfrexercise2.balanceservice.model.Transaction;
import at.technikum.sfrexercise2.balanceservice.repository.TransactionRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private TransactionRepository transactionRepository;

  public TransactionService(TransactionRepository transactionRepository){
    this.transactionRepository = transactionRepository;
  }

  public Optional<Transaction> getTransactionById(long id){
    return transactionRepository.findById(id);
  }

  public Iterable<Transaction> getAllTransactions(){
    return transactionRepository.findAll();
  }

  public void saveTransaction(Transaction transaction){
    transactionRepository.save(transaction);
  }

}
