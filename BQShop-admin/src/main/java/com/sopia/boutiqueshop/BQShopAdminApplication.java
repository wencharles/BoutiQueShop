package com.sopia.boutiqueshop;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * @author Sopia  on  2:56 PM 18-Oct-17.
 * @project BoutiQueShop
 */

@SpringBootApplication
public class BQShopAdminApplication extends SpringBootServletInitializer {

    public static void main(String[] args)  //throws Exception
    {
        SpringApplication.run(BQShopAdminApplication.class, args);
    }

}
