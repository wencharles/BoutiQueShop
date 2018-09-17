package com.sopia.boutiqueshop.entity.services.impl;

import com.sopia.boutiqueshop.entities.Permission;
import com.sopia.boutiqueshop.entity.services.PermissionsService;
import com.sopia.boutiqueshop.repositories.PermissionsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PermissionsServiceImpl implements PermissionsService {

    @Autowired
    private PermissionsRepository permissionsRepository;

    @Override
    public List<Permission> getPermissions() {
        return permissionsRepository.findAll();
    }

    @Override
    public void savePermissions(Permission permissions) {
    permissionsRepository.save(permissions);
    }

    @Override
    public Permission getPermissions(int PermissionsId) {
        return permissionsRepository.getOne(PermissionsId);
    }

    @Override
    public void deletePermissions(int PermissionsId) {
    permissionsRepository.delete(PermissionsId);
    }
}
