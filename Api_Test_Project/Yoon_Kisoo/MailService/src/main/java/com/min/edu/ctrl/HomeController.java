package com.min.edu.ctrl;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HomeController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JavaMailSender mailSender;
		
	@RequestMapping(value = "/home.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String home(String home) {
//		return "/WEB-INF/views/home.jsp";
		System.out.println("전달받은 값: "+home);
		return "home";
	}
	
	@RequestMapping(value = "/mailForm.do", method = RequestMethod.GET)
	public String mailForm() {
		logger.info("mailform 이동");
		return "mailForm";
	}
	
	@RequestMapping(value = "mailSender.do", method = RequestMethod.POST)
	public String mailSender(@RequestParam Map<String, String> mailMap) {
		logger.info("입력된 mail 값" + mailMap.get("tomail"));

		// 자신의 메일주소
		String setFrom = "gdproject2077@gamil.com";
		
		// 메일의 내용을 전송하기 위한 객체
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		
		messageHelper.setFrom(setFrom); // 보내는 사람의 이메일 생각하면 안되므로 유의할 것
		messageHelper.setTo(mailMap.get("tomail")); // 받는사람 이메일
		messageHelper.setSubject(mailMap.get("title")); // 메일의 제목 생략가능
		messageHelper.setText(mailMap.get("title"));
		
		// 파일첨부가 필요한 경우
		// MimeMessageHelper에 옵션중에 multipart를 true 했을경우
//		FileSystemResource를 객체를 통해 경로를 담고, 올리고자 하는 파일명과 경로를 messageHelper를 통해 attachment 해줌
//				FileSystemResource fileResource = new FileSystemResource("C:/Users/yoooo/Desktop/Yeti.png");
//		        messageHelper.addAttachment("Yeti.png", fileResource);
		        mailSender.send(message);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/home.do";
	}
}





