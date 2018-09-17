package com.sopia.boutiqueshop.admin.security;

import com.sopia.boutiqueshop.entities.Permission;
import com.sopia.boutiqueshop.entities.Role;
import com.sopia.boutiqueshop.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Sopia  on  8:01 PM 23-Oct-17.
 * @project Online BoutiQue Shop
 */

//Extends the userdetails.user class to the class
public class AuthenticatedUser extends org.springframework.security.core.userdetails.User
{

    private static final long serialVersionUID = 1L;
    private User user;



    //get the users details from the db and authenticate
    public AuthenticatedUser(User user)
    {
        super(user.getEmail(), user.getPassword(), getAuthorities(user));
        this.user = user;
    }


//return the authenticated user
    public User getUser(){
        return user;
    }
//
    private static Collection<? extends GrantedAuthority> getAuthorities(User user)
    {
        Set<String> roleAndPermissions = new HashSet<>();
        List<Role> roles = user.getRoles();


        //GrantedAuthorities of all its roles
        // return operations from the
        // userdetails.getAuthorities() method

        for (Role role : roles)
        {
            roleAndPermissions.add(role.getName());
            Collection<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions)
            {
                roleAndPermissions.add("ROLE_"+permission.getName());
            }
        }
        String[] roleNames = new String[roleAndPermissions.size()];
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleAndPermissions.toArray(roleNames));
        return authorities;


    }
}
