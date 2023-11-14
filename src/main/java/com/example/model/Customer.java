package com.example.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the Customers database table.
 * 
 */
@Entity
@Table(name="Customers")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CustomerID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerID;

	@Column(name="Address")
	private String address;

	@Column(name="City")
	private String city;

	@Column(name="contact_name")
	private String contactName;

	@Column(name="Country")
	private String country;

	@Column(name="customer_name")
	private String customerName;

	@Column(name="PostalCode")
	private String postalCode;

	//bi-directional many-to-one association to Order
	@JsonManagedReference
	@OneToMany(mappedBy="customer")
	@JsonIgnore
	private List<Order> orders;

	public Customer() {
	}

	public int getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setCustomer(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setCustomer(null);

		return order;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"customerID=" + customerID +
				", address='" + address + '\'' +
				", city='" + city + '\'' +
				", contactName='" + contactName + '\'' +
				", country='" + country + '\'' +
				", customerName='" + customerName + '\'' +
				", postalCode='" + postalCode + '\'' +
				", orders=" + orders +
				'}';
	}
}