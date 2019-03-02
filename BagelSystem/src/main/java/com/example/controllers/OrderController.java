package com.example.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.Bagel;
import com.example.domain.Customer;
import com.example.domain.Order;
import com.example.enums.BagelSizeEnum;
import com.example.repository.OrderRepository;



@Controller
@RequestMapping("/orders")
public class OrderController 
{
	private OrderRepository orderRepo;
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String orderGet(ModelMap model)
	{
	    List<Order> orders = orderRepo.findAll();
		
		model.put("orders", orders);
		
		return "orders";
	}

	
	@RequestMapping(value="{orderId}", method=RequestMethod.GET)
	public String orderGet(@PathVariable Long orderId, ModelMap model)
	{
		Order order = orderRepo.findOne(orderId);
		
		calculateOrderFinalPrice(order);
		
		model.put("order", order);
		
		return "orders";
		
	}
	
	private void calculateOrderFinalPrice(Order order)
	  {
	    double finalPrice = 0.0;
	    
	    for (Bagel bagel : order.getBagels())
	    {
	      if (bagel.getPrice() != null)
	      {
	        finalPrice += bagel.getPrice();
	      }
	    }
	    
	    order.setFinalPrice(finalPrice);
	  }
	
	
	@RequestMapping(value="{orderId}", method=RequestMethod.POST)
	public String orderPost (@PathVariable Long orderId, HttpServletRequest request, @ModelAttribute Order order, ModelMap model)
	{
		return "redirect:/orders/"+orderId+"/bagels";
	}
	
	
	@RequestMapping("/{orderId}/reviewOrder")
	  public String reviewOrder (@PathVariable Long orderId, ModelMap model)
	  {
		Order order = orderRepo.findOne(orderId);
		
		calculateOrderFinalPrice(order);
		 
		model.put("order", order);
		
		return "reviewOrder";
		
	  }
	
	@RequestMapping(value="/{orderId}/completeOrder", method=RequestMethod.POST)
	  public String submitOrder (@PathVariable Long orderId, ModelMap model)
	  {
	    Order order = orderRepo.findOne(orderId);
	    
	    order.setCompleted(true);
	    
	    orderRepo.save(order);
	    
	    return "redirect:/orders";
	  }
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String orderPost (HttpServletRequest request, @ModelAttribute Order order, ModelMap model)
	{
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		order.setCustomer(customer);
		
		Order savedOrder = orderRepo.save(order);
		
		return "redirect:/orders/"+savedOrder.getId()+"/bagels";
	}
	
	@Autowired
	public void setOrderRepo(OrderRepository orderRepo)
	{
		this.orderRepo = orderRepo;
	}

}
