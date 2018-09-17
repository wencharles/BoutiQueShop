package com.sopia.boutiqueshop.site.web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sopia Alfred on 6/12/2018 10:04 AM.
 * @project boutiqueshop.
 */

@Controller
public class ErrorController extends BQShopSiteBaseController{
    private static final String viewPrefix = "error/";

    @Override
    protected String getHeaderTitle()
    {
        return "Error";
    }

    @RequestMapping("/403")
    public String accessDenied()
    {
        return viewPrefix+"accessDenied";
    }

	/*@RequestMapping("/error")
	public String error()
	{
		return viewPrefix+"error";
	}*/
}
