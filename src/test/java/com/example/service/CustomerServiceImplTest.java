package com.example.service;

import com.example.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.model.Customer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomerServiceImpl.class)
class CustomerServiceImplTest {

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    @DisplayName("Test findAll")
    void getCustomers() {

        Customer customer1 = new Customer();
        customer1.setCustomerName("Test1");
        customer1.setCustomerID(1000);
        customer1.setCity("Test1");
        customer1.setContactName("Test1");
        customer1.setPostalCode("Test1");
        customer1.setAddress("Test1");

        Customer customer2 = new Customer();
        customer1.setCustomerName("Test2");
        customer1.setCustomerID(10001);
        customer1.setCity("Test2");
        customer1.setContactName("Test2");
        customer1.setPostalCode("Test2");
        customer1.setAddress("Test2");


        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer1));

        // Execute the service call
        List<Customer> customers = customerService.getCustomers();

        // Assert the response
        Assertions.assertEquals(2, customers.size(), "findAll should return 2 customers");

    }

    @Test
    void save() {

        Customer customer1 = new Customer();
        customer1.setCustomerName("Test1");
        customer1.setCustomerID(1000);
        customer1.setCity("Test1");
        customer1.setContactName("Test1");
        customer1.setPostalCode("Test1");
        customer1.setAddress("Test1");


        when(customerRepository.save(customer1)).thenReturn(null);

        customerService.save(customer1);

    }

    @Test
    @DisplayName("Test findById Success")
    void findById() {

        Customer customer1 = new Customer();
        customer1.setCustomerName("Test1");
        customer1.setCustomerID(1000);
        customer1.setCity("Test1");
        customer1.setContactName("Test1");
        customer1.setPostalCode("Test1");
        customer1.setAddress("Test1");

        when(customerRepository.findById(1000)).thenReturn(Optional.of(customer1));

        // Execute the service call
        Optional<Customer> customer = Optional.ofNullable(customerService.findById(1000));

        // Assert the response
        Assertions.assertTrue(customer.isPresent(), "customer was not found");
        Assertions.assertSame(customer.get(), customer1, "The customer returned was not the same as the mock");

    }



    @Test
    void delete() {

        Customer customer1 = new Customer();
        customer1.setCustomerName("Test1");
        customer1.setCustomerID(1000);
        customer1.setCity("Test1");
        customer1.setContactName("Test1");
        customer1.setPostalCode("Test1");
        customer1.setAddress("Test1");


        doNothing().when(customerRepository).delete(customer1);

        customerService.delete(customer1);
    }
}