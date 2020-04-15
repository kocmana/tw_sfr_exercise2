package at.technikum.sfrexercise2.customerservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer {

  private String firstName;
  private String lastName;
  private double balance;
  @Id
  private String iban;
}
