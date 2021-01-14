package com.dev2.ylml.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.dev2.ylml.dto.MailFormVo;

public class MailService {
	
	@Autowired
	JavaMailSender mailSender;
	
	MailFormVo mailVo;
	
	// 메일발송
	public boolean sendMail(String email, int code) {
	MimeMessage mail = mailSender.createMimeMessage();
	String msg = mailVo.getMessage(email, code);
			
	try {
		mail.setSubject("열락말락 인증 메일입니다.", "utf-8");
		mail.setText(msg, "utf-8", "html");
		mailSender.send(mail);
		return true;
		
	}catch (MessagingException e) {
		e.printStackTrace();
	}
		return false;
}

}
