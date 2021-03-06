package com.abc.orderservice.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.abc.orderservice.entity.Order;
import com.abc.orderservice.exception.ResourceNotFoundException;
import com.abc.orderservice.model.Customer;
import com.abc.orderservice.model.OrderDetails;
import com.abc.orderservice.model.Product;
import com.abc.orderservice.repository.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;

	
	@Override
	public Order saveOrder(Order order) {
		
		order.setOrderDate(LocalDate.now());
		
		
		int productId = order.getProductId();
		
		Product product = productService.getProductDetails(productId);
		
		double totalPrice = product.getProductPrice();
	    float orderPrice = (float) (order.getQuantity() * totalPrice);
		order.setOrderAmount(orderPrice);
		
		Order newOrder = orderRepository.save(order);
		return newOrder;
		
	}

	@Override
	public OrderDetails getOrderDetails(int orderId) {
		
		OrderDetails orderDetails = new OrderDetails();
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isEmpty()) {
			throw new ResourceNotFoundException("Order not found with order id:" +orderId);
		}
		
		Order order = optionalOrder.get();
		
		Product product = productService.getProductDetails(order.getProductId());
		
		Customer customer = customerService.getCustomerDetails(order.getCustomerId());
		
		orderDetails.setOrder(order);
		orderDetails.setProduct(product);
		orderDetails.setCustomer(customer);
		return orderDetails;
	}
	
	
}
