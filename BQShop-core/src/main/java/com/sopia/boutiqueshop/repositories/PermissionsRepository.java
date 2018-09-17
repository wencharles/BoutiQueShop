package com.sopia.boutiqueshop.repositories;

import com.sopia.boutiqueshop.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sopia  on  5:05 PM 20-Oct-17.
 * @project Online BoutiQue Shop
 */

@Repository
public interface PermissionsRepository extends JpaRepository<Permission, Integer>{
    //Permissions findByName(String name);
}
