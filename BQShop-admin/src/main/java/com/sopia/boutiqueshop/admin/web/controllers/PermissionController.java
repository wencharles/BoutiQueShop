/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sopia.boutiqueshop.admin.web.controllers;

import com.sopia.boutiqueshop.admin.security.SecurityUtility;
import com.sopia.boutiqueshop.entities.Permission;
import com.sopia.boutiqueshop.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 *
 * @author user
 */

@Controller
@Secured(SecurityUtility.MANAGE_PERMISSIONS)
public class PermissionController  extends BQShopAdminBaseController{

    private final String viewPrefix="permissions/";

    @Autowired protected SecurityService securityService;

    @Override
    protected String getHeaderTitle()
    {
        return "Manage Permissions";
    }

    @RequestMapping(value="/permissions", method= RequestMethod.GET)
    public String listPermissions(Model model) {

        List<Permission> list = securityService.getAllPermissions();
        model.addAttribute("permissions",list);
        return viewPrefix+"permissions";
    }

}
