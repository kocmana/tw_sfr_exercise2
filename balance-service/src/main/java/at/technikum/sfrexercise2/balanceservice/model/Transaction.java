package at.technikum.sfrexercise2.balanceservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

  private String sourceIban;
  private String destinationIban;
  private double amount;
}
