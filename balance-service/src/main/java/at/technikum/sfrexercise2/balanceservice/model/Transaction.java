package at.technikum.sfrexercise2.balanceservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Transaction {

  @Id
  @GeneratedValue
  private long id;
  private String sourceIban;
  private String destinationIban;
  private double amount;
}
