package at.technikum.sfrexercise2.customerservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Customer {

  private String fistName;
  private String lastName;
  private double balance;
  private String iban;
}
