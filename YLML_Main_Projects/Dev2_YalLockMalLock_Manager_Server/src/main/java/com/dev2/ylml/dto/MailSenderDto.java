package com.dev2.ylml.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MailSenderDto {
   
   private int code;
   private Date send_time;
   private int status;
   
   public MailSenderDto(int code, int status) {
      super();
      this.code = code;
      this.status = status;
      this.send_time = new Date(System.currentTimeMillis());
   }
   
   
   
   
   
   
   
   
   
// 인증번호 , 발송시간 , 상태(메일인증으 어떠한 용도로 쓸 것인지)

}