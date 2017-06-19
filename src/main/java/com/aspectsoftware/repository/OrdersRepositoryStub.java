package com.aspectsoftware.repository;


import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.aspectsoftware.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class OrdersRepositoryStub implements OrdersRepository{

private Stack<Order> ordersq = new Stack<Order>();	
private Set<String> idCustomers = new HashSet<String>(); 
private static final double TOLERANCE = 0.50000;
private static OrdersRepositoryStub instance = null;

public static OrdersRepositoryStub getInstance() {
    if(instance == null) {
       instance = new OrdersRepositoryStub();
    }
    return instance;
 }


public Stack<Order> getOrdersq() {
	return ordersq;
}

public void create(Order order){
	
	if (!idCustomers.contains(order.getIdCustomer())){
	  ordersq.add(order);
	  idCustomers.add(order.getIdCustomer());
	}
	System.out.println("Inserted in Stack!");
	System.out.println("SIZE "+ ordersq.size()); 
  }
		
public String checkPosition(String idCustomer){
	
	Queue<Order> queueCopy = new LinkedList<Order>(ordersq);
	
  	if (ordersq.size() > 0  &&  idCustomers.contains(idCustomer)){
  	   
  		int index = 0;
  		for (Order order : queueCopy){
  		  if (order.getIdCustomer().equals(idCustomer)){
            long s = System.currentTimeMillis();
            
  		    return  String.valueOf(index) +"  "+String.valueOf( (new BigDecimal(s).subtract(new BigDecimal(order.getWaitingTime()))).divide(new BigDecimal(1000L))  );     	
  		  } 	 
  		  index++;
  		}	
  	}
  	return "No results";   		
}

public String allEntries() throws JsonProcessingException{
	  
	 List<Order> all =  new ArrayList<Order>();
	 Queue<Order> queueCopy = new LinkedList<Order>(ordersq);	
	 ObjectMapper objectMapper = new ObjectMapper();
	 
	 for (Order order : queueCopy){
	     long s = System.currentTimeMillis() / 1000L; 
		 if (Math.abs(s - order.getWaitingTime()) < TOLERANCE){
	    	all.add(order);
	    }
	 }
	 String json = objectMapper.writeValueAsString(all); 
	
	 return json;
}


public Order nextOrder(){

   if (ordersq != null && ordersq.size() > 0)
	   return ordersq.peek();  
   
	return null;
}

public Order cancelOrder(String idCustomer){
	
	 if (ordersq != null && ordersq.size() > 0){
		 
	    for (Order order : ordersq){ 
	       if (order.getIdCustomer().equals(idCustomer)){
	    	     ordersq.remove(order);
	             return order; 
	       }      
	    }
	 }
	return null; 
}


}

