package at.technikum.sfrexercise2.corebankingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {

  private String sourceIban;
  private String destinationIban;
  private double amount;
}
