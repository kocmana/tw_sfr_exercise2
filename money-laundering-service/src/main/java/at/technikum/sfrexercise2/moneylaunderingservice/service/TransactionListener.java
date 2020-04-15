package at.technikum.sfrexercise2.moneylaunderingservice.service;

import static at.technikum.sfrexercise2.moneylaunderingservice.model.MoneyLaunderingAlert.Criticality.HIGH;
import static at.technikum.sfrexercise2.moneylaunderingservice.model.MoneyLaunderingAlert.Criticality.LOW;
import static at.technikum.sfrexercise2.moneylaunderingservice.model.MoneyLaunderingAlert.Criticality.MEDIUM;
import static at.technikum.sfrexercise2.moneylaunderingservice.model.MoneyLaunderingAlert.Criticality.UNDEFINED;

import at.technikum.sfrexercise2.moneylaunderingservice.model.MoneyLaunderingAlert;
import at.technikum.sfrexercise2.moneylaunderingservice.model.MoneyLaunderingAlert.Criticality;
import at.technikum.sfrexercise2.moneylaunderingservice.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionListener {

  private MessageSender messageSender;

  public TransactionListener(MessageSender messageSender) {
    this.messageSender = messageSender;
  }

  @KafkaListener(topics = "transactions", groupId = "banking")
  public void listenWithHeaders(
      @Payload Transaction transaction,
      @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
    log.info(
        "Received transaction from: \"{}\" to: \"{}\" for the amount of {} € from partition \"{}\"",
        transaction.getSourceIban(),
        transaction.getDestinationIban(),
        transaction.getAmount(),
        partition);

    if (transaction.getAmount() > 100_000) {
      sendMoneyLaunderingAlert(transaction);
    }
  }

  private void sendMoneyLaunderingAlert(Transaction transaction) {

    Criticality criticality = UNDEFINED;

    if (transaction.getAmount() > 300_000) {
      criticality = HIGH;
    } else if (transaction.getAmount() > 200_000) {
      criticality = MEDIUM;
    } else if (transaction.getAmount() > 100_000) {
      criticality = LOW;
    }
    log.warn("Money laundering risk discovered in transaction from {} to {}",
        transaction.getSourceIban(),
        transaction.getDestinationIban());
    messageSender.sendAlert(new MoneyLaunderingAlert(criticality,transaction));
  }
}
