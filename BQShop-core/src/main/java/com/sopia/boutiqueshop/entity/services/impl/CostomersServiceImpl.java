package com.sopia.boutiqueshop.entity.services.impl;

import com.sopia.boutiqueshop.entities.Customer;
import com.sopia.boutiqueshop.entity.services.CostomersService;
import com.sopia.boutiqueshop.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CostomersServiceImpl implements CostomersService {

    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public List<Customer> getCustomers() {
        return customersRepository.findAll();
    }

    @Override
    public void saveCustomer(Customer customer) {
        customersRepository.save(customer);
    }

    @Override
    public Customer getCustomer(int CustomerId) {
        return customersRepository.findOne(CustomerId);
    }

    @Override
    public void deleteCustomer(int CustomerId) {
        customersRepository.delete(CustomerId);
    }

    @Override
    public Customer findByEmail(String email) {
        return customersRepository.findByEmail(email);
    }
}
