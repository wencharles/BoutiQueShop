package com.sopia.boutiqueshop.admin.web.controllers;

import com.sopia.boutiqueshop.BQShopException;
import com.sopia.boutiqueshop.admin.web.utils.WebUtility;
import com.sopia.boutiqueshop.common.services.EmailService;
import com.sopia.boutiqueshop.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;

import static com.sopia.boutiqueshop.admin.web.utils.MessageCodes.*;
import static com.sopia.boutiqueshop.admin.web.utils.MessageCodes.ERROR_INVALID_PASSWORD_RESET_REQUEST;
import static com.sopia.boutiqueshop.admin.web.utils.MessageCodes.LABEL_PASSWORD_RESET_EMAIL_SUBJECT;

/**
 * @author Sopia  on  1:48 AM 26-Nov-17.
 * @project Online BoutiQue Shop
 */
@Controller
public class UserAuthController extends BQShopAdminBaseController{

    private final static String viewPrefix="public/";

    @Autowired
    protected SecurityService securityService;
    @Autowired protected EmailService emailService;
    @Autowired protected PasswordEncoder passwordEncoder;
    @Autowired protected TemplateEngine templateEngine;


    //Override the BQShobaseController abtract method.
    @Override
    protected  String getHeaderTitle(){
        return "User";
    }


    @RequestMapping(value = "/forgotPwd", method = RequestMethod.GET)
    public String forgotPwd(){
        return viewPrefix +"forgotPwd";
    }



    //send the password reset token via th email provided
    @RequestMapping(value="/forgotPwd", method = RequestMethod.POST)
    public String handleForgotPwd(HttpServletRequest request, RedirectAttributes redirectAttributes){
        //Get the provided email address
        String email = request.getParameter("email");

        try{

            String token = securityService.resetPassword(email);
            String resetPwdURL = WebUtility.getURLWithContextPath(request)+"/resetPwd?email="+email+"&token="+token;
            logger.debug(resetPwdURL);
            this.sendForgotPasswordEmail(email, resetPwdURL);
            redirectAttributes.addFlashAttribute("msg", getMessage(INFO_PASSWORD_RESET_LINK_SENT));


        }catch (BQShopException e)
        {
            logger.error(e);
            redirectAttributes.addFlashAttribute("msg", e.getMessage());

        }
        return "redirect:/forgotPwd";
    }


    //Check the password reset token send via the provided email address
    @RequestMapping (value = "/resetPwd",method = RequestMethod.GET)
    public String resetPwd(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes)
    {
        String email = request.getParameter("email");
        String token = request.getParameter("token");
        //we will validate the Token and
        boolean valid = securityService.verifyPasswordResetToken(email, token);
        if(valid){
            model.addAttribute("email", email);
            model.addAttribute("token", token);
            return viewPrefix+"resetPwd";// if the token is valid we show the page to reset pwd
        } else {
            //if the the token is invalid we show the erro and direct the user to the login pg
            redirectAttributes.addFlashAttribute("msg", getMessage(ERROR_INVALID_PASSWORD_RESET_REQUEST));
            return "redirect:/login";
        }

    }

    //Handles the reset password
    @RequestMapping (value ="/resetPwd" , method = RequestMethod.POST)
    public String handleResetPwd(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes)
    {
        try
        {
            //R
            String email = request.getParameter("email");
            String token = request.getParameter("token");
            String password = request.getParameter("password");
            String confPassword = request.getParameter("confPassword");
            if(!password.equals(confPassword))
            {
                model.addAttribute("email", email);
                model.addAttribute("token", token);
                model.addAttribute("msg", getMessage(ERROR_PASSWORD_CONF_PASSWORD_MISMATCH));
                return viewPrefix+"resetPwd";
            }
            String encodedPwd = passwordEncoder.encode(password);
            securityService.updatePassword(email, token, encodedPwd);

            redirectAttributes.addFlashAttribute("msg", getMessage(INFO_PASSWORD_UPDATED_SUCCESS));
        } catch (BQShopException e)
        {
            logger.error(e);
            redirectAttributes.addFlashAttribute("msg", getMessage(ERROR_INVALID_PASSWORD_RESET_REQUEST));
        }
        return "redirect:/login";
    }

    //Sends the  emails method . Tied to the EmailService Class
    protected void sendForgotPasswordEmail(String email, String resetPwdURL)
    {
        try {

            // Prepare the evaluation context
            final Context ctx = new Context();
            ctx.setVariable("resetPwdURL", resetPwdURL);

            // Create the HTML body using Thymeleaf
            final String htmlContent = this.templateEngine.process("forgot-password-email", ctx);

            emailService.sendEmail(email, getMessage(LABEL_PASSWORD_RESET_EMAIL_SUBJECT), htmlContent);
        } catch (BQShopException e) {
            logger.error(e);
        }
    }
}
