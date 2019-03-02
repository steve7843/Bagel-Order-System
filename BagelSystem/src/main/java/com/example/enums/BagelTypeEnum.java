package com.example.enums;

public enum BagelTypeEnum 
{
	BLUEBERRY("blueberry", 1.0),
	CHOCOLATE_CHIP("chocolate chip", 1.0),
	CINNAMON_RAISIN("cinnamon raisin", 1.5),
	CRANBERRY("cranberry", 1.0),
	GARLIC("garlic", 1.25),
	HONEY_WHOLE_WHEAT("honey whole wheat", 1.75),
	ONION("onion", 1.0),
	PLAIN("plain", 0.0),
	POPPY_SEED("poppy seed", 2.0),
	SEASAME_SEED("seasame seed", 2.0),
	PUMPERNICKEL("pumpernickel",1.5),
	PRETZEL("pretzel", 1.5);
	
	
	private String description;
	private Double price;
	
	
	BagelTypeEnum(String description ,Double price)
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
