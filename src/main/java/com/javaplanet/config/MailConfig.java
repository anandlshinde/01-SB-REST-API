package com.javaplanet.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Value("smtp.gmail.com")
	private String host;
	
	@Value("587")
	private Integer port;
	
	@Bean
	public JavaMailSender javaMailService() {
		JavaMailSenderImpl mailSenderImpl=new JavaMailSenderImpl();
		
		mailSenderImpl.setHost(host);
		mailSenderImpl.setPort(port);
		mailSenderImpl.setUsername("alshinde7@gmail.com");
		mailSenderImpl.setPassword("Shree@11");
		mailSenderImpl.setJavaMailProperties(getMailProperties());
		return mailSenderImpl;
	}
	
	private Properties getMailProperties() {
		Properties properties=new Properties();
		
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "false");
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.debug", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");

		return properties;
	}
	
}
