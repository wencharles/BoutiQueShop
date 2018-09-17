package com.sopia.boutiqueshop.entity.services;

import com.sopia.boutiqueshop.entities.Permission;

import java.util.List;

public interface PermissionsService {

    public List<Permission> getPermissions();

    public void savePermissions(Permission permissions);

    public Permission getPermissions(int PermissionsId);

    public void deletePermissions(int PermissionsId);
}
