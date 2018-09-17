package com.sopia.boutiqueshop.site.SocialLogin;

import com.sopia.boutiqueshop.entities.Customer;
import com.sopia.boutiqueshop.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Arrays;

/**
 * @author Sopia Alfred on 7/15/2018 1:04 PM.
 * @project boutiqueshop.
 */
@Service
public class FacebookSignInAdapter implements SignInAdapter {
    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
        System.out.println(" ====== Sign In adapter");
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(connection.getDisplayName(), null, Arrays.asList(new SimpleGrantedAuthority("FACEBOOK_USER"))));
        return null;
    }
}
