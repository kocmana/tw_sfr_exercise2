package at.technikum.sfrexercise2.corebankingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {

  private String firstName;
  private String lastName;
  private double balance;
  private String iban;
}
