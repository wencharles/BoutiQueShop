package com.sopia.boutiqueshop.site.web.Controllers;

import com.sopia.boutiqueshop.cunstomer.CustomerService;
import com.sopia.boutiqueshop.entities.Customer;
//import com.sopia.boutiqueshop.entities.Order;
import com.sopia.boutiqueshop.entities.Order;
import com.sopia.boutiqueshop.entities.Payment;
import com.sopia.boutiqueshop.site.web.validators.CustomerValidator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Sopia Alfred on 6/11/2018 12:58 PM.
 * @project boutiqueshop.
 */

@Controller
public class CustomerController extends BQShopSiteBaseController {

    @Autowired private CustomerService customerService;
    @Autowired private CustomerValidator customerValidator;
    @Autowired protected  PasswordEncoder passwordEncoder;

   /* @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
    @Override
    protected String getHeaderTitle()
    {
        return "Login/Register";
    }

    @RequestMapping(value="/register", method=RequestMethod.GET)
    protected String registerForm(Model model)
    {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    protected String register(@Valid @ModelAttribute("customer") Customer customer, BindingResult result,
                              Model model, RedirectAttributes redirectAttributes){
        customerValidator.validate(customer, result);
        if(result.hasErrors()){
            return "register";
        }
        String password = customer.getPassword();
        String encodedPwd = passwordEncoder.encode(password);
        customer.setPassword(encodedPwd);

        Customer persistedCustomer = customerService.createCustomer(customer);
        logger.debug("Created new Customer with id : {} and email : {}", persistedCustomer.getId(), persistedCustomer.getEmail());
        redirectAttributes.addFlashAttribute("info", "Customer created successfully");
        return "redirect:/login";
    }

    @RequestMapping(value="/myAccount", method=RequestMethod.GET)
    protected String myAccount(Model model){
        Integer id = getCurrentUser().getCustomer().getId();
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        List<Order> orders = customerService.getCustomerOrders(id);
        model.addAttribute("orders", orders);
        //List<Payment> payments = orders.
        return "myAccount";
    }
}
