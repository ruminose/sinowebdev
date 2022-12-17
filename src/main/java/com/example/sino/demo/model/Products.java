package com.example.sino.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
// import javax.persistence.EnumType;
// import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Products")
@EntityListeners(AuditingEntityListener.class)

public class Products {
	// Constucture
	public Products() {
		// initial value
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "Productsid", length = 5)
	private int id;

	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;

	@NotBlank
	@Size(min = 3, max = 100)
	@Column(name = "Code", length = 100)
	private String code;

	@NotBlank
	@Size(min = 3, max = 100)
	@Column(name = "Productsname", length = 100)
	private String productsname;

	@Column(name = "Productscategory", length = 10)
	private String productscategory;

	@NotBlank
	@Size(min = 3, max = 50)
	@Column(name = "Price", length = 50)
	private double price;

	@Column(name = "Status", length = 10)
	private String status;


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProductsname() {
		return this.productsname;
	}

	public void setProductsname(String productsname) {
		this.productsname = productsname;
	}

	public String getProductscategory() {
		return this.productscategory;
	}

	public void setProductscategory(String productscategory) {
		this.productscategory = productscategory;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}

