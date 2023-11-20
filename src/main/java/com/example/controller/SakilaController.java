package com.example.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.model.Category;
import com.example.model.Customer;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.example.service.CustomerService;


@RestController
@RequestMapping("/api")
public class SakilaController {



    @Autowired
    private CustomerService customerService;
    @Autowired
    private CategoryService categoryService;



    @GetMapping("/allcustomers")
    public ResponseEntity<?> getCustomers(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Customer> userList = customerService.getCustomers();
        if (!userList.isEmpty()) {
            map.put("status", 1);
            map.put("data", userList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/saveCustomer", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> saveCustomer(@RequestBody Customer user) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        System.out.println(user.toString());
        customerService.save(user);
        map.put("status", 1);
        map.put("message", "Record is Saved Successfully!");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }


    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Customer user = customerService.findById(id);
            map.put("status", 1);
            map.put("data", user);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Customer user = customerService.findById(id);
            customerService.delete(user);
            map.put("status", 1);
            map.put("message", "Record is deleted successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<?> updateCustomerById(@PathVariable Integer id, @RequestBody Customer userDetail) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Customer user = customerService.findById(id);
            user.setCountry(userDetail.getCountry());
            user.setCity(userDetail.getCity());
            user.setPostalCode(userDetail.getPostalCode());
            customerService.save(user);
            map.put("status", 1);
            map.put("data", customerService.findById(id));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allcategories")
    public ResponseEntity<?> getCtegories(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Category> userList = categoryService.getCategories();
        if (!userList.isEmpty()) {
            map.put("status", 1);
            map.put("data", userList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/saveCategory", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCustomer(@RequestBody Category user) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        categoryService.save(user);
        map.put("status", 1);
        map.put("message", "Record is Saved Successfully!");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Category user = categoryService.findById(id);
            map.put("status", 1);
            map.put("data", user);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Category user = categoryService.findById(id);
            categoryService.delete(user);
            map.put("status", 1);
            map.put("message", "Record is deleted successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable Integer id, @RequestBody Category userDetail) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Category user = categoryService.findById(id);
            user.setCategoryName(userDetail.getCategoryName());
            user.setDescription(userDetail.getDescription());

            categoryService.save(user);
            map.put("status", 1);
            map.put("data", categoryService.findById(id));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}

