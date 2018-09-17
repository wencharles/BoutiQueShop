package com.sopia.boutiqueshop.site.web.validators;

import com.sopia.boutiqueshop.cunstomer.CustomerService;
import com.sopia.boutiqueshop.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Sopia Alfred on 6/11/2018 10:12 PM.
 * @project boutiqueshop.
 */
@Component
public class CustomerValidator implements Validator
{
    @Autowired
    private CustomerService custmoerService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        Customer customerByEmail = custmoerService.getCustomerByEmail(customer.getEmail());
        if(customerByEmail != null){
            errors.rejectValue("email", "error.exists", new Object[]{customer.getEmail()}, "Email "+customer.getEmail()+" already in use");
        }
    }

}
