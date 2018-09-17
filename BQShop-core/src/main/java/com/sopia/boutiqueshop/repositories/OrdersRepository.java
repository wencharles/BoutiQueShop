
package com.sopia.boutiqueshop.repositories;
import com.sopia.boutiqueshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 
 * @author wencharles 
 */
public interface OrdersRepository extends JpaRepository<Order, Integer>{
    Order findByOrderNumber(String orderNumber);

}
