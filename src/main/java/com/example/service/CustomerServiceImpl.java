package com.example.service;

import java.util.List;

import com.example.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.CustomerRepository;



@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public List<Customer> getCustomers() {

		return customerRepo.findAll();
	}
	@Override
	public void save(Customer user) {
		customerRepo.save(user);
	}

	@Override
	public Customer findById(Integer id) {
	return customerRepo.findById(id).get();
	}
	@Override
	public void delete(Customer user) {
		customerRepo.delete(user);
	}
}
