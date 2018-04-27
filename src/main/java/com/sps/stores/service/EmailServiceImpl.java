/**
 * 
 */
package com.sps.stores.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;




/**
 * @author Jai1.Kumar
 *
 */
@Service("emailService")
@PropertySource(value = { "classpath:application.properties" })
public class EmailServiceImpl implements EmailService {
	
	 @Autowired
	 private Environment environment;
	 
	@Override
	public boolean send(String to,String msg,String sub){
		//Get properties object    
        Properties props = new Properties();    
        props.put("mail.smtp.host",environment.getRequiredProperty("mail.smtp.host"));    
        props.put("mail.smtp.socketFactory.port", environment.getRequiredProperty("mail.smtp.port"));    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         @Override
		protected javax.mail.PasswordAuthentication getPasswordAuthentication() {    
         return new javax.mail.PasswordAuthentication(environment.getRequiredProperty("smtp.user"),environment.getRequiredProperty("smtp.pass"));  
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
         message.setSubject(sub);    
         message.setText(msg);    
         //send message  
         Transport.send(message);    
         System.out.println("message sent successfully");    
        } catch (MessagingException e) {throw new RuntimeException(e);}    
       return true;    
  }  
	

}
