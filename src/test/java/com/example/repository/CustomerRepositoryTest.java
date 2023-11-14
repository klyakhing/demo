package com.example.repository;

import com.example.demo.DemoApplication;
import com.example.model.Customer;
import com.example.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureDataJpa
@SpringBootTest(classes = DemoApplication.class)
@ContextConfiguration(classes=DemoApplication.class)
class CustomerRepositoryTest {


    @Autowired
    private CustomerRepository customerRepository;
    private Customer customer1 = null;
    private Customer customer2 = null;

    @BeforeEach
    public void setUp() {

        customer1 = new Customer();
        customer1.setCustomerName("Test1");
        customer1.setCustomerID(20);
        customer1.setCity("Test1");
        customer1.setContactName("Test1");
        customer1.setPostalCode("Test1");
        customer1.setAddress("Test1");

        customer2 = new Customer();
        customer1.setCustomerName("Test2");
        customer1.setCustomerID(10001);
        customer1.setCity("Test2");
        customer1.setContactName("Test2");
        customer1.setPostalCode("Test2");
        customer1.setAddress("Test2");

    }

    @AfterEach
    public void tearDown() {

        customerRepository.delete(customer1);
        customer1 = null;
        customerRepository.delete(customer2);
        customer2 = null;
    }

    @Test
    public void givenCustomerToAddShouldReturnAddedCustomer(){


        Customer fetchedCustomer = customerRepository.findById(20).get();
        assertEquals("Ernst Handel", fetchedCustomer.getCustomerName());
    }

    @Test
    public void GivenGetAllCustomerShouldReturnListOfAllCustomer(){



        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        assertEquals("Alfreds Futterkiste", customerList.get(0).getCustomerName());

    }

    @Test
    public void givenIdTODeleteThenShouldDeleteTheProduct() {

        customerRepository.deleteById(100);
        Optional optional = customerRepository.findById(100);
        assertEquals(Optional.empty(), optional);
    }

}