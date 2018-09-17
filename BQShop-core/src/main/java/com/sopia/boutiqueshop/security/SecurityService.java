package com.sopia.boutiqueshop.security;

import com.sopia.boutiqueshop.BQShopException;
import com.sopia.boutiqueshop.entities.Permission;
import com.sopia.boutiqueshop.entities.Role;
import com.sopia.boutiqueshop.entities.User;
import com.sopia.boutiqueshop.repositories.PermissionsRepository;
import com.sopia.boutiqueshop.repositories.RolesRepository;
import com.sopia.boutiqueshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Sopia  on  12:16 AM 20-Oct-17.
 * @project Online BoutiQue Shop
 */


@Service
@Transactional
public class SecurityService {

    @Autowired UserRepository userRepository;
    @Autowired PermissionsRepository permissionsRepository;
    @Autowired RolesRepository rolesRepository;

//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    PermissionsRepository permissionsRepository;
    //Find user by emails

    public User findUsersByEmail(String email){
        return userRepository.findByEmail(email);
    }

    //Check the email provided for reseting password
    public String resetPassword(String email)
    {
        User users = findUsersByEmail(email);
        if(users == null)
        {
            throw new BQShopException("Invalid email address");
        }

        //Set password reset token when email prvided is found in users table
        String uuid = UUID.randomUUID().toString();
        users.setPasswordResetToken(uuid);
        return uuid;// returtn the Password reset token
    }

    //Setting a new password method
    public void updatePassword(String email, String token, String password)
    {
        User user = findUsersByEmail(email);
        //Get excuted when user with provided email doeasnt exist on recoards.
        if(user == null)
        {
            throw new BQShopException("Invalid email address");
        }
        //Check if the provided reset token matches the one set on the table
        if(!StringUtils.hasText(token) || !token.equals(user.getPasswordResetToken())){
            throw new BQShopException("Invalid password reset token");
        }
        user.setPassword(password);//set password to new one
        user.setPasswordResetToken(null);//clear the reset token
    }

    //Check the reset token is sett against the provided email
    public boolean verifyPasswordResetToken(String email, String token)
    {
        User user = findUsersByEmail(email);
        if(user == null)
        {
            throw new BQShopException("Invalid email address");
        }
        if(!StringUtils.hasText(token) || !token.equals(user.getPasswordResetToken())){
            return false;
        }
        return true;
    }

    public List<Permission> getAllPermissions() {
        return permissionsRepository.findAll();
    }


////    Below code is to be commeted
//    public Users getUserById(Integer id)
//    {
//        return userRepository.findOne(id);
//    }
//
//    public List<Users> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    //public List<Permissions> getAllPermissions() {
//        return permissionsRepository.findAll();
//    }

//    public List<Permission> getAllPermissions() {
//        return permissionRepository.findAll();
//    }

    public List<Role> getAllRoles() {
        return rolesRepository.findAll();
    }

    public Role getRoleByName(String roleName)
    {
        return rolesRepository.findByName(roleName);
    }

    public Role createRole(Role role)
    {
        Role roleByName = getRoleByName(role.getName());
        if(roleByName != null){
            throw new BQShopException("Role "+role.getName()+" already exist");
        }
        List<Permission> persistedPermissions = new ArrayList<>();
        List<Permission> permissions = role.getPermissions();
        if(permissions != null){
            for (Permission permission : permissions) {
                if(permission.getId() != null)
                {
                    persistedPermissions.add(permissionsRepository.findOne(permission.getId()));
                }
            }
        }

        role.setPermissions(persistedPermissions);
        return rolesRepository.save(role);
    }

    public Role updateRole(Role role)
    {
        Role persistedRole = getRoleById(role.getId());
        if(persistedRole == null){
            throw new BQShopException("Role "+role.getId()+" doesn't exist");
        }
        persistedRole.setDescription(role.getDescription());
        List<Permission> updatedPermissions = new ArrayList<>();
        List<Permission> permissions = role.getPermissions();
        if(permissions != null){
            for (Permission permission : permissions) {
                if(permission.getId() != null)
                {
                    updatedPermissions.add(permissionsRepository.findOne(permission.getId()));
                }
            }
        }
        persistedRole.setPermissions(updatedPermissions);
        return rolesRepository.save(persistedRole);
    }

    public Role getRoleById(Integer id) {
        return rolesRepository.findOne(id);
    }

    public User getUserById(Integer id)
    {
        return userRepository.findOne(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user)
    {
        User userByEmail = findUsersByEmail(user.getEmail());
        if(userByEmail != null){
            throw new BQShopException("Email "+user.getEmail()+" already in use");
        }
        List<Role> persistedRoles = new ArrayList<>();
        List<Role> roles = user.getRoles();
        if(roles != null){
            for (Role role : roles) {
                if(role.getId() != null)
                {
                    persistedRoles.add(rolesRepository.findOne(role.getId()));
                }
            }
        }
        user.setRoles(persistedRoles);

        return userRepository.save(user);
    }

    public User updateUser(User user)
    {
        User persistedUser = getUserById(user.getId());
        if(persistedUser == null){
            throw new BQShopException("User "+user.getId()+" doesn't exist");
        }

        List<Role> updatedRoles = new ArrayList<>();
        List<Role> roles = user.getRoles();
        if(roles != null){
            for (Role role : roles) {
                if(role.getId() != null)
                {
                    updatedRoles.add(rolesRepository.findOne(role.getId()));
                }
            }
        }
        persistedUser.setRoles(updatedRoles);
        return userRepository.save(persistedUser);
    }

}
