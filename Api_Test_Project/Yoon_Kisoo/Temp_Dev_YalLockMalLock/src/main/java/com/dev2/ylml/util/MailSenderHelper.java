package com.dev2.ylml.util;

import java.sql.Date;
import java.util.HashMap;

import com.dev2.ylml.dto.MailSenderDto;

public class MailSenderHelper {
	
		private final int DEFAULT_LENGTH; // 빈에서 인증번호 자릿 수를 설정 할 수 있음
		private final int LIMIT_SECOND;   // 빈에서 인증번호의 유효 초를 설정 할 수 있음
		private final int CLEAN_TIMING;
		
		public static final int NOT_EXIST = -1;
		public static final int FAILED = -1;
		public static final int SUCCESS = 1;
		private final int RESET = 100;
		private final int PROVE = 200;
		private HashMap<String, MailSenderDto> prove_info;
		
		MailService mailService;
		
		public void setMailService(MailService mailService) {
			this.mailService = mailService;
		}
		
		public MailSenderHelper(int code_length, int limit_second, int clean_timing) {
			this.DEFAULT_LENGTH = code_length;
			this.LIMIT_SECOND = limit_second;
			this.CLEAN_TIMING = clean_timing;
			prove_info = new HashMap<String, MailSenderDto>(); // 생성자를 생성 할 때만 사용할 것 (고유값)
			
		}
		
		// 이메일 난수 생성 메서드
		private int getRandCode(int length) {
			int result = 1;
			for (int i = 0; i < length; i++) {
				result *= 10;
			}
			int rand = (int)(Math.random() * result); // 10000까지의 난수 생성
			return rand;
		}
		
		public void sendCode4Reset(String email) {
			sendCode(RESET, email);
		}
		
		public void sendCode4Prove(String email) {
			sendCode(PROVE, email);
		}
		
		private boolean sendCode(int status, String email) {
			if(prove_info.size() > CLEAN_TIMING) {
				cleanExpired();
			}
			
			int code = getRandCode(DEFAULT_LENGTH);
			if(mailService.sendMail(email, code)) {
				MailSenderDto dto = new MailSenderDto(code,status);
				prove_info.put(email, dto);
				return true;
			}else{
				return false;
			}
		}
			
		public int checkCode4Reset(String email, int code) {
			return checkCode(RESET,email, code);
		}
		
		public int checkCode4Prove(String email, int code) {
			return checkCode(PROVE, email, code);
		}
		
		private int checkCode(int status, String email, int code) {
			// 맵의 저장된 키(이메일)과 밸류(인증번호) 입력 받은 키 ,밸류 값과 비교 + 상태
			boolean hasKey = prove_info.containsKey(email);
			MailSenderDto dto = prove_info.get(email);
			
			
			int sendTime = (int) dto.getSend_time().getTime(); 
			System.out.println(sendTime);
			System.out.println("메일헬퍼 ㅍㅍㅍㅍㅍㅍㅍㅍㅍ"+dto.getCode() + code);
			// 시간이 초과했을 경우 failed
			if(hasKey
					&& checkTime(sendTime)  // 사실상 주석에 들어가야 맞음 시간을 계산하는 메소드를 만들어서 하면 편함
					&& dto.getCode() == code){
				
						return SUCCESS;
					}else {
						return FAILED;
			}
			
		}
		
		/**
		 * 시간을 계산해주는 메소드
		 * @param sendTime
		 * @return 시간이 초과 했을 경우 false , 아니면 true
		 */
		private boolean checkTime(int sendTime) {
			int nowDate = (int) new Date(System.currentTimeMillis()).getTime();
			return sendTime + LIMIT_SECOND * 1000 > nowDate;  // 사실상 주석에 들어가야 맞음 시간을 계산하는 메소드를 만들어서 하면 편함
			
		}
		
		private void cleanExpired() {
			
			for (String email : prove_info.keySet()) {
				MailSenderDto dto = prove_info.get(email);
				if(!checkTime((int) dto.getSend_time().getTime())) {
					prove_info.remove(email);
				}
				
			}
		}
		
		
		
		
		
		
		
		
		
	// 생성자 : 유효시간(limitSec 초단위)
	
	// 난수 생성 후 메일 발송 > 무엇을 보냈는지 기억(리스트 dto에 누적하여 보관) > 누구한테 보냈는지 list에서 확인해야함 >
	// 시나리오
	// 메일발송 누름 (sendCode 실행) 
	//		난수 생성 > 생성된 난수를 메일에 담아 발송하고 리스트에 누적시킴 >
	// 메일 인증번호 입력  > 인증번호 확인 (checkCode)
	//		받은 인증번호 확인 (저장된 인증번호와 비교)? 누구의 인증번호인지 모름 > 입력받은 이메일과 비교(session에 로그인.email과 비교함)
	// 		필요한 인자 : 인증번호와 이메일(session에서 로그인 정보)
	//	1. 리스트에 있는 이메일이 포함된 dto가 있는지 확인
	//  2. 포함된 dto가 있다면 인증번호를 확인(만약 여러번 인증코드 재발송을 한다면, 마지막 번호만 사용하게 해야하나?)
	// 		컬렉션 중 해시맵을 쓰면 키가 중복이 안됨, 똑같은 키(이메일) 인증번호+생성시간을 가지면 이메일로 발송여부를 확인 할 수 있음
	//			(HashMap.hasKey() ?) 여기에 이메일을 주면 해쉬맵에 키값이 있는지 체크가 가능
	//	-> 다시짜야댐 
	/**********************************새로 짠 설계 ******************************************/
		
	// 메일발송 누름 (sendCode 실행) // 받는 값 : 이메일
	//	1. 난수 생성 
	//	2-1. 메일발송
	// 	2-2. 해쉬맵을 생성 -> 키 값(이메일), value(인증번호, 생성시간)
	// 
	// 메일 인증번호 입력  > 인증번호 확인 (checkCode) : 	// 받는 값 : 인증번호와 이메일(session에서 로그인 정보 받아옴)
	//	1. 해쉬맵에 이메일이 있는지 체크 -> hasKey(이메일)로 키가 있는지 확인 할 수 있음
	//	2-1. 키가 있는 경우 
	//		해당 키 값으로 value(인증번호)를 가져옴 -> 인증번호 비교
	//			일치할 경우 : 1  리턴
	//			일치 하지 않을 경우 : 0  리턴
	//			키가 만료됬을 경우 : -1  리턴
	//  2-2. 메일 없음 : -1 리턴	
	// 결과를 int로 리턴한다 
	//	-1 : 메일 없음 혹은 키가 만료됬음
	//	0 : 인증키가 올바르지 않음
	//	1 : 인증키가 일치함
	
		
	// **********************************아래 쪽 부터는 결과설명*******************************************************
	// ex) 
	//	MailSenderHelper
	//	static final NOT_ EXIST = -1;
	//	static final FAILED = -1;
	//	static final SUCCESS = 1;
	//-1, 0, 1 -> 
	//	if(checkCode(1234, "yoooon0104@gmail.com") == MailSenderHelper.NOT_EXIST ){
	//
	//int code = 1234; 				// 파라미터로 받아옴
	//String email = "yoooon0104@gmail.com"	// 로그인 세션에서 가져옴
	//boolean sendCode (Email) - > 그냥 보냄 
	//	false면 메일 발송 실패  -> 사용자한테 알려줘야함
	//checkCode(code. email); -> 올바른지 여부 (성공/실패)  / 아예 이메일 발송한적  없을 때 -> 문제점! 인증번호를 발송할때마다 무작정 list에 담을 수 없음
	//	-> 자체적으로 삭제해줘야함
	//(https://medium.com/@igniter.yoo/java-linkedhashmap-%EC%88%9C%EC%84%9C%EB%A5%BC-%EC%9C%A0%EC%A7%80%ED%95%98%EB%8A%94-%ED%95%B4%EC%8B%9C%EB%A7%B5-11a7846d8893)
	//
	//sendMail -> list.size > 1000 -> list의 value들을 싹다 만료시간 체크해서 일부를 비워주면 됨
	//
	//// Controller
	//(클래스 빈에 올리는 법 검색)
	//@Autowired
	//MailSenderHelper mailHelper;
	//
	//@Request....(....do....)
	//public String sendCodeToMail(HttpSession session){
	//	String email = session.getAttr("mem").getEmail();
	//	mailHelper.sendCode(email);  // 메일 보내기, 인증번호 기억해두는거
	//
	//public String checkCode(HttpSession session, int code){
	//	String email = session.getAttr("mem").getEmail();
	//	if(mailHelper.sendCode(email, code)==상태){
	//	
	//	}else if( ==상태){
	//	
	//	}
	//구현 . 문제발생. 구현 뜯어고침, 누더기가 됨 -> 다시만듬 
	//만들어졌다고 치면 어떤 결과인지 정함 -> 거기에 맞춰서 문제를 코드로 구성
	//-> 거기에 필요한 결과는 다시 임의로 정함
	//그래서 그냥 컨트롤러에서 구현했다치고 만들어봄 -> 필요한게 있음 -> 만듬
	
		
		
	// 메일 코드
//	private final int RESET = 100;
//	private final in PROVE = 200;
//	private HashMap<String, Dto> map;
//
//	생성자(int 유효초)
//		map = new Hashmap<>{};
//
//
//	sendCode4Reset(email)
//		sendCode(MailSenderHelper.RESET, email)
//
//	sendCode4Prove(email)
//		sendCode(MailSenderHelper.PROVE, email)
//
//	sendCode(int status, String email)
//		int rand = new MathRand..
//		Dto dto = new Dto(rand, status)
//		map.put(email, dto)
//
//	checkCode4Reet
//		checkCode(MailSenderHelper.RESET, email)
//	checkCode4Prove
//		checkCode(MailSenderHelper.PROVE, email)
//	checkCode
	
	
}
