package at.technikum.sfrexercise2.corebankingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {

  private String fistName;
  private String lastName;
  private double balance;
  private String iban;
}
