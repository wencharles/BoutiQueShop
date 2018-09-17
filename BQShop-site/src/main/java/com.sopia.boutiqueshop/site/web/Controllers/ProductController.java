package com.sopia.boutiqueshop.site.web.Controllers;


import com.sopia.boutiqueshop.catalog.CatalogService;
import com.sopia.boutiqueshop.catalog.ProductService;
import com.sopia.boutiqueshop.common.services.OrderService;
import com.sopia.boutiqueshop.entities.Category;
import com.sopia.boutiqueshop.entities.OrderItem;
import com.sopia.boutiqueshop.entities.Product;
import com.sopia.boutiqueshop.repositories.ProductsRepository;
import com.sopia.boutiqueshop.site.configuration.WebUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Sopia Alfred on 6/11/2018 12:57 PM.
 * @project boutiqueshop.
 */
@Controller
public class ProductController extends BQShopSiteBaseController{

    @Autowired
private CatalogService catalogService;

    @Autowired private ProductsRepository productsRepository;


    @Override
    public String getHeaderTitle(){
        return "product";
    }
    @RequestMapping("/products/{sku}")
    public String product(@PathVariable String sku, Model model)
    {
        Product product = catalogService.getProductBySku(sku);
        List<Category> categories = catalogService.getAllCategories();
        model.addAttribute("product", product);
        Category recommended = product.getCategory();
        //List<Product> recommended1 =product.getCategory().getProducts();
        //categories
        model.addAttribute("categories", categories);
        //recommended
        model.addAttribute("recommended",recommended);
        //popular products
        //model.addAttribute("popular",list);
        return "product-details";

    }

    @RequestMapping("/products")
    //@ResponseBody
    public  String searchProducts(@RequestParam(name="q", defaultValue="") String query, Model model)
    {
        List<Product> products =catalogService.searchProducts(query);
       // return  catalogService.searchProducts(query);
        /*products = catalogService.searchProducts(query);*/
        model.addAttribute("products", products);
        return "product_search_results";
    }


    @RequestMapping(value = "/affordable", method = RequestMethod.GET)
    //@ResponseBody
    public String getAffordable(@RequestParam (name="price", defaultValue = "") BigDecimal price, Model model){
        List<Product> affordable =catalogService.affordable(price);
        model.addAttribute("affordable",affordable);
        return "affordable";

    }


    /*@RequestMapping("/recommended/{name}")
    public String category(@PathVariable String name, Model model)
    {
        Category recommended = catalogService.getCategoryByName(name);
        model.addAttribute("recommended", recommended);
        return "product-details";
    }*/

    @RequestMapping(value="/products/images/{productId}", method= RequestMethod.GET)
    public void showProductImage(@PathVariable String productId, HttpServletRequest request, HttpServletResponse response) {
        try {
            FileSystemResource file = new FileSystemResource(WebUtility.IMAGES_DIR +productId+".jpg");
            response.setContentType("image/jpg");
            org.apache.commons.io.IOUtils.copy(file.getInputStream(), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}