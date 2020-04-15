package at.technikum.sfrexercise2.balanceservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MoneyLaunderingAlert {

  private Criticality risk;
  private Transaction transaction;

  public enum Criticality {
    LOW, MEDIUM, HIGH, UNDEFINED
  }
}
