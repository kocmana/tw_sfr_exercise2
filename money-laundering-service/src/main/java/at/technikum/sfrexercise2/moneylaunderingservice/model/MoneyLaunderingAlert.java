package at.technikum.sfrexercise2.moneylaunderingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MoneyLaunderingAlert {

  private Criticality risk;
  private Transaction transaction;

  public enum Criticality {
    LOW, MEDIUM, HIGH, UNDEFINED
  }
}
