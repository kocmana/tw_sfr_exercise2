package at.technikum.sfrexercise2.corebankingservice;

import at.technikum.sfrexercise2.corebankingservice.model.Customer;
import at.technikum.sfrexercise2.corebankingservice.model.Transaction;
import at.technikum.sfrexercise2.corebankingservice.service.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Runner implements CommandLineRunner {

  private MessageSender messageSender;

  public Runner(MessageSender messageSender) {
    this.messageSender = messageSender;
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("Starting up");

    messageSender.sendCustomer(generateCustomer(), "customers");
    messageSender.sendTransaction(generateTransaction(), "transactions");
  }

  private Customer generateCustomer(){
   return new Customer("Hans", "Kanns", 100.50,"AT01234");
  }

  private Transaction generateTransaction(){
    return new Transaction("AT01234", "AT02345", 2_000_050.50);
  }
}
