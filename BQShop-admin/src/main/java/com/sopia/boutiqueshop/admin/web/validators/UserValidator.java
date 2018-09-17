package com.sopia.boutiqueshop.admin.web.validators;

import com.sopia.boutiqueshop.entities.User;
import com.sopia.boutiqueshop.security.SecurityService;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Sopia  on  3:55 AM 24-Nov-17.
 * @project Online BoutiQue Shop
 */
@Component
public class UserValidator implements Validator
{
    @Autowired
    protected MessageSource messageSource;
    @Autowired protected SecurityService securityService;

    @Override
    public boolean supports(Class<?> clazz)
    {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        User user = (User) target;
        String email = user.getEmail();
        User userByEmail = securityService.findUsersByEmail(email);
        if(userByEmail != null){
            errors.rejectValue("email", "error.exists", new Object[]{email}, "Email "+email+" already in use");
        }
    }


}
