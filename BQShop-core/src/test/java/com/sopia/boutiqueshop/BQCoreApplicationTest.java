package com.sopia.boutiqueshop;

import com.sopia.boutiqueshop.common.services.EmailService;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



/**
 * @author Sopia  on  1:24 PM 18-Oct-17.
 * @project E_Boutique_Shop
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BQCoreApplication.class)
public class BQCoreApplicationTest {
    @Autowired DataSource dataSource;
    @Autowired EmailService emailService;

    @Test
    public void testDummy() throws SQLException
    {
        String schema = dataSource.getConnection().getCatalog();
        assertTrue("bqshop".equalsIgnoreCase(schema));
    }

    @Test
    @Ignore
    public void testSendEmail()
    {
        emailService.sendEmail("admin@gmail.com", "BQShop - Test Mail", "This is a test email from BQShop");
    }

}
