package at.technikum.sfrexercise2.corebankingservice.service;

import at.technikum.sfrexercise2.corebankingservice.model.Customer;
import at.technikum.sfrexercise2.corebankingservice.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class MessageSender {

  KafkaTemplate<String, Customer> kafkaCustomerTemplate;
  KafkaTemplate<String, Transaction> kafkaTransactionTemplate;

  public MessageSender(KafkaTemplate<String, Customer> kafkaCustomerTemplate,
      KafkaTemplate<String, Transaction> kafkaTransactionTemplate) {
    this.kafkaCustomerTemplate = kafkaCustomerTemplate;
    this.kafkaTransactionTemplate = kafkaTransactionTemplate;
  }

  public void sendCustomer(Customer customer, String topicName) {

    ListenableFuture<SendResult<String, Customer>> future = kafkaCustomerTemplate
        .send(topicName, customer);

    future.addCallback(new ListenableFutureCallback<SendResult<String, Customer>>() {

      @Override
      public void onSuccess(SendResult<String, Customer> result) {
        log.info("Sent customer={} with offset {}",
            customer.toString(),
            result.getRecordMetadata().offset());
      }

      @Override
      public void onFailure(Throwable ex) {
        log.error("Unable to send message \"{}\" due to: ",
            customer.toString(),
            ex.getMessage());
      }
    });
  }

  public void sendTransaction(Transaction transaction, String topicName) {

    ListenableFuture<SendResult<String, Transaction>> future = kafkaTransactionTemplate
        .send(topicName, transaction);

    future.addCallback(new ListenableFutureCallback<SendResult<String, Transaction>>() {

      @Override
      public void onSuccess(SendResult<String, Transaction> result) {
        log.info("Sent transaction={} with offset {}",
            transaction.toString(),
            result.getRecordMetadata().offset());
      }

      @Override
      public void onFailure(Throwable ex) {
        log.error("Unable to send message \"{}\" due to: ",
            transaction.toString(),
            ex.getMessage());
      }
    });
  }

}
