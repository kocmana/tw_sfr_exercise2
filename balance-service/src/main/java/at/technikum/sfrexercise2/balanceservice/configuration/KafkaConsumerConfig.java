package at.technikum.sfrexercise2.balanceservice.configuration;

import at.technikum.sfrexercise2.balanceservice.model.MoneyLaunderingAlert;
import at.technikum.sfrexercise2.balanceservice.model.Transaction;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  @Value(value = "${kafka.bootstrapAddress}")
  private String bootstrapAddress;

  @Value(value = "${spring.kafka.consumer.group-id}")
  private String groupId;

  @Bean
  public ConsumerFactory<String, Transaction> transactionConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        bootstrapAddress);
    props.put(
        ConsumerConfig.GROUP_ID_CONFIG,
        groupId);
    return new DefaultKafkaConsumerFactory<>(props,
        new StringDeserializer(),
        new JsonDeserializer<>(Transaction.class)
            .ignoreTypeHeaders());
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Transaction> transactionKafkaListenerContainerFactory() {

    ConcurrentKafkaListenerContainerFactory<String, Transaction> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(transactionConsumerFactory());
    return factory;
  }

  @Bean
  public ConsumerFactory<String, MoneyLaunderingAlert> moneyLaunderingAlertConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        bootstrapAddress);
    props.put(
        ConsumerConfig.GROUP_ID_CONFIG,
        groupId);
    return new DefaultKafkaConsumerFactory<>(props,
        new StringDeserializer(),
        new JsonDeserializer<>(MoneyLaunderingAlert.class)
            .ignoreTypeHeaders());
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, MoneyLaunderingAlert> moneyLaunderingAlertKafkaListenerContainerFactory() {

    ConcurrentKafkaListenerContainerFactory<String, MoneyLaunderingAlert> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(moneyLaunderingAlertConsumerFactory());
    return factory;
  }

}
