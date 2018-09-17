package com.sopia.boutiqueshop.repositories;

import com.sopia.boutiqueshop.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sopia  on  5:01 PM 20-Oct-17.
 * @project Online BoutiQue Shop
 */

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
