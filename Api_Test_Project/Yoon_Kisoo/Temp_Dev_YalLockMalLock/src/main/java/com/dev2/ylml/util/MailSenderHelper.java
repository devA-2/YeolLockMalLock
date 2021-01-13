package com.dev2.ylml.util;

public class MailSenderHelper {
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
	/************************************************************************************/
		
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
