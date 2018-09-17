package com.sopia.boutiqueshop.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Sopia  on  11:38 PM 19-Oct-17.
 * @project Online BoutiQue Shop
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService  customUserDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/resources/**", "/webjars/**","/assets/**").permitAll()
                .antMatchers("/", "/forgotPwd","/resetPwd","/upload","/uploadStatus").permitAll()
//            .antMatchers("/resources/**", "/webjars/**","/assets/**").permitAll()
//            .antMatchers("/","/products","/create_product","/users","/create_user","/create_product","/categories","/create_category","/customers","/view_customer","/permissions","/roles","/create_role","/home","/accessDenied","/forgotPwd","/test","/resetPwd").permitAll()
            //.antMatchers(HttpMethod.POST,"/api","/api/**").hasRole("ROLE_ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/home")
            .failureUrl("/login?error")
            .permitAll()
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            //.logoutUrl("/logout")
            .permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/403");
    }

    @Autowired
    public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder());

    }
}
