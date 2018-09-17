
package com.sopia.boutiqueshop.repositories;
import com.sopia.boutiqueshop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author wencharles
 */
public interface CategoriesRepository extends JpaRepository<Category, Integer>{
    Category getByName(String name);
}
