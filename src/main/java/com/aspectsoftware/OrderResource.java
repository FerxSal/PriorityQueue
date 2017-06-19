package com.aspectsoftware;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aspectsoftware.model.Order;
import com.aspectsoftware.repository.OrdersRepository;
import com.aspectsoftware.repository.OrdersRepositoryStub;
import com.fasterxml.jackson.core.JsonProcessingException;


@Path("orders/")
public class OrderResource {

	private OrdersRepositoryStub orderRepository;
	
	@POST
	@Path("order/{idCustomer}/{qt}")
	@Produces(MediaType.APPLICATION_JSON)
	public Order createOrder(@PathParam("idCustomer") String idCustomer,@PathParam("qt") String qt){

		Order order = new Order();
		order.setIdCustomer(idCustomer);
		order.setQuantity(qt);
		order.setProductName("Rubber Duck");
		long secs = System.currentTimeMillis();
		order.setWaitingTime(secs);
			
		OrdersRepositoryStub.getInstance().create(order);
		
		return order;
	}
	
	
	@GET
	@Path("checkpos/{idCustomer}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String CheckPosition(@PathParam("idCustomer") String idCustomer){
	
	   return OrdersRepositoryStub.getInstance().checkPosition(idCustomer);	 
	   
	}
	
	@GET
	@Path("allorders")
	@Produces(MediaType.APPLICATION_JSON)
	public String allOrders() throws JsonProcessingException{
		
		//return Response.ok(OrdersRepositoryStub.getInstance().allEntries()).build(); 
		return OrdersRepositoryStub.getInstance().allEntries();
	
	}
	
	
	@GET
	@Path("nextorder")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Order nextOrder(){
		
	    Order nextOrder = OrdersRepositoryStub.getInstance().nextOrder();
	    if (nextOrder != null)
	      return nextOrder;
	 	
	    return null;
	}
	
	@DELETE
	@Path("ordercancel/{idCustomer}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Order cancelOrder(@PathParam("idCustomer") String idCustomer){
	
		  Order cancelOrder = OrdersRepositoryStub.getInstance().cancelOrder(idCustomer);
		  if (cancelOrder != null)
		    return cancelOrder;
		  
	  return null; 	  
	}
	
	//Just for test the server !
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		
		return "Got it Orders Resource!";
		    
	  }	
	
}
