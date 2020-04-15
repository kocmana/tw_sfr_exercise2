package at.technikum.sfrexercise2.customerservice.repository;

import at.technikum.sfrexercise2.customerservice.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String> {

}
