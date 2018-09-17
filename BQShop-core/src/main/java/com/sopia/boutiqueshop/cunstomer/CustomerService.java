package com.sopia.boutiqueshop.cunstomer;

import com.sopia.boutiqueshop.entities.Customer;
import com.sopia.boutiqueshop.entities.Order;
import com.sopia.boutiqueshop.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wen  on  4:26 PM 3/30/2018.
 * @project BoutiQueShop
 */
@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomersRepository customersRepository;

    public Customer getCustomerByEmail(String email) {
        return customersRepository.findByEmail(email);
    }
    public Customer getCustomerByFirstName(String username) {
        return customersRepository.findByFirstName(username);
    }

    public Customer createCustomer(Customer customer) {
        return customersRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customersRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customersRepository.findOne(id);
    }

    public List<Order> getCustomerOrders(Integer id) {
        //Sort sort = new Sort(Sort.Direction.DESC, "createdOn");
        return customersRepository.getCustomerOrders(id);
    }

}
