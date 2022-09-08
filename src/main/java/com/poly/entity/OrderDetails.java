package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="Orderdetails")
public class OrderDetails implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id") private Long id;
	@Column(name="Price") private Double price;
	@Column(name="Quantity") private Integer quantity;
	
	@ManyToOne @JoinColumn(name="Productid") Product product;
	@ManyToOne @JoinColumn(name="Orderid") Order order;
}
