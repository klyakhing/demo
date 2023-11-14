package com.example.controller;

import com.example.controller.SakilaController;
import com.example.model.Customer;
import com.example.service.CategoryService;
import com.example.service.CustomerService;
import com.example.service.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import static org.hamcrest.CoreMatchers.is;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes=SakilaControllerTest.class)
//@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class SakilaControllerTest {

    @InjectMocks
    private SakilaController sakilaController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @MockBean
    private CategoryService categoryService;

    private Customer customer1, customer2 = null;

    private List<Customer> customers = new ArrayList<Customer>();

    @BeforeEach
    public void setUp(){

        customer1 = new Customer();
        customer1.setCustomerName("Test1");
        customer1.setCustomerID(1000);
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

        customers.add(customer1);
        customers.add(customer2);

        mockMvc = MockMvcBuilders.standaloneSetup(sakilaController).build();
    }

    @AfterEach
    void tearDown() {
        customer1 = null;
        customer2 = null;
        customers = null;
    }

    @Test
    void getCustomers() throws Exception {

        //when(customerService.getCustomers()).thenReturn(Arrays.asList(customer1, customer1));
        doReturn(customers).when(customerService).getCustomers();

        // Execute the GET request
        mockMvc.perform(get("/api/allcustomers"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON));


                // Validate the returned fields
                //.andExpect(jsonPath("$", hasSize(2)))
                /*.andExpect(jsonPath("$[0].customerID", is(1000)))
                .andExpect(jsonPath("$[0].address", is("Test1")))
                .andExpect(jsonPath("$[0].city", is("Test1")))
                .andExpect(jsonPath("$[0].contactName", is("Test1")))
                .andExpect(jsonPath("$[0].country", is("Test1")))
                .andExpect(jsonPath("$[0].customerName", is("Test1")))
                .andExpect(jsonPath("$[0].postalCode", is("Test1")))
                .andExpect(jsonPath("$[1].customerID", is(10001)))
                .andExpect(jsonPath("$[1].address", is("Test2")))
                .andExpect(jsonPath("$[1].city", is("Test2")))
                .andExpect(jsonPath("$[1].contactName", is("Test2")))
                .andExpect(jsonPath("$[1].country", is("Test2")))
                .andExpect(jsonPath("$[1].customerName", is("Test2")))
                .andExpect(jsonPath("$[1].postalCode", is("Test2")));*/

        verify(customerService).getCustomers();
        verify(customerService,times(1)).getCustomers();
    }

    @Test
    void saveCustomer() throws Exception {

        //doNothing().when(customerService).save(customer1);
        mockMvc.perform(post("/api/saveCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer1)))
                        .andExpect(status().isCreated());
        verify(customerService,times(1)).save(any());

    }

    @Test
    void getCustomerById() throws Exception {
        when(customerService.findById(1000)).thenReturn(customer1);

        mockMvc.perform(get("/api/customer/1000"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
                /*.andExpect(jsonPath("$[0].customerID", is(1000)))
                .andExpect(jsonPath("$[0].address", is("Test1")))
                .andExpect(jsonPath("$[0].city", is("Test1")))
                .andExpect(jsonPath("$[0].contactName", is("Test1")))
                .andExpect(jsonPath("$[0].country", is("Test1")))
                .andExpect(jsonPath("$[0].customerName", is("Test1")))
                .andExpect(jsonPath("$[0].postalCode", is("Test1")));*/

    }

    @Test
    void deleteCustomer() throws Exception{

        doNothing().when(customerService).delete(customer1);
        mockMvc.perform(delete("/api/deleteCustomer/1000")
                        .contentType(MediaType.APPLICATION_JSON));

        verify(customerService,times(1)).delete(any());
    }

    @Test
    void updateCustomerById() throws Exception{

        /*mockMvc.perform(put("/api/updateCustomer/1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer1)))
                        .andExpect(status().isOk());*/

    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}