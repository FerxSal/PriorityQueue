package com.aspectsoftware;

import java.util.Timer;
import java.util.TimerTask;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aspectsoftware.client.OrdersToFrontDesk;

/**
* Root resource (exposed at "myresource" path)
*/
@Path("myresource")
public class MyResource {

/**
* Method handling HTTP GET requests. The returned object will be sent
* * to the client as "text/plain" media type.
*
*
* @return String that will be returned as a text/plain response.
* */
@GET
@Produces(MediaType.TEXT_PLAIN)
public String getIt() {
	
	 Timer t = new Timer();
	 final OrdersToFrontDesk frontdesk = new OrdersToFrontDesk(); 
	 
     t.schedule(new TimerTask() {
         @Override
         public void run() {
        	 frontdesk.displayFrontDesk();
         }
     }, 0, 300000);
 	
	return "Got it!";
	    
  }
	
}
