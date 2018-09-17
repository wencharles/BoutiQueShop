package com.sopia.boutiqueshop.entity.services;

import com.sopia.boutiqueshop.entities.Customer;

import java.util.List;

public interface CostomersService {

    public List<Customer> getCustomers();

    public void saveCustomer(Customer customer);

    public Customer getCustomer(int CustomerId);

    public void deleteCustomer(int CustomerId);

    public Customer findByEmail(String email);
}
