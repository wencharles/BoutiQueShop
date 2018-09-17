package com.sopia.boutiqueshop.common.services;

import com.sopia.boutiqueshop.BQShopException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
//import com.sopia.boutiqueshop.BQShopException;

/**
 * @author Sopia  on  12:33 PM 17-Oct-17.
 * @project E_Boutique_Shop
 */

@Component
public class EmailService
{
    private static final BQLogger logger = BQLogger.getLogger(EmailService.class);

    @Autowired JavaMailSender javaMailSender;

    @Value("${support.email}") String supportEmail;

    public void sendEmail(String to, String subject, String content)
    {
        try
        {
            // Prepare message using a Spring helper
            final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
            message.setSubject(subject);
            message.setFrom(supportEmail);
            message.setTo(to);
            message.setText(content, true /* isHtml */);

            javaMailSender.send(message.getMimeMessage());
        }
        catch (MailException | MessagingException e)
        {
            logger.error(e);
            throw new BQShopException("Unable to send email");
        }
    }


}