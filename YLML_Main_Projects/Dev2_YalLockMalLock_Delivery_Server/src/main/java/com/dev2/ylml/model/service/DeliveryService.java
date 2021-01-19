package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.ylml.util.ApiClientHelper;


@Service
public class DeliveryService implements DeliveryIService{
	@Autowired
	ApiClientHelper helper;

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
	
	//public boolean insertMember(MemberDto memberDto) {
	//	return (boolean)helper.request("insertMember.do", memberDto);
	//}
	//필요한 dto 들고 오셔야하구여
	//iService 에 본인이 사용할 메소드들을 api 서버에서 복사해서 input(파라미터) output(리턴) 수정해주시고 구현해주시면 됩니당
	
}
