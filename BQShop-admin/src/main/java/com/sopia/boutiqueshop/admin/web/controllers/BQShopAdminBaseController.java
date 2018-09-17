package com.sopia.boutiqueshop.admin.web.controllers;

import com.sopia.boutiqueshop.admin.security.AuthenticatedUser;
import com.sopia.boutiqueshop.common.services.BQLogger;
import com.sopia.boutiqueshop.common.services.UserService;
import com.sopia.boutiqueshop.entities.Role;
import com.sopia.boutiqueshop.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Sopia  on  12:52 PM 02-Nov-17.
 * @project Online BoutiQue Shop
 */
public abstract class BQShopAdminBaseController {

    protected final BQLogger logger = BQLogger.getLogger(getClass());
//
    @Autowired private UserService userService;

    @Autowired protected MessageSource messageSource;
    protected abstract String getHeaderTitle();

    //print out messge to the html pages base on
    public String getMessage(String code)
    {
        return messageSource.getMessage(code, null, null);
    }

    public String getMessage(String code, String defaultMsg)
    {
        return messageSource.getMessage(code, null, defaultMsg, null);
    }

    //exposing to the html pages
    @ModelAttribute("authenticatedUser")
    public AuthenticatedUser authenticatedUser(@AuthenticationPrincipal AuthenticatedUser authenticatedUser)
    {
        return authenticatedUser;
    }

    public static AuthenticatedUser getCurrentUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AuthenticatedUser) {
            return ((AuthenticatedUser) principal);
        }
        // principal object is either null or represents anonymous user -
        // neither of which our domain User object can represent - so return null
        return null;
    }

    public static boolean isLoggedIn() {
        return getCurrentUser() != null;
    }

    //To be commeted later
//    @RequestMapping(value="/profile", method= RequestMethod.GET)
//    protected String profile(Model model) {
//
//        String email = getCurrentUser().getUser().getEmail();
//        User user = userService.getUserByEmail(email);
//        model.addAttribute("profile", user);
////        List<Role> roles = userService.getUserRoles(email);
////        model.addAttribute("roles", roles);
//        return "profile";
//    }
//
}
