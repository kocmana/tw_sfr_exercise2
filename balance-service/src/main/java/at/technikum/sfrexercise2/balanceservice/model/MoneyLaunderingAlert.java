package at.technikum.sfrexercise2.balanceservice.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class MoneyLaunderingAlert {

  @Id
  @GeneratedValue
  private Long id;
  @Enumerated
  private Criticality risk;
  @OneToOne
  private Transaction transaction;

  public enum Criticality {
    LOW, MEDIUM, HIGH, UNDEFINED
  }
}
