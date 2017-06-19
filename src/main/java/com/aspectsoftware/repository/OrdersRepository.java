package com.aspectsoftware.repository;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import com.aspectsoftware.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrdersRepository {

	void create(Order order);
	
	String checkPosition(String idCustomer);
	
	String allEntries() throws JsonProcessingException;
	
	Order nextOrder();
	
	Order cancelOrder(String idCustomer);
	
	Stack<Order> getOrdersq();
	
}
