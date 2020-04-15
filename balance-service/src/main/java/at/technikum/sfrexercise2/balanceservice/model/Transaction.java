package at.technikum.sfrexercise2.balanceservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Transaction {

  private String sourceIban;
  private String destinationIban;
  private double amount;
}
