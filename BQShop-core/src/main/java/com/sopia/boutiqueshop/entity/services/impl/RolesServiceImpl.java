package com.sopia.boutiqueshop.entity.services.impl;

import com.sopia.boutiqueshop.entities.Role;
import com.sopia.boutiqueshop.entity.services.RolesService;
import com.sopia.boutiqueshop.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<Role> getRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public void saveRoles(Role roles) {
    rolesRepository.save(roles);
    }

    @Override
    public Role getRole(int RolesId) {
        return rolesRepository.getOne(RolesId);
    }

    @Override
    public void deleteRoles(int RolesId) {
        rolesRepository.delete(RolesId);
    }
}
