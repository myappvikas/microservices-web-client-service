package com.order.controller;

import com.order.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.order.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@GetMapping("/welcome")
	public String welcome(){
		System.out.println("Order service");
		return "Welcoming into order service.";
	}
	
	@PostMapping("/place")
	public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
		Order orderInfo = orderService.placeOrder(order);
		return ResponseEntity.ok(orderInfo);
	}
}
