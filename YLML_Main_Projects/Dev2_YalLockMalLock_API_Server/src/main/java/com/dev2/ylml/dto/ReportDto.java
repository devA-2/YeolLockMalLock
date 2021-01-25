package com.dev2.ylml.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@ToString
//@NoArgsConstructor

@Data
@NoArgsConstructor
public class ReportDto {

	private String seq;
	private String email;
	private String title;
	private String content;
	private String regdate;
	private String category;
	private String image="";
	private String delflag;
	private String process_status;
	private String refer;
	private String step;
	private String depth;

}
