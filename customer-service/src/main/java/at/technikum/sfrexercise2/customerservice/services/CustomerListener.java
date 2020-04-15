package at.technikum.sfrexercise2.customerservice.services;

import at.technikum.sfrexercise2.customerservice.model.Customer;
import at.technikum.sfrexercise2.customerservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerListener {

  CustomerRepository customerRepository;

  private CustomerListener(CustomerRepository customerRepository){
    this.customerRepository = customerRepository;
  }

  @KafkaListener(topics = "${customers.topic.name}")
  public void listenWithHeaders(
      @Payload Customer customer,
      @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
    log.info(
        "Received new Customer: \"{} {}\" with IBAN \"{}\" from partition[{}]",
            customer.getFirstName(), customer.getLastName(),
            customer.getIban(),
            partition);
    customerRepository.save(customer);
  }
}
