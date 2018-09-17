package com.sopia.boutiqueshop.site.web.Controllers;

import com.sopia.boutiqueshop.BQShopException;
import com.sopia.boutiqueshop.common.services.EmailService;
import com.sopia.boutiqueshop.common.services.OrderService;
import com.sopia.boutiqueshop.cunstomer.CustomerService;
import com.sopia.boutiqueshop.entities.*;
import com.sopia.boutiqueshop.site.web.models.Cart;
import com.sopia.boutiqueshop.site.web.models.LineItem;
import com.sopia.boutiqueshop.site.web.models.OrderDTO;
import com.sopia.boutiqueshop.site.web.models.PdfGenaratorUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/*
*
 * @author Sopia Alfred on 6/28/2018 12:57 PM.
 * @project boutiqueshop.
 */
@Controller
public class OrderController extends BQShopSiteBaseController {

    @Autowired private CustomerService customerService;
    @Autowired protected OrderService orderService;
    @Autowired protected EmailService emailService;
    @Autowired PdfGenaratorUtility pdfGenaratorUtility;
    @Override
    protected String getHeaderTitle(){
        return "Order";
    }

    @RequestMapping(value="/orders", method= RequestMethod.POST)
    public String placeOrder(@Valid @ModelAttribute("order") OrderDTO order,
                             BindingResult result, Model model, HttpServletRequest request)
    {
        Cart cart = getOrCreateCart(request);
        if (result.hasErrors()) {
            model.addAttribute("cart", cart);
            return "checkout";
        }

        Order newOrder = new Order();
        String email = getCurrentUser().getCustomer().getEmail();
        Customer customer = customerService.getCustomerByEmail(email);
        newOrder.setCustomer(customer);

        Address deliveryAddress = new Address();
        deliveryAddress.setAddressLine1(order.getAddressLine1());
        deliveryAddress.setAddressLine2(order.getAddressLine2());
        deliveryAddress.setCity(order.getCity());
        deliveryAddress.setState(order.getState());
        deliveryAddress.setZipCode(order.getZipCode());
        deliveryAddress.setCountry(order.getCountry());

        newOrder.setDeliveryAddress(deliveryAddress);

        Address billingAddress = new Address();
        billingAddress.setAddressLine1(order.getAddressLine1());
        billingAddress.setAddressLine2(order.getAddressLine2());
        billingAddress.setCity(order.getCity());
        billingAddress.setState(order.getState());
        billingAddress.setZipCode(order.getZipCode());
        billingAddress.setCountry(order.getCountry());

        newOrder.setBillingAddress(billingAddress);


        Set<OrderItem> orderItems = new HashSet<OrderItem>();
        List<LineItem> lineItems = cart.getItems();
        for (LineItem lineItem : lineItems)
        {
            OrderItem item = new OrderItem();
            item.setProduct(lineItem.getProduct());
            item.setQuantity(lineItem.getQuantity());
            item.setPrice(lineItem.getProduct().getPrice());
            item.setOrder(newOrder);
            orderItems.add(item);
        }

        newOrder.setItems(orderItems);

        Payment payment = new Payment();
        payment.setCcNumber(order.getCcNumber());
        payment.setCvv(order.getCvv());

        newOrder.setPayment(payment);
       Order savedOrder = orderService.createOrder(newOrder);

        this.sendOrderConfirmationEmail(savedOrder);

        request.getSession().removeAttribute("CART_KEY");//clear the cart session
        return "redirect:orderconfirmation?orderNumber="+savedOrder.getOrderNumber();
    }

    protected void sendOrderConfirmationEmail(Order order)
    {
        try {
            emailService.sendEmail(order.getCustomer().getEmail(),
                    "BoutiqueShop - Order Confirmation",
                    "Your order has been placed successfully.\n"
                            + "Order Number : "+order.getOrderNumber());
        } catch (BQShopException e) {
            logger.error(e);
        }
    }

    @RequestMapping(value="/orderconfirmation", method=RequestMethod.GET)
    public String showOrderConfirmation(@RequestParam(value="orderNumber")String orderNumber, Model model){
        Order order = orderService.getOrder(orderNumber);
        model.addAttribute("order", order);
        return "orderconfirmation";
    }

    //pdf
    /*@RequestMapping(value="/orderconfirmation", method=RequestMethod.GET)
    public void pdfReciept(@RequestParam(value="orderNumber")String orderNumber, Model model) throws Exception{
        Order order = orderService.getOrder(orderNumber);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("order",order);
        pdfGenaratorUtility.createPdf("receipt",data);

        }*/


    @RequestMapping(value="/orders/{orderNumber}", method=RequestMethod.GET,produces = "application/pdf")
    public String viewOrder(@PathVariable(value="orderNumber")String orderNumber, Model model)
    {
        Order order = orderService.getOrder(orderNumber);
        model.addAttribute("order", order);
        return "view_order";
    }
}
