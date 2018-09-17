package com.sopia.boutiqueshop.site.security;

import com.sopia.boutiqueshop.cunstomer.CustomerService;
import com.sopia.boutiqueshop.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sopia Alfred on 6/11/2018 2:04 PM.
 * @project boutiqueshop.
 */
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    CustomerService customerService;
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Customer customer = customerService.getCustomerByEmail(email);
        if(customer == null){
            throw new UsernameNotFoundException("Email "+email+" not found");
        }
        return new AuthenticatedUser(customer);
    }

}
