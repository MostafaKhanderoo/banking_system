package app;

import app.entity.Customer;
import app.repository.impl.CustomerRepositoryImpl;
import app.service.impl.CustomerServiceImpl;

public class Main {
    public static void main(String[] args) {
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl(Customer.class);
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepository);
        Customer customer = new Customer();
        customer.setFirstname("a");
        customer.setLastname("a");
        customer.setAge(12);
        customer.setNationalCode("12345");
        customer.setUsername("a");
        customer.setPassword("a");
      //  customerService.save(customer);
        System.out.println(customerService.findAll());
        /*
            Banking System

           1.add entity Customer Employee Account Credit_card  Bank_branch Boss Transaction BaseEntity
 private String status; //  example "CREATED"، "PAID"، "PAYMENT_FAILED" or "CANCELLED"
todo design pattern
todo Stream
todo unit test
todo : dto  /  validation
todo : new dataBase
todo : docker <----
todo : optional in OneToMany
todo : jeneric

         */
    }
}
