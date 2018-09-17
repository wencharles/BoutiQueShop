package com.sopia.boutiqueshop.admin.web.controllers;

import com.sopia.boutiqueshop.BQShopException;
import com.sopia.boutiqueshop.admin.security.SecurityUtility;
import com.sopia.boutiqueshop.common.services.EmailService;
import com.sopia.boutiqueshop.common.services.OrderService;
import com.sopia.boutiqueshop.entities.Order;

import com.sopia.boutiqueshop.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Sopia Alfred on 6/10/2018 2:27 PM.
 * @project boutiqueshop.
 */
@Controller
@Secured(SecurityUtility.MANAGE_ORDERS)
public class OrderController extends BQShopAdminBaseController{
    private static final String viewPrefix = "orders/";

    @Autowired protected EmailService emailService;
    @Autowired protected OrderService orderService;
    @Autowired private TemplateEngine templateEngine;

    @Override
    protected String getHeaderTitle()
    {
        return "Manage Orders";
    }


    @RequestMapping(value="/orders", method=RequestMethod.GET)
    public String listOrders(Model model) {
        List<Order> list = orderService.getAllOrders();
        model.addAttribute("orders",list);
        return viewPrefix+"orders";
    }

    @RequestMapping(value="/ordereditems", method=RequestMethod.GET)
    public String listOrdereditems(HttpServletRequest request,Model model) {
        //OrderItem orderItem = orderService.getAllOrderItmes(request);
        List<OrderItem> list = orderService.getAllOrderItmes();
        model.addAttribute("ordereditems",list);
        //model.addAttribute("itemordered",)
        return viewPrefix+"ordereditems";
    }

    @RequestMapping(value="/orders/{orderNumber}", method=RequestMethod.GET)
    public String editOrderForm(@PathVariable String orderNumber, Model model) {
        Order order = orderService.getOrder(orderNumber);
        model.addAttribute("order",order);
        return viewPrefix+"edit_order";
    }

    @RequestMapping(value="/orders/{orderNumber}", method=RequestMethod.POST)
    public String updateOrder(@ModelAttribute("order") Order order, BindingResult result,
                              Model model, RedirectAttributes redirectAttributes) {
        Order persistedOrder = orderService.updateOrder(order);
        this.sendOrderStatusUpdateEmail(persistedOrder);
        logger.debug("Updated order with orderNumber : {}", persistedOrder.getOrderNumber());
        redirectAttributes.addFlashAttribute("info", "Order updated successfully");
        return "redirect:/orders";
    }
    @RequestMapping(value="/vieworder/{orderNumber}", method=RequestMethod.GET)
    public String viewOrder(@PathVariable String orderNumber, Model model){
        Order order = orderService.getOrder(orderNumber);
        model.addAttribute("order", order);
        return viewPrefix+"view_order";
    }

    protected void sendOrderStatusUpdateEmail(Order order)
    {
        try {

            // Prepare the evaluation context
            final Context ctx = new Context();
            ctx.setVariable("order", order);

            // Create the HTML body using Thymeleaf
            final String htmlContent = this.templateEngine.process("order-status-update-email", ctx);

            emailService.sendEmail(order.getCustomer().getEmail(),
                    "BQSHOP - Order Status Update",
                    htmlContent);
        } catch (BQShopException e) {
            logger.error(e);
        }
    }
}
