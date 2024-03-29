package at.technikum.sfrexercise2.corebankingservice.configuration;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfiguration {

  @Value(value = "${kafka.bootstrapAddress}")
  private String bootstrapAddress;

  @Value(value="${transaction.topic.name}")
  private String transactionChannelName;

  @Value(value="${customer.topic.name}")
  private String customerChannelName;

  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic customerTopic() {
    return new NewTopic(customerChannelName, 1, (short) 1);
  }


  @Bean
  public NewTopic transactionTopic() {
    return new NewTopic(transactionChannelName, 1, (short) 1);
  }
}
