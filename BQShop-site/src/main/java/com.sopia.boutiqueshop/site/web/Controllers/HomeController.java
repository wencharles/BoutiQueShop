package com.sopia.boutiqueshop.site.web.Controllers;

import com.sopia.boutiqueshop.catalog.CatalogService;
import com.sopia.boutiqueshop.common.services.OrderService;
import com.sopia.boutiqueshop.entities.Category;
import com.sopia.boutiqueshop.entities.OrderItem;
import com.sopia.boutiqueshop.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * @author Sopia Alfred on 6/12/2018 10:05 AM.
 * @project boutiqueshop.
 */

@Controller
public class HomeController extends BQShopSiteBaseController {

    @Autowired
    private CatalogService catalogService;

    @Autowired private OrderService orderService;

    @Override
    protected String getHeaderTitle()
    {
        return "Home";
    }
    private static final String viewPrefix = "layout/";

    @RequestMapping("/index")
    public String home(Model model)
    {
        List<Category> previewCategories = new ArrayList<>();
        List<Category> categories = catalogService.getAllCategories();
        List<OrderItem> list = orderService.getAllOrderItmes();
        for (Category category : categories)
        {
            Set<Product> products = category.getProducts();
            Set<Product> previewProducts = new HashSet<>();
            int noOfProductsToDisplay = 1;
            if(products.size() > noOfProductsToDisplay){
                Iterator<Product> iterator = products.iterator();
                for (int i = 0; i < noOfProductsToDisplay; i++)
                {
                    previewProducts.add(iterator.next());
                }
            } else {
                //display all products available i.e 1 in this case m
                previewProducts.addAll(products);
            }
            category.setProducts(previewProducts);
            previewCategories.add(category);
        }
        model.addAttribute("categories", previewCategories);
        model.addAttribute("popular",list);
        return "index";
    }

    @RequestMapping("/shop")
    public String shop(Model model){
        List<Category> categories = catalogService.getAllCategories();
        model.addAttribute("categories",categories);
        return viewPrefix+"mainLayout";
    }

    @RequestMapping("/categories/{name}")
    public String category(@PathVariable String name, Model model)
    {
        Category category = catalogService.getCategoryByName(name);
        List<Category> categories = catalogService.getAllCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("category", category);
        return "category";
    }

}
