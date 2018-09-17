package com.sopia.boutiqueshop.admin.web.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sopia  on  1:46 PM 07-Nov-17.
 * @project Online BoutiQue Shop
 */
public class WebUtility {
	private WebUtility()
    {
    }
    //public static final String IMAGES_PREFIX = "/products/images/";
    public static final String IMAGES_PREFIX = "/products/images/";
    public static final String IMAGES_DIR = "C:/Users/user/Desktop/New folder/BoutiQueShop/products";

    public static String getURLWithContextPath(HttpServletRequest request)
    {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath();
    }


}
