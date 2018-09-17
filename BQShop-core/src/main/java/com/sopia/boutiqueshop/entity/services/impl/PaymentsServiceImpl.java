package com.sopia.boutiqueshop.entity.services.impl;

import com.sopia.boutiqueshop.entities.Payment;
import com.sopia.boutiqueshop.entity.services.PaymentsService;
import com.sopia.boutiqueshop.repositories.PaymentsReposiory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    private PaymentsReposiory paymentsReposiory;

    @Override
    public List<Payment> getPayments() {
        return paymentsReposiory.findAll();
    }

    @Override
    public void savePayments(Payment payment) {
        paymentsReposiory.save(payment);
    }

    @Override
    public Payment getPayments(int PaymentId) {
        return paymentsReposiory.getOne(PaymentId);
    }

//    @Override
//    public void deletePayments(int PaymentId) {
//    paymentsReposiory.delete(PaymentId);
//    }
}
