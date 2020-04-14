package at.technikum.sfrexercise2.customerservice.services;

import at.technikum.sfrexercise2.customerservice.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerListener {
  @KafkaListener(topics = "customers")
  public void listenWithHeaders(
      @Payload Customer customer,
      @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
    log.info(
        "Received new Customer: \"{} {}\" with IBAN \"{}\" from partition[{}]",
            customer.getFistName(), customer.getLastName(),
            customer.getIban(),
            partition);
  }
}
