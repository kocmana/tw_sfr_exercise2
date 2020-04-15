package at.technikum.sfrexercise2.balanceservice.service;

import at.technikum.sfrexercise2.balanceservice.model.MoneyLaunderingAlert;
import at.technikum.sfrexercise2.balanceservice.repository.MoneyLaunderingAlertRepository;
import at.technikum.sfrexercise2.balanceservice.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MoneyLaunderingAlertService {

  private MoneyLaunderingAlertRepository moneyLaunderingAlertRepository;
  private TransactionRepository transactionRepository;

  public Iterable<MoneyLaunderingAlert> getAllAlerts(){
    return moneyLaunderingAlertRepository.findAll();
  }

  public void saveAlert(MoneyLaunderingAlert alert){
    transactionRepository.save(alert.getTransaction());
    moneyLaunderingAlertRepository.save(alert);
  }

}
