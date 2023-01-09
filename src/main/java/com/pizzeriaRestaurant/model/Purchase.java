package com.pizzeriaRestaurant.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Date;

@Entity
public class Purchase {
	@Id
	private int id;
	private Date date;
	private int quantity;
	private float totalcost;
	private int productid;

	private String name;
	@OneToOne
	private Customer customer;
	

	public Purchase() {
		super();
	}


	public Purchase(int id, Date date, int quantity, float totalcost, int productid, String name, Customer customer) {
		super();
		this.id = id;
		this.date = date;
		this.quantity = quantity;
		this.totalcost = totalcost;
		this.productid = productid;
		this.name = name;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(float totalcost) {
		this.totalcost = totalcost;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
