package com.sopia.boutiqueshop.common.services;

import com.sopia.boutiqueshop.entities.Customer;
import com.sopia.boutiqueshop.entities.Order;
import com.sopia.boutiqueshop.entities.Role;
import com.sopia.boutiqueshop.entities.User;
import com.sopia.boutiqueshop.repositories.CustomersRepository;
import com.sopia.boutiqueshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sopia Alfred on 6/11/2018 10:37 AM.
 * @project boutiqueshop.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findOne(id);
    }

//    public List<Role> getUserRoles(String email) {
//        return userRepository.getUserRoles(email);
//    }
}

