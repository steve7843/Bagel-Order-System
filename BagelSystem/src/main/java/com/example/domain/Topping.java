package com.example.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Topping 
{
	private Long id;
	private String description;
	private Double price;
	private Set<Bagel> bagels = new HashSet<>();
	
	@Id
	@GeneratedValue
	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public Double getPrice() 
	{
		return price;
	}
	
	public void setPrice(Double price) 
	{
		this.price = price;
	}
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="bagel_topping",
		joinColumns=@JoinColumn(name="topping_id"),
		inverseJoinColumns=@JoinColumn(name="bagel_id"))
	public Set<Bagel> getBagels() 
	{
		return bagels;
	}
	
	public void setBagels(Set<Bagel> bagels) 
	{
		this.bagels = bagels;
	}
	
	

}
