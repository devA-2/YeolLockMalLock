package com.dev2.ylml.util;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;

import com.dev2.ylml.dto.MailFormVo;


public class MailService {
   
   JavaMailSender mailSender;
   
   public void setMailSender(JavaMailSender mailSender) {
      this.mailSender = mailSender;
      System.out.println("메일센더 작동작동작동작동작동");
   }
   
   MailFormVo mailVo = new MailFormVo() ;
   
   // 메일발송
   public boolean sendMail(String email, int code) {
   MimeMessage mail = mailSender.createMimeMessage();
   String msg = mailVo.getMessage(email, code);
   String subject = mailVo.getSubject();
   String encodingType = mailVo.getEncodingType();
   String contextType = mailVo.getContextType();
   System.out.println(mailVo.toString());
   try {
      mail.setSubject(subject, encodingType);
      mail.setText(msg, encodingType, contextType);
      mail.addRecipient(RecipientType.TO, new InternetAddress(email));
      mailSender.send(mail);
      return true;
      
   }catch (MessagingException e) {
      e.printStackTrace();
      return false;
   }
}
   
	// 메일발송
	public boolean sendMail(String email, String title, String content) {
	MimeMessage mail = mailSender.createMimeMessage();
	
	String msg = "<h2>안녕하세요"+ email +" 님 열락말락 입니다.</h2><br><br>"
			+ "<h2>문의하신 글의 제목입니다. : "+title+"</h2>"
			+ "<h2>문의하신 글의 답변 입니다. : " + content + "</h2>"; // mailVo.getMessage를 호출해서 사용하는거라서 컨트롤러에서 가져온 email title content가 아니라 비어있는 null 3개가 들고와진다.
	// 이말은 VO에서 가져오는 고정된 값이 아니라 변하는 dto의 값을 가져오거나 controller에서 다이렉트로 가져와야 원하는 값을 가져올 수 있네?
	// 그럼 코드가 지져분해지만 일단 실행시켜야한다.
	String subject = mailVo.getSubject();
	String encodingType = mailVo.getEncodingType();
	String contextType = mailVo.getContextType();
	
	System.out.println(mailVo.toString());
	try {
		mail.setSubject(subject, encodingType);
		mail.setText(msg, encodingType, contextType);
		mail.addRecipient(RecipientType.TO, new InternetAddress(email));
		mailSender.send(mail);
		return true;
		
	}catch (MessagingException e) {
		e.printStackTrace();
		return false;
	}
}

}