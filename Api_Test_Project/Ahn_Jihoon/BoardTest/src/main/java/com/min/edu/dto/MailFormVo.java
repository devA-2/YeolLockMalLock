package com.min.edu.dto;

public class MailFormVo {
	
	private String subject = "문의하신 열락말락 신고 글의 답변 글입니다.";
	private String encodingType ="UTF-8";
	private String contextType = "html";
	
	
	public String getSubject() {
		return subject;
	}
	public String getEncodingType() {
		return encodingType;
	}
	
	public String getMessage(String email, String title, String content) {
		return 	"<h2>안녕하세요"+ email +" 님 열락말락 입니다.</h2><br><br>"
				+ "<h2>문의하신 글의 제목입니다. : "+title+"</h2>"
				+ "<h2>문의하신 글의 답변 입니다. : " + content + "</h2>";
	}
	
	public String getContextType() {
		return contextType;
	}
	@Override
	public String toString() {
		return "MailFormVo [subject=" + subject + ", encodingType=" + encodingType + ", contextType=" + contextType
				+ "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
