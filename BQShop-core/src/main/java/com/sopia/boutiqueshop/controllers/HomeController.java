//package com.sopia.boutiqueshop.controllers;
//
//
//import com.sopia.boutiqueshop.entity.services.CategoriesService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class HomeController {
//
//    @Autowired
//    private CategoriesService categoriesService;
//
//
//
//    public HomeController(){}
//
//
//    //method to get the homepage
//    @RequestMapping(value = "/home")
//    public String getHome(Model model)
//    {
//        //the homepage requires a list of the categories, a list of products under the features items and a list of products under the categories
//        //model.addAllAttributes(featureItems, tshirtsAsFeatureItems, categoriesService.getCategories());
//        return "home";
//    }
//
//    //method  to view a product category
//    //method calls categories service to get all products in the category
//    @RequestMapping(value= "/home/categories/{pathname}")
//    public String getCategory(@PathVariable String pathname, Model model)
//    {
//
//        return
//    }
//}
