package com.payment.service.impl;

import com.payment.config.Order;
import com.payment.entity.Account;
import com.payment.repository.PaymentRepository;
import com.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private WebClient webClient;

    @Override
    public Double checkBalance(Integer customerId) {
        Optional<Account> accountInfo = paymentRepository.findById(customerId);
        Double balance = 0.0d;
        if(accountInfo.isPresent()){
            balance = accountInfo.get().getAmount();
        }
        return balance;
    }

    @Override
    public Account saveAccount(Account account) {
        //Account account_2 = paymentRepository.save(account);
        return webClient.post()
                .uri("/api/order/place")
                .bodyValue(account)
                .retrieve()
                .bodyToMono(Account.class).block();
    }
}
