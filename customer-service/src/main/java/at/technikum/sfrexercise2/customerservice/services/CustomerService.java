package at.technikum.sfrexercise2.customerservice.services;

import at.technikum.sfrexercise2.customerservice.model.Customer;
import at.technikum.sfrexercise2.customerservice.repository.CustomerRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository){
    this.customerRepository = customerRepository;
  }

  public Optional<Customer> getCustomerById(String iban){
    return customerRepository.findById(iban);
  }

  public Iterable<Customer> getAllCustomer(){
    return customerRepository.findAll();
  }

  public void saveCustomer(Customer customer){
    customerRepository.save(customer);
  }

}
