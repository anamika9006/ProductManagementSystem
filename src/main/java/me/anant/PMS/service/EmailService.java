package me.anant.PMS.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import me.anant.PMS.config.EmailConfig;

@Service
public class EmailService {
	@Autowired
	EmailConfig emailConfig;
	
	public void send(String to, String subject, String body) {
		String template = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n" + 
				"<html>\r\n" + 
				" <head>\r\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"
				+ "<style>"
				+ "table {width: 100%;border-collapse: collapse;}"
				+ "table, th, td {border: 1px solid black; padding: 5px}"
				+ "</style>"
				+ "</head>"
				+ "<body>"
				+ body
				+ "<br><br>"
				+ "Thanks & Regards,<br>"
				+ "Team PMS"
				+ "</body>"
				+ "</html>";
		JavaMailSender mailSender = emailConfig.getJavaMailSender();
		 MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(to);
                message.setFrom("system@gmail.com");
                message.setSubject(subject);
                message.setText(template, true);
            }
        };
        mailSender.send(preparator);
	}
}
