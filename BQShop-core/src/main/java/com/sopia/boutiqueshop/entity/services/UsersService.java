package com.sopia.boutiqueshop.entity.services;

import com.sopia.boutiqueshop.entities.User;

import java.util.List;

public interface UsersService {

    public List<User> getUsers();

    public void saveUser(User users);

    public User getUser(int UsersId);

    public void deleteUser(int UsersId);
}
