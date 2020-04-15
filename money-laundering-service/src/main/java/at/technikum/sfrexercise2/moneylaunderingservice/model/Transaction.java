package at.technikum.sfrexercise2.moneylaunderingservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

  private String sourceIban;
  private String destinationIban;
  private double amount;
}
