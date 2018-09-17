package com.sopia.boutiqueshop.admin.web.controllers;

import com.sopia.boutiqueshop.admin.security.SecurityUtility;
import com.sopia.boutiqueshop.admin.web.validators.RoleValidator;
import com.sopia.boutiqueshop.admin.web.validators.UserValidator;
import com.sopia.boutiqueshop.entities.Permission;
import com.sopia.boutiqueshop.entities.Role;
import com.sopia.boutiqueshop.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sopia  on  3:05 AM 25-Nov-17.
 * @project Online BoutiQue Shop
 */

@Controller
@Secured(SecurityUtility.MANAGE_ROLES)
public class RolesController extends BQShopAdminBaseController {

    private static final String viewPrefix="roles/";

    @Autowired protected
    SecurityService securityService;
    @Autowired private RoleValidator roleValidator;

    @Override
    protected String getHeaderTitle(){
        return "Manage Roles";
    }
    //get a permision list
    @ModelAttribute("permissionsList")
    public List<Permission> permissionsList(){
        return securityService.getAllPermissions();
    }

    @RequestMapping(value="/roles", method=RequestMethod.GET)
    public String listRoles(Model model) {
        List<Role> list = securityService.getAllRoles();
        model.addAttribute("roles",list);
        return viewPrefix+"roles";
    }

    @RequestMapping(value="/roles/{id}", method=RequestMethod.GET)
    public String editRoleForm(@PathVariable Integer id, Model model) {
        Role role = securityService.getRoleById(id);
        Map<Integer, Permission> assignedPermissionMap = new HashMap<>();
        List<Permission> permissions = role.getPermissions();
        for (Permission permission : permissions)
        {
            assignedPermissionMap.put(permission.getId(), permission);
        }
        List<Permission> rolePermissions = new ArrayList<>();
        List<Permission> allPermissions = securityService.getAllPermissions();
        for (Permission permission : allPermissions)
        {
            if(assignedPermissionMap.containsKey(permission.getId())){
                rolePermissions.add(permission);
            } else {
                rolePermissions.add(null);
            }
        }
        role.setPermissions(rolePermissions);
        model.addAttribute("role",role);
        //model.addAttribute("permissionsList",allPermissions);
        return viewPrefix+"edit_role";
    }

    @RequestMapping(value="/roles/{id}", method=RequestMethod.POST)
    public String updateRole(@ModelAttribute("role") Role role, BindingResult result,
                             Model model, RedirectAttributes redirectAttributes) {
        Role persistedRole = securityService.updateRole(role);
        logger.debug("Updated role with id : {} and name : {}", persistedRole.getId(), persistedRole.getName());
        redirectAttributes.addFlashAttribute("info", "Role updated successfully");
        return "redirect:/roles";
    }

    //provides roles creation form
    @RequestMapping(value = "create_role", method = RequestMethod.GET)
    public String creatRolesForm(Model model){
        Role roles = new Role();
        model.addAttribute("role",roles);
        return viewPrefix+"create_role";
    }
    //Procees the data entered on the role creation form
    @RequestMapping(value = "roles", method = RequestMethod.POST)
    public String createRole(@Valid @ModelAttribute("role") Role roles, BindingResult result,
                             RedirectAttributes redirectAttributes, Model model) {
        //check the data provided
        roleValidator.validate(roles,result);
        //return user to creat_role pg if data has errors
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("info","Incorrect data!");
            return viewPrefix+"create_role";
        }
        //Created new role
        Role persistedRole = securityService.createRole(roles);
        logger.debug("Created new role with id: {} and name: {}",persistedRole.getId(),persistedRole.getName());
        redirectAttributes.addFlashAttribute("info", "Role Created Successfulllly!");//Shows a flash message to user
        return viewPrefix + "roles";
    }

}
