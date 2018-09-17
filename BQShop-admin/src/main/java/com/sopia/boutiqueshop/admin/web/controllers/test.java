package com.sopia.boutiqueshop.admin.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Sopia  on  2:50 AM 16-Nov-17.
 * @project Online BoutiQue Shop
 */

@Controller
@RequestMapping(value="/public", method = RequestMethod.GET)
public class test {


    private static final String viewPrefix="public/";
    public test(){

    }
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String forgotPwd(){
        return viewPrefix +"test";
    }
}
