package com.lumen.ebonding.util;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigPortalEmail {
	private Logger log = LoggerFactory.getLogger(ConfigPortalEmail.class);
	
	
	@Value("${mail.smtp.host}")
	public String MAIL_SMTP_HOST;
	
	@Value("${mail.smtp.port}")
	public String MAIL_SMTP_PORT;
	
	@Value("${from.address}")
	public String FROM_ADDRESS;
	
	public void sendMailPasscode(String cuId,String passcode,String emailId) {
		log.info("Start: sendMailPasscode");
        String subject="ConfigPortal verification code";
        String catalinaPath=System.getProperty("catalina.base");
        try
        { 
		
			Properties props = new Properties();
			props.put("mail.smtp.host",MAIL_SMTP_HOST);
			Session session = Session.getDefaultInstance(props);
			
			log.info("mail.smtp.host " +MAIL_SMTP_HOST);
			
			String toAddress = emailId;

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(FROM_ADDRESS));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
				
            msg.setSubject(subject);

            MimeMultipart multipart = new MimeMultipart();
            
            StringBuilder buf = new StringBuilder();
            buf.append("<html><head>  <title>table</title>  <style>table{   border-collapse: collapse;  width: 100%; } th,td{border: 1px solid green;padding: 15px; text-align: left}</style></head>" +
                       "<body> <p align ='middle'><br><img  src='cid:notification'></p> " );
            
             buf.append("</br>");
             buf.append("Hello "+cuId+"</br>");
             buf.append("</br> Please use <b>"+ passcode +"</b> as your verification code to reset your password</br>");
             buf.append("If you did not request this password reset verification code or you received this message in error, "
             		+ "please report this immediately to CTL_EBONDING_DEV@lumen.com to secure your account.</br></br>");
             buf.append("<br>Thanks & Regards</br>Ebonding Team</br>");
             buf.append("</body>"
						+ "<br><br><i>This communication is the property of Lumen Technologies and may contain confidential or privileged information. Unauthorized use of this communication is strictly prohibited and may be unlawful. "
						+ "<br>If you have received this communication in error, please immediately notify to CTL_EBONDING_DEV@lumen.com by reply e-mail and destroy all copies of the communication and any attachments.</i>"
						+ "</html>");
            
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(buf.toString(),"text/html");
						
			multipart.addBodyPart(contentPart);
				
            MimeBodyPart imagePart = new MimeBodyPart();
            String imagePath = catalinaPath +
	                  File.separator + "images" + File.separator + "Lumen_logo.png";
			//imagePath = "C:\\Users\\AC96718\\Downloads\\Lumen_Logo.png";
			DataSource fds = new FileDataSource(imagePath);
			imagePart.setDataHandler(new DataHandler(fds));
			imagePart.setHeader("Content-ID", "notification");
			imagePart.setDisposition(MimeBodyPart.INLINE);
			multipart.addBodyPart(imagePart);
            msg.setContent(multipart);

            Transport.send(msg);
            log.info("sendMailPasscode :Successfully sent an email  ");
			
        }
        catch(Exception e){
        	
        	log.error("sendMailPasscode failed" +ExceptionUtils.getStackTrace(e));
        }
	}

	public void sendPassword(String cuId, String emailId, String password) {
		log.info("Start: sendPassword");
        String subject="ConfigPortal default password";
        String catalinaPath=System.getProperty("catalina.base");
        try
        { 
		
			Properties props = new Properties();
			props.put("mail.smtp.host",MAIL_SMTP_HOST);
			Session session = Session.getDefaultInstance(props);
			
			log.info("mail.smtp.host " +MAIL_SMTP_HOST);
			
			String toAddress = emailId;

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(FROM_ADDRESS));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
				
            msg.setSubject(subject);

            MimeMultipart multipart = new MimeMultipart();
            
            StringBuilder buf = new StringBuilder();
            buf.append("<html><head>  <title>table</title>  <style>table{   border-collapse: collapse;  width: 100%; } th,td{border: 1px solid green;padding: 15px; text-align: left}</style></head>" +
                       "<body> <p align ='middle'><br><img  src='cid:notification'></p> " );
            
             buf.append("</br>");
             buf.append("Hello "+cuId+"</br>");
             buf.append("</br> Please use <b>"+ password +"</b> as your default password. Please update password by login to config portal</br>");
             buf.append("If you did not request this default password or you received this message in error, "
             		+ "please report this immediately to CTL_EBONDING_DEV@lumen.com to secure your account.</br></br>");
             buf.append("<br>Thanks & Regards</br>Ebonding Team</br>");
             buf.append("</body>"
						+ "<br><br><i>This communication is the property of Lumen Technologies and may contain confidential or privileged information. Unauthorized use of this communication is strictly prohibited and may be unlawful. "
						+ "<br>If you have received this communication in error, please immediately notify to CTL_EBONDING_DEV@lumen.com by reply e-mail and destroy all copies of the communication and any attachments.</i>"
						+ "</html>");
            
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(buf.toString(),"text/html");
						
			multipart.addBodyPart(contentPart);
				
            MimeBodyPart imagePart = new MimeBodyPart();
            String imagePath = catalinaPath +
	                  File.separator + "images" + File.separator + "Lumen_logo.png";
			//imagePath = "C:\\Users\\AC96718\\Downloads\\Lumen_Logo.png";
			DataSource fds = new FileDataSource(imagePath);
			imagePart.setDataHandler(new DataHandler(fds));
			imagePart.setHeader("Content-ID", "notification");
			imagePart.setDisposition(MimeBodyPart.INLINE);
			multipart.addBodyPart(imagePart);
            msg.setContent(multipart);

            Transport.send(msg);
            log.info("sendPassword :Successfully sent an email  ");
			
        }
        catch(Exception e){
        	
        	log.error("sendPassword failed" +ExceptionUtils.getStackTrace(e));
        }
		
	}
}
