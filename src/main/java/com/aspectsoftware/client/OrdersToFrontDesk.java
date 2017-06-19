package com.aspectsoftware.client;


import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.aspectsoftware.model.Order;
import com.aspectsoftware.repository.OrdersRepository;
import com.aspectsoftware.repository.OrdersRepositoryStub;

public class OrdersToFrontDesk {

    PriorityQueue<Order> cartList =new PriorityQueue<Order>(10, new Comparator<Order>() {
        public int compare(Order o1, Order o2) {
            if (Long.valueOf(o1.getIdCustomer())  < Long.valueOf(o2.getIdCustomer())) return -1;
            if (Long.valueOf(o1.getIdCustomer()) > Long.valueOf(o2.getIdCustomer())) return 1;
            return 0;
        }
    });
    
    private OrdersRepositoryStub orderRepository;
    

	public PriorityQueue<Order> ordersOnFrontDesk(){
		//iterate over Queue
		if (OrdersRepositoryStub.getInstance().getOrdersq() !=  null && !OrdersRepositoryStub.getInstance().getOrdersq().isEmpty()){
		  
		   int index = 0;
		  while (index <= 25  && !OrdersRepositoryStub.getInstance().getOrdersq().isEmpty()){ 	
			  cartList.add((OrdersRepositoryStub.getInstance().getOrdersq().peek()));
			index++;  
		  }
			
		}
	   return cartList;
	}
	
   public void  displayFrontDesk(){
	   
	 if (cartList != null && cartList.size() > 0)  
	    for (Order  order : cartList){	
	      System.out.println(order.toString());	
	    }	   	   
   }
}
