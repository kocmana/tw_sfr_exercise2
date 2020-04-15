package at.technikum.sfrexercise2.moneylaunderingservice.service;

import at.technikum.sfrexercise2.moneylaunderingservice.model.MoneyLaunderingAlert;
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

  @Value(value="${alert.topic.name}")
  private String alertChannelName;

  private KafkaTemplate<String, MoneyLaunderingAlert> moneyLaunderingAlertKafkaTemplate;

  public MessageSender(KafkaTemplate<String, MoneyLaunderingAlert> kafkaMoneyLaunderingAlertTemplate) {
    this.moneyLaunderingAlertKafkaTemplate = kafkaMoneyLaunderingAlertTemplate;
  }

  public void sendAlert(MoneyLaunderingAlert alert) {

    ListenableFuture<SendResult<String, MoneyLaunderingAlert>> future = moneyLaunderingAlertKafkaTemplate
        .send(alertChannelName, alert);

    future.addCallback(new ListenableFutureCallback<SendResult<String, MoneyLaunderingAlert>>() {

      @Override
      public void onSuccess(SendResult<String, MoneyLaunderingAlert> result) {
        log.info("Sent transaction={} with offset {}",
            alert.toString(),
            result.getRecordMetadata().offset());
      }

      @Override
      public void onFailure(Throwable ex) {
        log.error("Unable to send message \"{}\" due to: {}",
            alert.toString(),
            ex.getMessage());
      }
    });
  }

}
