package com.payment.controller;

import com.payment.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.payment.service.PaymentService;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;

	@Autowired
	private WebClient webClient;

	@GetMapping("/say")
	public Mono<String> welcome(){
			return webClient.get()
					.uri("/api/order/welcome")
					.retrieve()
					.bodyToMono(String.class);
	};

	@GetMapping("/balance/{id}")
	public ResponseEntity<Double> checkBalance(@PathVariable Integer id){
		Double balance = paymentService.checkBalance(id);
		return ResponseEntity.ok(balance);
	}

	@PostMapping("/save")
	public ResponseEntity<Account> createAccount(@RequestBody Account account){
		Account savedAccount  = paymentService.saveAccount(account);
		return ResponseEntity.ok(savedAccount);
	}
}
