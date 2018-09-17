package com.sopia.boutiqueshop.admin.web.controllers;

import com.sopia.boutiqueshop.entities.Role;
import com.sopia.boutiqueshop.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sopia.boutiqueshop.admin.security.SecurityUtility;
import com.sopia.boutiqueshop.admin.web.validators.UserValidator;
import com.sopia.boutiqueshop.security.SecurityService;

/**
 * @author Sopia  on  3:43 AM 24-Nov-17.
 * @project Online BoutiQue Shop
 */
@Controller
@Secured(SecurityUtility.MANAGE_USERS)
public class UserController extends BQShopAdminBaseController
{
    private static final String viewPrefix = "users/";
    @Autowired
    protected SecurityService securityService;
    @Autowired private UserValidator userValidator;
    @Autowired protected PasswordEncoder passwordEncoder;

    @Override
    protected String getHeaderTitle()
    {
        return "Manage Users";
    }
    //return all roles
    @ModelAttribute("rolesList")
    public List<Role> rolesList(){
        return securityService.getAllRoles();
    }

    //get all the users in a list
    @RequestMapping(value="/users", method= RequestMethod.GET)
    public String listUsers(Model model) {
        List<User> list = securityService.getAllUsers();
        model.addAttribute("users",list);
        return viewPrefix+"users";
    }

    //Provide the user creation form
    @RequestMapping(value="/users/new", method=RequestMethod.GET)
    public String createUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        //model.addAttribute("rolesList",securityService.getAllRoles());

        return viewPrefix + "create_user";
    }

    //Handles the data entered on the user creation form
    @RequestMapping(value="/users", method=RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result,
                             Model model, RedirectAttributes redirectAttributes) {
        userValidator.validate(user, result);
        //returns the user creation form
        if(result.hasErrors()){
            System.out.print("===Error====Creating===User=="+user);
            return viewPrefix+"create_user";
        }
        //create user and return the users list
        String password = user.getPassword();
        String encodedPwd = passwordEncoder.encode(password);
        user.setPassword(encodedPwd);
        User persistedUser = securityService.createUser(user);
        logger.debug("Created new User with id : {} and name : {}", persistedUser.getId(), persistedUser.getName());
        redirectAttributes.addFlashAttribute("info", "User created successfully");
        System.out.print("===========User====created=====successfully"+user);
        return "redirect:/users";
    }
    //randers the users details edit_users form based on the user id
    @RequestMapping(value="/users/{id}", method=RequestMethod.GET)
    public String editUserForm(@PathVariable Integer id, Model model) {
        User user = securityService.getUserById(id);
        Map<Integer, Role> assignedRoleMap = new HashMap<>();
        List<Role> roles = user.getRoles();
        for (Role role : roles)
        {
            assignedRoleMap.put(role.getId(), role);
        }
        List<Role> userRoles = new ArrayList<>();
        List<Role> allRoles = securityService.getAllRoles();
        for (Role role : allRoles)
        {
            if(assignedRoleMap.containsKey(role.getId())){
                userRoles.add(role);
            } else {
                userRoles.add(null);
            }
        }
        user.setRoles(userRoles);
        model.addAttribute("user",user);
        //model.addAttribute("rolesList",allRoles);
        return viewPrefix+"edit_user";
    }
    //handle the edited posted data from the edit_user page
    @RequestMapping(value="/users/{id}", method=RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user, BindingResult result,
                             Model model, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()){
            System.out.print("===========Error====Editing==="+user);
            return viewPrefix+"edit_user";
        }
        User persistedUser = securityService.updateUser(user);
        logger.debug("Updated user with id : {} and name : {}", persistedUser.getId(), persistedUser.getName());
        redirectAttributes.addFlashAttribute("info", "User updates successfully");
        System.out.print("==========User======Updated==========Successfully=========");
        return "redirect:/users";
    }

}
