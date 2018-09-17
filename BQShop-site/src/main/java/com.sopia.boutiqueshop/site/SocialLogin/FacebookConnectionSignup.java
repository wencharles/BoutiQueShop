package com.sopia.boutiqueshop.site.SocialLogin;

import com.sopia.boutiqueshop.entities.Customer;
import com.sopia.boutiqueshop.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

/**
 * @author Sopia Alfred on 7/15/2018 1:05 PM.
 * @project boutiqueshop.
 */
@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public String execute(Connection<?> connection) {
        System.out.println("signup === ");
        final Customer user = new Customer();
        user.setFirstName(connection.getDisplayName());
        user.setPassword(randomAlphabetic(8));
        customersRepository.save(user);
        return user.getFirstName();
    }

}
