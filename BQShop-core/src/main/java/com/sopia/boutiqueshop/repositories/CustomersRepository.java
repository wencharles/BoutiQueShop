

package com.sopia.boutiqueshop.repositories;
import com.sopia.boutiqueshop.entities.Customer;
import com.sopia.boutiqueshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmail(String email);

    Customer findByFirstName(String username);


    @Query("select o from Order o where o.customer.id=?1")
    List<Order> getCustomerOrders(Integer id);
}
