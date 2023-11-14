package com.example.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the Shippers database table.
 * 
 */
@Entity
@Table(name="Shippers")
@NamedQuery(name="Shipper.findAll", query="SELECT s FROM Shipper s")
public class Shipper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ShipperID")
	private int shipperID;

	@Column(name="Phone")
	private String phone;

	@Column(name="shipper_name")
	private String shipperName;

	//bi-directional many-to-one association to Order
	@JsonManagedReference
	@OneToMany(mappedBy="shipper")
	private List<Order> orders;

	public Shipper() {
	}

	public int getShipperID() {
		return this.shipperID;
	}

	public void setShipperID(int shipperID) {
		this.shipperID = shipperID;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getShipperName() {
		return this.shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setShipper(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setShipper(null);

		return order;
	}

}