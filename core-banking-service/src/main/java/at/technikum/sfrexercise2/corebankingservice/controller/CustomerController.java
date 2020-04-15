package at.technikum.sfrexercise2.corebankingservice.controller;

import at.technikum.sfrexercise2.corebankingservice.model.Customer;
import at.technikum.sfrexercise2.corebankingservice.service.MessageSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  private MessageSender messageSender;

  public CustomerController(MessageSender messageSender){
    this.messageSender = messageSender;
  }

  @PostMapping(path = "/customer")
  public void createCustomer(@RequestBody Customer customer) {
    messageSender.sendCustomer(customer);
  }
}
