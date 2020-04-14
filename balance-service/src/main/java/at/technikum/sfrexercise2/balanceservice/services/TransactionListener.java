package at.technikum.sfrexercise2.balanceservice.services;

import at.technikum.sfrexercise2.balanceservice.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionListener {
  @KafkaListener(topics = "transactions", groupId = "banking")
  public void listenWithHeaders(
      @Payload Transaction transaction,
      @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
    log.warn("Received transaction from: \"{}\" to: \"{}\" for the amount of {} € from partition \"{}\"",
        transaction.getSourceIban(),
        transaction.getDestinationIban(),
        transaction.getAmount(),
        partition);
  }
}
