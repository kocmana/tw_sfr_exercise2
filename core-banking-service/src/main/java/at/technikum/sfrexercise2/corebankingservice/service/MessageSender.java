package at.technikum.sfrexercise2.corebankingservice.service;

import at.technikum.sfrexercise2.corebankingservice.model.Customer;
import at.technikum.sfrexercise2.corebankingservice.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class MessageSender {

  @Value(value = "${customer.topic.name}")
  private String customerTopicName;

  @Value(value = "${transaction.topic.name}")
  private String transactionTopicName;


  KafkaTemplate<String, Customer> kafkaCustomerTemplate;
  KafkaTemplate<String, Transaction> kafkaTransactionTemplate;

  public MessageSender(KafkaTemplate<String, Customer> kafkaCustomerTemplate,
      KafkaTemplate<String, Transaction> kafkaTransactionTemplate) {
    this.kafkaCustomerTemplate = kafkaCustomerTemplate;
    this.kafkaTransactionTemplate = kafkaTransactionTemplate;
  }

  public void sendCustomer(Customer customer) {

    ListenableFuture<SendResult<String, Customer>> future = kafkaCustomerTemplate
        .send(customerTopicName, customer);

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

  public void sendTransaction(Transaction transaction) {

    ListenableFuture<SendResult<String, Transaction>> future = kafkaTransactionTemplate
        .send(transactionTopicName, transaction);

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
