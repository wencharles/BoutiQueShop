package com.sopia.boutiqueshop.admin.web.controllers;

import com.sopia.boutiqueshop.admin.security.SecurityUtility;
import com.sopia.boutiqueshop.cunstomer.CustomerService;
import com.sopia.boutiqueshop.entities.Customer;
import com.sopia.boutiqueshop.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Wen  on  4:23 PM 3/30/2018.
 * @project BoutiQueShop
 */
@Controller
@Secured(SecurityUtility.MANAGE_CUSTOMERS)
public class CustomerController extends BQShopAdminBaseController
{
    private static final String viewPrefix = "customers/";

    @Autowired
    private CustomerService customerService;

    @Override
    protected String getHeaderTitle()
    {
        return "Manage Customers";
    }

    @RequestMapping(value="/customers", method= RequestMethod.GET)
    public String listCustomers(Model model) {
        List<Customer> list = customerService.getAllCustomers();
        model.addAttribute("customers",list);
        return viewPrefix+"customers";
    }

    @RequestMapping(value="/customers/{id}", method=RequestMethod.GET)
    public String viewCustomerForm(@PathVariable Integer id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer",customer);
       /* List<Order> orders = customerService.getCustomerOrders(customer.getEmail());
        model.addAttribute("orders", orders);*/
        return viewPrefix+"view_customer";
    }
    @RequestMapping(value="/customerorders/{id}", method=RequestMethod.GET)
    protected String customerOrders(@PathVariable Integer id, Model model){
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        List<Order> orders = customerService.getCustomerOrders(id);
        model.addAttribute("orders", orders);
        //List<Payment> payments = orders.
        return viewPrefix+"customer_orders";
    }

}
