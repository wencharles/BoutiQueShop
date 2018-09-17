package com.sopia.boutiqueshop.entity.services;

import com.sopia.boutiqueshop.entities.Payment;

import java.util.List;

public interface PaymentsService {

    public List<Payment> getPayments();

    public void savePayments(Payment payment);

    public Payment getPayments(int PaymentId);

//    public void deletePayments(int PaymentId);
}
