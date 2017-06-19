package com.aspectsoftware.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public final class Order implements Serializable, Comparator<Order>{

	private String idCustomer;
	private String quantity;
	private String productName;
	private Long waitingTimeSeconds;
	
	
	public String getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getWaitingTime() {
		return waitingTimeSeconds;
	}
	public void setWaitingTime(Long waitingTimeSeconds) {
		this.waitingTimeSeconds = waitingTimeSeconds;
	}
	
	@Override
	public int compare(Order o1, Order o2) {
		
     return Long.valueOf(o1.getIdCustomer()).compareTo(Long.valueOf(o2.getIdCustomer()));
	}
	

	
}
