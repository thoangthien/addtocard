package com.poly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@NamedQuery(name = "findByName", query = "SELECT p FROM Product p WHERE p.name LIKE ?1")

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="Products")
public class Product implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String image;
	private Double price;
	@Column(name="Createdate")
	@Temporal(TemporalType.DATE) private Date createdate = new Date();
	private Boolean available = true;
//	@Column(name="DESCRIPTION") private String description;
//	private Integer discount;
	
	@ManyToOne @JoinColumn(name="Categoryid") Category category;
	@JsonIgnore
	@OneToMany(mappedBy = "product") List<OrderDetails> orderdetails;
}
