package com.sopia.boutiqueshop.site.web.Controllers;

import com.sopia.boutiqueshop.catalog.CatalogService;
import com.sopia.boutiqueshop.cunstomer.CustomerService;
import com.sopia.boutiqueshop.entities.Customer;
import com.sopia.boutiqueshop.entities.Product;
import com.sopia.boutiqueshop.site.web.models.Cart;
import com.sopia.boutiqueshop.site.web.models.LineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sopia Alfred on 6/16/2018 17:56 PM.
 * @project boutiqueshop.
 */
@Controller
public class CartController extends BQShopSiteBaseController
{
    @Autowired
    private CatalogService catalogService;

    @Override
    protected String getHeaderTitle()
    {
        return "Cart";
    }


    @RequestMapping(value="/cart", method=RequestMethod.GET)
    public String showCart(HttpServletRequest request, Model model)
    {
        Cart cart = getOrCreateCart(request);
        model.addAttribute("cart", cart);
        return "cart";
    }

    //add producst to cart
    @ResponseBody
    @RequestMapping(value="/cart/items", method=RequestMethod.POST)
    public void addItemToCart(@RequestBody Product product, HttpServletRequest request, RedirectAttributes redirectAttributes) //recieve an ajax call from addtocart function
    {
        Cart cart = getOrCreateCart(request);
        /*Product p = catalogService.getProductById(productId);
        LineItem newLineItem = new LineItem(p.getName(), p.getId(), p.getPrice(), 1);
        cart.addItemToCart(newLineItem);
        return cart;*/
        Product p = catalogService.getProductById(product.getId());
        cart.addItem(p);// add the itme based on the id code pass to js function
        redirectAttributes.addFlashAttribute("info", "Item added to cart");
        /*return null;*/
    }
    //update cart qnty and totals amount
    @RequestMapping(value="/cart/items", method=RequestMethod.PUT)
    @ResponseBody
    public void updateLineItemQuanity(@RequestBody LineItem item, HttpServletRequest request, HttpServletResponse response) //listen to the ajax call
    {
        Cart cart = getOrCreateCart(request);
        /*for (Map.Entry<Integer, Integer> elementToUpdate : item.entrySet()){
            cart.updateQuantity(elementToUpdate.getKey(),elementToUpdate.getValue());
        }
        return cart;*/
        if(item.getQuantity() <= 0){
            Integer id = item.getProduct().getId();
            cart.deleteItem(id);
        } else {
            cart.updateLineItemQuantity(item.getProduct(), item.getQuantity());//get the updtted line itmes qnty as well as the total amount
        }
        /* cart.updateItemQuantity(item.getProduct(), item.getQuantity()); */

    }

    @RequestMapping(value="/cart/items/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public void deleteCartItem(@PathVariable("id") Integer id, HttpServletRequest request)
    {
        Cart cart = getOrCreateCart(request);
        cart.deleteItem(id);
    }

    //cart count
    @RequestMapping(value="/cart/items/count", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCartItemCount(HttpServletRequest request, Model model)
    {
        Cart cart = getOrCreateCart(request);
        int itemCount = cart.getItemCount();
        // List<LineItem> cartItems= cart.getItems();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", itemCount);
        //map.put("items",cartItems);
        return map;
    }
}