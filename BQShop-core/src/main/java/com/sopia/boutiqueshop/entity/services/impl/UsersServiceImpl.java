package com.sopia.boutiqueshop.entity.services.impl;

import com.sopia.boutiqueshop.entities.User;
import com.sopia.boutiqueshop.entity.services.UsersService;
import com.sopia.boutiqueshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User users) {
        userRepository.save(users);
    }

    @Override
    public User getUser(int UsersId) {
        return userRepository.getOne(UsersId);
    }

    @Override
    public void deleteUser(int UsersId) {
    userRepository.delete(UsersId);
    }
}
