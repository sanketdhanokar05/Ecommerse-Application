package com.abc.orderservice.service;

import com.abc.orderservice.entity.Order;
import com.abc.orderservice.model.OrderDetails;

public interface OrderService {
	
	public Order saveOrder(Order order);
	
	public OrderDetails getOrderDetails(int orderId);

}
