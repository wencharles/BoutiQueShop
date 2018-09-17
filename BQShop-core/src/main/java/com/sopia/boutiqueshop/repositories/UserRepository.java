package com.sopia.boutiqueshop.repositories;

import com.sopia.boutiqueshop.entities.Role;
import com.sopia.boutiqueshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sopia  on  12:24 AM 20-Oct-17.
 * @project Online BoutiQue Shop
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    User findByEmail(String email);
    //@Query("select o from Role o where o.user.email=?1")
    //List<Role> getUserRoles(String email);
}
