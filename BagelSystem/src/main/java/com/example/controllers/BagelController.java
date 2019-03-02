package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.Bagel;
import com.example.domain.Order;
import com.example.domain.Topping;
import com.example.enums.BagelSizeEnum;
import com.example.enums.BagelTypeEnum;
import com.example.repository.BagelRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ToppingRepository;

@Controller
@RequestMapping("/orders/{orderId}/bagels")
public class BagelController 
{
	
	private ToppingRepository toppingRepo;
	private OrderRepository orderRepo;
	private BagelRepository bagelRepo;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String bagelGet(@PathVariable Long orderId, ModelMap model)
	{
		model.put("bagelSizes", BagelSizeEnum.values());
		model.put("bagelTypes", BagelTypeEnum.values());
		model.put("toppings", toppingRepo.findAll());
		
		Bagel bagel = new Bagel();
		
		model.put("bagel", bagel);
		
		return "bagels";
	}
	
	@RequestMapping(value="/{bagelId}/delete", method=RequestMethod.GET)
	  public String bagelGet (@PathVariable Long orderId, @PathVariable Long bagelId, ModelMap model)
	  {
	    Order order = orderRepo.findOne(orderId);

	    Bagel bagelToDelete = null;
	    
	    for (Bagel bagel : order.getBagels())
	    {
	      if (bagel.getId().equals(bagelId))
	      {
	    	  bagelToDelete = bagel;
	    	  break;
	      }
	    }

	    bagelToDelete.getToppings().clear();
	    
	    order.getBagels().remove(bagelToDelete);
	    if (bagelToDelete != null)
	    {
	    	bagelToDelete.setOrder(null);
	    }
	    
	    orderRepo.save(order);
	    
	    return "redirect:/orders/" + orderId;
	  }
	
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String bagelPost(@ModelAttribute Bagel bagel, @PathVariable Long orderId, ModelMap model)
	{
		Order order = orderRepo.findOne(orderId);
		
		double bagelPrice = 0.0;
		
		for (Topping topping : bagel.getToppings())
		{
			topping.getBagels().add(bagel);
			bagelPrice += topping.getPrice();
		}
		
		for (BagelTypeEnum bagelTypeEnum : BagelTypeEnum.values())
		{
			if (bagelTypeEnum.getDescription().equals(bagel.getBreadType()))
			{
				bagelPrice += bagelTypeEnum.getPrice();
			}
		}
		
		for (BagelSizeEnum bagelSizeEnum : BagelSizeEnum.values())
		{
			if (bagelSizeEnum.getDescription().equals(bagel.getSize()))
			{
				bagelPrice += bagelSizeEnum.getPrice();
			}
			
		}
		
		bagel.setPrice(bagelPrice);
		bagel.setOrder(order);
		order.getBagels().add(bagel);
		
		orderRepo.save(order);
		return "redirect:/orders/"+orderId;
		
	}
	
	@Autowired
	public void setToppingRepo(ToppingRepository toppingRepo)
	{
		this.toppingRepo = toppingRepo;
	}
	
	@Autowired
	public void setOrderRepo(OrderRepository orderRepo)
	{
		this.orderRepo = orderRepo;
	}

	@Autowired
	public void setBagelRepo(BagelRepository bagelRepo) {
		this.bagelRepo = bagelRepo;
	}
	
	
	
}
