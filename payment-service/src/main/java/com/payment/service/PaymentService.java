package com.payment.service;

import com.payment.entity.Account;

public interface PaymentService {

   Double checkBalance(Integer customerId);

   Account saveAccount(Account account);

}
