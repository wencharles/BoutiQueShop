package com.sopia.boutiqueshop.entity.services;

import com.sopia.boutiqueshop.entities.Role;

import java.util.List;

public interface RolesService {

    public List<Role> getRoles();

    public void saveRoles(Role roles);

    public Role getRole(int RolesId);

    public void deleteRoles(int RolesId);
}
