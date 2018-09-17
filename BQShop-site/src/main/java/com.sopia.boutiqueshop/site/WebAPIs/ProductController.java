/*
package com.sopia.boutiqueshop.site.WebAPIs;

import com.sopia.boutiqueshop.catalog.CatalogService;
import com.sopia.boutiqueshop.entities.Category;
import com.sopia.boutiqueshop.entities.Product;
import com.sopia.boutiqueshop.repositories.ProductsRepository;
import com.sopia.boutiqueshop.site.configuration.WebUtility;
import com.sopia.boutiqueshop.site.web.Controllers.BQShopSiteBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
*/
/*

*
 * @author Sopia Alfred on 7/9/2018 11:24 AM.
 * @project boutiqueshop.
*//*



@RestController
public class ProductController  {

    @Autowired
    private CatalogService catalogService;
    @RequestMapping("/search/api")
    @ResponseBody
    public List<Product>searchProducts(@RequestParam(name = "q", defaultValue = "") String query) {
       return catalogService.searchProducts(query);

    }


    @RequestMapping(value = "/affordable/api", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAffordable(@RequestParam(name = "price", defaultValue = "") BigDecimal price) {
        return catalogService.affordable(price);

    }

}
*/
