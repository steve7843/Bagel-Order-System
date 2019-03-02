package com.example.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Bagel implements Comparable<Bagel>
{
	private Long id;
	private String size;
	private String breadType;
	private Order order;
	private Double price;
	private Set<Topping> toppings = new HashSet<>();
	
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
	
	public String getSize() 
	{
		return size;
	}
	
	public void setSize(String size) 
	{
		this.size = size;
	}
	
	public String getBreadType() 
	{
		return breadType;
	}
	
	public void setBreadType(String breadType) 
	{
		this.breadType = breadType;
	}
	
	@ManyToOne
	public Order getOrder() 
	{
		return order;
	}
	
	public void setOrder(Order order) 
	{
		this.order = order;
	}
	
	public Double getPrice() 
	{
		return price;
	}
	
	public void setPrice(Double price) 
	{
		this.price = price;
	}
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="bagels")
	public Set<Topping> getToppings() 
	{
		return toppings;
	}
	
	public void setToppings(Set<Topping> toppings) 
	{
		this.toppings = toppings;
	}

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bagel other = (Bagel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(Bagel otherBagel) 
	{
		if (this.getId() == null)
			return 1;
		if (otherBagel.getId() == null)
			return -1;
		return this.getId().compareTo(otherBagel.getId());
	}
	
	
	
	
	
	
	
	
	
}
