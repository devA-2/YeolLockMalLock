package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.DeliveryDto;
import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.util.ApiClientHelper;


@Service
public class DeliveryService implements DeliveryIService{
	@Autowired
	ApiClientHelper helper;
	
	@Autowired
	private PasswordEncoder pwEncoder;
	
	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getSampleData(String id, String pw) {
		//URL+PW로 데이터를 만들어서 전달
		//list의 0번째인 Certification check후 false일경우 return null;
		
		Map<String, String> map=new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		
		//API 서버에서 helper.generateData(sendData)안의 sendData 의 타입으로 돌려줌
		//helper.getData(map)가 캐스팅된 타입이 helper.request("sampleData.do", map)의 map에 들어감
		return (HashMap<String, String>)helper.request("sampleData.do", map);
	}
	@Override
	public String getSampleData2(){
		return helper.request("sampleData.do2").getClass().getName();
	}
	/*
	 * 회원가입
	 */
	public boolean insertMember(MemberDto memberDto) {
		return (boolean)helper.request("insertMember.do", memberDto);
	}
	
	/*
	 * 아이디 중복체크
	 */
	public int idCheck(String email) {
		System.out.println("맴버디티오 : "+email);
		return (int)helper.request("idCheck.do", email);
	}
	
	/*
	 * 휴대폰 중복체크
	 */
	public int phoneCheck(String phoneNum) {
		return (int)helper.request("phoneCheck.do", phoneNum);
		
	}
	
	/*
	 * 로그인
	 */
	public MemberDto login(Map<String, Object> map) {
		String enPw = pwEncoder.encode((String) map.get("pw"));
		map.put("pw", enPw);
		return (MemberDto)helper.request("login.do", map);
	}
	
	/*
	 * 아이디 찾기
	 */
	public String idSearch(Map<String, Object> map) {
		return (String)helper.request("idSearch,do", map);
	}
	
	/*
	 * 비밀번호 찾기
	 */
	public int pwSearch(Map<String, String> map) {
		return (int)helper.request("pwSearch.do", map);
	}
	
	/*
	 * 개인정보 변경
	 */
	public int updateInfo(Map<String, Object> map) {
		return (int)helper.request("updateInfo.do", map);
	}
	
	/*
	 * 비밀번호 변경
	 */
	public int updatePw(MemberDto dto) {
		return (int)helper.request("updatePw.do", dto);
	}
	
	/*
	 * 회원탈퇴
	 */
	public int quitMember(String email) {
		return (int)helper.request("quitMember.do", email);
	}
	
	
	
	@Override
	public List<DeliveryDto> selectDeliveryList(String email, String auth) {
		return null;
	}

	@Override
	public boolean updatedeliveryStrat(String deliveryCode) {
		return (boolean)helper.request("updatedeliveryStrat.do", deliveryCode);
	}
	
}
