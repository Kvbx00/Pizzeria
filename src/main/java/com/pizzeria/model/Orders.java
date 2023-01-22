package com.pizzeria.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
public class Orders {
	@Id
	private int id;
	private Date date;

	@OneToOne
	private Customer customer;

	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
	public List<OrderDetails> orderDetails;

	@NotNull
	@Column(name = "price", nullable = false)
	private Float price;

	public Orders() {
		super();
	}


	public Orders(int id, Date date, Customer customer) {
		super();
		this.id = id;
		this.date = date;
		this.customer = customer;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
