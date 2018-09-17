package com.sopia.boutiqueshop.admin.security;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Sopia  on  12:51 AM 11-Nov-17.
 * @project Online BoutiQue Shop
 */
public class MenuSettings {private static Map<String, String> MENU_URL_PATTERN_MAP = new HashMap<>();

    static
    {
        MENU_URL_PATTERN_MAP.put("/home", "Home");
        MENU_URL_PATTERN_MAP.put("/categories", "Categories");
        MENU_URL_PATTERN_MAP.put("/products", "Products");
        MENU_URL_PATTERN_MAP.put("/orders", "Orders");
        MENU_URL_PATTERN_MAP.put("/ordereditems", "Reports");
        MENU_URL_PATTERN_MAP.put("/customers", "Customers");
        MENU_URL_PATTERN_MAP.put("/users", "Users");
        MENU_URL_PATTERN_MAP.put("/roles", "Roles");
        MENU_URL_PATTERN_MAP.put("/permissions", "Permissions");


    }

    public static Map<String, String> getMenuUrlPatternMap() {
        return Collections.unmodifiableMap(MENU_URL_PATTERN_MAP);
    }

    public static String getMatchingMenu(String uri) {
        Set<String> keySet = MENU_URL_PATTERN_MAP.keySet();
        for (String key : keySet) {
            if(uri.startsWith(key)){
                return MENU_URL_PATTERN_MAP.get(key);
            }
        }
        return "";
    }
}
