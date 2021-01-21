package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.Manager_MemberDto;
import com.dev2.ylml.dto.Manager_StorageDto;
import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageGoodsDto;
import com.dev2.ylml.util.ApiClientHelper;


@Service
public class ManagerService implements ManagerIService{
	@Autowired
	ApiClientHelper helper;
	
	@Autowired
	private PasswordEncoder pwEncoder;

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
	
	/*
	 * 로그인
	 */
	@Override
	public MemberDto login(Map<String, Object> map) {
		String enPw = pwEncoder.encode((String) map.get("pw"));
		map.put("pw", enPw);
		return (MemberDto)helper.request("login.do", map);
	}
	
	/*
	 * 회원 전체리스트
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberDto> selectAll(String email) {
		return (List<MemberDto>)helper.request("selectAll.do", email);

	}
	
	/*
	 * 회원 아이디로 검색
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> memberIdSearch() {
		return (List<String>)helper.request("memberIdSearch.do");
	}

	@Override
	public MemberDto detailMember(String email) {
		return (MemberDto)helper.request("detail.do", email);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StorageGoodsDto> memberUsing(String email) {
		return (List<StorageGoodsDto>)helper.request("memberUsing.do",email);
	}
	
	
	

	//-------------------------------------------------------------
	
	// 관리자 로그인 
//	@Override
//	public Manager_MemberDto loginMember(Map<String, Object> map) {
//		return (Manager_MemberDto)helper.request("loginMember.do", map);
//	}

	// -------------------------------담당자 및 배송원---------------------------------
	
	// 담당자 및 배송원 전체 정보조회
	//TODO : 담당자 및 배송원 전체 정보조회, 파라미터 없음
	@Override
	public List<Manager_MemberDto> selectallDelivery() {
		return (List<Manager_MemberDto>)helper.request("selectallDelivery.do");
	}

	//  담당자 및 배송원 상세 정보조회
	@Override
	public Manager_MemberDto selectDetail(String email) {
		return (Manager_MemberDto)helper.request("selectDetail.do", email);
	}

	// 배송 정보조회
	@Override
	public Manager_MemberDto DeliveryInfo(String email) {
		return (Manager_MemberDto)helper.request("DeliveryInfo.do", email);
	}

	// 임시권한 담당자 및 배송원 정보조회
	//TODO : 임시권한 담당자 및 배송원 정보조회, 파라미터 없음
	@Override
	public List<Manager_MemberDto> selectTempDelivery() {
		return (List<Manager_MemberDto>)helper.request("selectTempDelivery.do");
	}

	// 담당자 및 배송원 임시권한 변경
	@Override
	public boolean modifyAuth(String email) {
		return (boolean)helper.request("modifyAuth.do", email);
	}

	// 담당자 및 배송원의 아이디를 통해 정보 조회
	@Override
	public Manager_MemberDto selectIdDelivery(String email) {
		return (Manager_MemberDto)helper.request("selectIdDelivery.do", email);
	}

	// -------------------------------보관함---------------------------------
	
	// 보관함 전체조회
	//TODO : 보관함 전체조회, 파라미터 없음
	@Override
	public List<Manager_StorageDto> selectAllStorage() {
		return (List<Manager_StorageDto>)helper.request("selectAllStorage.do");
	}

	// 보관함 ID 조회
	@Override
	public Manager_StorageDto selectIdStorage(String storage_id) {
		return (Manager_StorageDto)helper.request("selectIdStorage.do", storage_id);
	}

	// 보관함 지하철역으로 조회
	@Override
	public Manager_StorageDto selectSubwayStorage(String subway) {
		return (Manager_StorageDto)helper.request("selectSubwayStorage.do", subway);
	}

	// 보관함 상세정보 조회
	@Override
	public Manager_StorageDto selectDetailStorage(String storage_id) {
		return (Manager_StorageDto)helper.request("selectDetailStorage.do", storage_id);
	}

	// 보관함 상태조회
	@SuppressWarnings("unchecked")
	@Override
	public List<Manager_StorageDto> selectBoxStatus(String storage_id) {
		return (List<Manager_StorageDto>)helper.request("selectBoxStatus.do", storage_id);
	}

	// 보관함 등록
	@Override
	public boolean registStorage(Manager_StorageDto dto) {
		return (boolean)helper.request("registStorage.do", dto);
	}

	// 보관함 수정
	@Override
	public boolean modifyStorage(Manager_StorageDto dto) {
		return (boolean)helper.request("modifyStorage.do", dto);
	}

	// 사용불가 보관함이 해결되었을때 관리자가 사용 가능 보관함으로 변경
	@Override
	public boolean ActivateStorage(String seq) {
		return (boolean)helper.request("ActivateStorage.do", seq);
	}

}
