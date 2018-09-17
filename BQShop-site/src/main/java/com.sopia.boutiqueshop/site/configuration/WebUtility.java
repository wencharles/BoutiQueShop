package com.sopia.boutiqueshop.site.configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sopia Alfred on 6/12/2018 1:22 PM.
 * @project boutiqueshop.
 */
public class WebUtility {
	private WebUtility()
    {
    }
    public static final String IMAGES_PREFIX = "/products/";
    public static final String IMAGES_DIR = "C:/Users/user/Desktop/New folder/BoutiQueShop/products";

    public static String getURLWithContextPath(HttpServletRequest request)
    {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
    }
}