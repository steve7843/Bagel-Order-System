package com.example.enums;

public enum BagelSizeEnum 
{
	HALF_DOZEN("half dozen", 5.0),
	DOZEN("dozen", 10.0),
	BOX("box", 15.0);
	
	private String description;
	private Double price;
	
	BagelSizeEnum(String description, Double price)
	{
		this.description = description;
		this.price = price;
	}

	public String getDescription() 
	{
		return description;
	}

	public Double getPrice() 
	{
		return price;
	}
	
	
	
	
	
	
	
}
