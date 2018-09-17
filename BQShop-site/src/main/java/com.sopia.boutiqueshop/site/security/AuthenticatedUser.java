package com.sopia.boutiqueshop.site.security;

import com.sopia.boutiqueshop.entities.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * @author Sopia Alfred on 6/11/2018 2:05 PM.
 * @project boutiqueshop.
 */
public class AuthenticatedUser extends org.springframework.security.core.userdetails.User
{

    private static final long serialVersionUID = 1L;
    private Customer customer;

    public AuthenticatedUser(Customer customer)
    {
        super(customer.getEmail(), customer.getPassword(), getAuthorities(customer));
        this.customer = customer;
    }
    public Customer getCustomer()
    {
        return customer;
    }
    private static Collection<? extends GrantedAuthority> getAuthorities(Customer customer)
    {
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        return authorities;
    }
}
