package com.sopia.boutiqueshop.admin.security;

import com.sopia.boutiqueshop.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sopia.boutiqueshop.entities.User;
import com.sopia.boutiqueshop.security.SecurityService;

/**
 * @author Sopia  on  12:13 AM 20-Oct-17.
 * @project Online BoutiQue Shop
 */
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService
{

    @Autowired
    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String userName)
        throws UsernameNotFoundException {
        User user = securityService.findUsersByEmail(userName);
        if(user == null){
            throw new UsernameNotFoundException("Email "+userName+" not found");
        }
        return new AuthenticatedUser(user);
    }

}
