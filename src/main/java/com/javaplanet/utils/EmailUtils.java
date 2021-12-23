package com.javaplanet.utils;

import javax.validation.Valid;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.javaplanet.dto.Message;

@Component
public class EmailUtils {
	
	private JavaMailSender mailSenderObj;
	
	public EmailUtils(JavaMailSender javaMailSender) {
		this.mailSenderObj=javaMailSender;
	}
	
	public void sendmail(@Valid Message message) {

		mailSenderObj.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				mimeMsgHelperObj.setTo(message.getRecipient());

				mimeMsgHelperObj.setText(message.getMessage(), true);

				mimeMsgHelperObj.setSubject(message.getSubject());
			}
		});

	}


}
