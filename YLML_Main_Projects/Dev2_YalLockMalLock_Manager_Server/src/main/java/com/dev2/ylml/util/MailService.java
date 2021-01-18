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

}