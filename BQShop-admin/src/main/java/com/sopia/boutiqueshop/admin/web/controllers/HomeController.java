

package com.sopia.boutiqueshop.admin.web.controllers;

import groovy.lang.Grab;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sopia  on  12:46 AM 19-Oct-17.
 * @project Online BoutiQue Shop
 */

@Controller
public class HomeController extends BQShopAdminBaseController
{
    @Override
    protected String getHeaderTitle() {
        return "Home";
    }

    @RequestMapping("/home")
    public String home(Model model)
    {
        return "home";
    }



}
