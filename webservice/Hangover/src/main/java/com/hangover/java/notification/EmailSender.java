package com.hangover.java.notification;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;

@Component("emailSender")
public class EmailSender implements MessageSender {


    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public EmailSender() {
    }

    public void send(Message mail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("sonuqureshi1512@gmail.com");
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            Template template = velocityEngine.getTemplate(mail.getTemplateName());
            VelocityContext velocityContext = new VelocityContext(mail.getContext());
            StringWriter stringWriter = new StringWriter();
            template.merge(velocityContext, stringWriter);
            helper.setText(stringWriter.toString());
        } catch (MessagingException e) {
            throw new MailParseException(e);
        }
        mailSender.send(message);
    }
}
