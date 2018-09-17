
package com.sopia.boutiqueshop.repositories;

import com.sopia.boutiqueshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author wencharles
 */
public interface ProductsRepository extends JpaRepository<Product, Integer>{
    Product findByName(String name);

    Product findBySku(String sku);
    @Query("select p from Product p where p.name like ?1 or p.sku like ?1 or p.description like ?1")
    List<Product> search(String query);

    //@Query("select p from Product p where p.price <= ?1 ")
    @Query("SELECT p FROM Product p WHERE p.price <= :price")
    //@Query(" SELECT * FROM bqshop.products  WHERE price <= price")
    List<Product> findByPriceLessThan(BigDecimal price);

}
