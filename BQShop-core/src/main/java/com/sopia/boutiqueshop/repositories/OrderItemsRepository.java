
package com.sopia.boutiqueshop.repositories;
import com.sopia.boutiqueshop.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wencharles
 */
public interface OrderItemsRepository extends JpaRepository<OrderItem, Integer>{

    OrderItem findByProductId(Integer id);
    
}
