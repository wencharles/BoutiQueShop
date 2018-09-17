package com.sopia.boutiqueshop.admin.web.validators;

import com.sopia.boutiqueshop.entities.Role;
import com.sopia.boutiqueshop.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * @author Sopia  on  4:05 AM 25-Nov-17.
 * @project Online BoutiQue Shop
 */

@Component
public class RoleValidator implements Validator {

    @Autowired protected MessageSource messageSource;
    @Autowired protected SecurityService securityService;

    @Override
    public boolean supports(Class<?> clazz)
    {
        return Role.class.isAssignableFrom(clazz);
    }

        @Override
        public void validate(Object target, Errors errors){
            Role roles = (Role) target;
            String name = roles.getName();
            Role rolesByName= securityService.getRoleByName(name);

            if(rolesByName !=null){
                errors.rejectValue("name","error.exist",new Object[]{roles}, "Role"+name+"already exist");
            }

        }

}
