/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sopia.boutiqueshop.admin.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@Controller
public class ErroController {
     private static final String viewPrefix = "error/";

        @RequestMapping("/403")
        public String accessDenied()
        {
            return viewPrefix+"accessDenied";
        }

    }
