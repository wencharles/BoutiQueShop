
 
package com.sopia.boutiqueshop.repositories;
import com.sopia.boutiqueshop.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wencharles
 */
public interface PaymentsReposiory extends JpaRepository<Payment, Integer>{
    
}
