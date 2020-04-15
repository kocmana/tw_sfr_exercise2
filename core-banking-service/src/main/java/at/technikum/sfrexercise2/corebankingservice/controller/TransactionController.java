package at.technikum.sfrexercise2.corebankingservice.controller;

import at.technikum.sfrexercise2.corebankingservice.model.Transaction;
import at.technikum.sfrexercise2.corebankingservice.service.MessageSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

  private MessageSender messageSender;

  public TransactionController(MessageSender messageSender) {
    this.messageSender = messageSender;
  }

  @PostMapping(path = "/transaction")
  public void createTransaction(@RequestBody Transaction transaction) {
    messageSender.sendTransaction(transaction);
  }

}
