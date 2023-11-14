package com.example.service;

import com.example.model.Customer;

import java.util.List;


public interface CustomerService {
	public List<Customer> getCustomers();
	public void save(Customer user);
	public Customer findById(Integer id);
	public void delete(Customer user);
}
