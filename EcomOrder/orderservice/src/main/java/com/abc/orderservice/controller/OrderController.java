package com.abc.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.orderservice.entity.Order;
import com.abc.orderservice.model.OrderDetails;
import com.abc.orderservice.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create")
	public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
		
		Order newOrder = orderService.saveOrder(order);
		return new ResponseEntity<>(newOrder,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{oid}")
	public ResponseEntity<OrderDetails> fetchOrderDetails(@PathVariable("oid") int orderId) {
	
		OrderDetails order = orderService.getOrderDetails(orderId);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

}
