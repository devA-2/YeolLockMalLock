package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.CostDto;
import com.dev2.ylml.dto.DeliveryDto;
import com.dev2.ylml.dto.LostPropertyDto;
import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.RFIDDto;
import com.dev2.ylml.dto.ReportDto;
import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageGoodsDto;
import com.dev2.ylml.dto.StorageListDto;
import com.dev2.ylml.dto.UserStorageListDto;
import com.dev2.ylml.util.ApiClientHelper;
import com.dev2.ylml.util.PagingVO;


@Service
public class UserService implements UserIService{
	@Autowired
	ApiClientHelper helper;
	
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getSampleData(String id, String pw) {
		//URL+PW로 데이터를 만들어서 전달
		//list의 0번째인 Certification check후 false일경우 return null;
		
		Map<String, String> map=new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		
		return (HashMap<String, String>)helper.request("sampleData.do", map);
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
		return (MemberDto)helper.request("login.do", map);
	}
	
	/*
	 * api 간편 로그인
	 */
	public MemberDto apiLogin(Map<String, Object> map) {
		return (MemberDto)helper.request("apiLogin.do", map);
	}
	
	/*
	 * 일반회원 임시권한 변경
	 */
	public boolean authUpdate(MemberDto dto) {
		return (boolean)helper.request("authUpdate.do", dto);
	}
	
	/*
	 * 아이디 찾기
	 */
	public String idSearch(Map<String, Object> map) {
		return (String)helper.request("idSearch.do", map);
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
	 * 탈퇴전 사용서비스 확인
	 */
	public int usingCheck(String email) {
		return (int)helper.request("usingCheck.do", email);
	}
	
	/*
	 * 회원탈퇴
	 */
	public int quitMember(String email) {
		return (int)helper.request("quitMember.do", email);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> selectMap() {
		return (List<Map<String, Object>>)helper.request("selectMap.do");
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> selectStorageList() {
		return (List<Map<String, String>>)helper.request("selectStorageList.do");
	}

	@Override
	public StorageListDto ajaxCountStorage(String id) {
		return (StorageListDto)helper.request("ajaxCountStorage.do", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StorageBoxDto> selectStorageStatus(String id) {
		return (List<StorageBoxDto>)helper.request("selectStorageStatus.do", id);
	}
	@Override
	public int tagNFC(MemberDto mem) {
		return (int)helper.request("tagNFC.do",mem);
	}
	@Override
	public boolean insertGoods(Map<String, Object> map) {
		return (boolean)helper.request("insertGoods.do", map);
	}

	@Override
	public int scheduledForMidnight(List<String> list) {
		return (int)helper.request("scheduledForMidnight.do", list);
	}

	@Override
	public boolean updateExtend(Map<String, Object> map) {
		return (boolean)helper.request("updateExtend.do", map);
	}

	@Override
	public CostDto compareKey(String key) {
		return (CostDto)helper.request("compareKey.do", key);
	}

	@Override
	public boolean updateExtraCost(Map<String, Object> map) {
		return (boolean)helper.request("updateExtraCost.do", map);
	}

	@Override
	public boolean afterPayment(Map<String, String> map) {
		return (boolean)helper.request("afterPayment.do", map);
	}

	@Override
	public String checkOutEmail(String email) {
		return (String)helper.request("checkOutEmail.do", email);
	}

	@Override
	public boolean updateOutUser(Map<String, Object> map) {
		return (boolean)helper.request("updateOutUser.do", map);
	}

	@Override
	public boolean insertReturn(Map<String, String> map) {
		return (boolean)helper.request("insertReturn.do", map);
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserStorageListDto> selectUserStorageList(String email) {
		return (List<UserStorageListDto>) helper.request("selectUserStorageList.do", email);
	}

	@Override
	public StorageListDto selectStorageBoxList(String storageId) {
		return (StorageListDto) helper.request("selectStorageBoxList.do", storageId);
	}

	@Override
	public StorageGoodsDto selectStorageGoods(Map<String, Object> map) {
		return (StorageGoodsDto) helper.request("selectStorageGoods.do", map);
	}

	@Override
	public int selectTimeTableSeq(String subway) {
		return (int) helper.request("selectTimeTableSeq.do", subway);
	}

	@Override
	public int selectSubwayCnt() {
		return (int) helper.request("selectSubwayCnt.do");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberDto> selectDeliveryMan() {
		return (List<MemberDto>) helper.request("selectDeliveryMan.do");
	}

	@Override
	public String selectDeliveryLoc(String deliverymanId) {
		return (String) helper.request("selectDeliveryLoc.do", deliverymanId);
	}

	@Override
	public int selectDeliveryQty(String deliverymanId) {
		return (int) helper.request("selectDeliveryQty.do", deliverymanId);
	}

	@Override
	public int selectDeliveryTime(Map<String, Integer> subwaySeqs) {
		return (int) helper.request("selectDeliveryTime.do", subwaySeqs);
	}

	@Override
	public boolean insertDelivery(DeliveryDto deliveryDto, StorageGoodsDto storageGoodsDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("DeliveryDto", deliveryDto);
		map.put("StorageGoodsDto", storageGoodsDto);
		return (boolean) helper.request("insertDelivery.do", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeliveryDto> selectDeliveryList(Map<String, String> map) {
		return (List<DeliveryDto>) helper.request("selectDeliveryList.do", map);
	}

	@Override
	public boolean updateCostStatus(String costCode) {
		return (boolean)helper.request("updateCostStatus.do", costCode);
	}
	
	// ------------------------------ AJH -------------------------------------

	@Override
	public List<LostPropertyDto> selectAllLostProperty() {
		return (List<LostPropertyDto>) helper.request("selectAllLostProperty.do");
	}

	@Override
	public LostPropertyDto selectOneLostProperty(String seq) {
		return (LostPropertyDto) helper.request("selectOneLostProperty.do", seq);
	}

	@Override
	public boolean insertReport(ReportDto dto) {
		return (boolean) helper.request("insertReport.do", dto);
	}

	@Override
	public boolean replyReport(ReportDto dto) {
		return (boolean) helper.request("replyReport.do", dto);
	}

	@Override
	public boolean reply(ReportDto dto) {
		return (boolean)helper.request("reply.do", dto);
	}

	@Override
	public boolean modifyReport(ReportDto dto) {
		return (boolean)helper.request("modifyReport.do", dto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportDto> selectAllReport() {
		return (List<ReportDto>) helper.request("selectAllReport.do");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportDto> selectDetailReport(String refer) {
		return (List<ReportDto>) helper.request("selectDetailReport.do", refer);
	}

	@Override
	public boolean updateProcessStatus(ReportDto dto) {
		return (boolean) helper.request("updateProcessStatus.do", dto);
	}

	@Override
	public ReportDto selectDetail(String seq) {
		return (ReportDto) helper.request("selectDetail.do", seq);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportDto> searchId(String email) {
		return (List<ReportDto>) helper.request("searchId.do", email);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LostPropertyDto> searchId2(String receipt_user_id) {
		return (List<LostPropertyDto>) helper.request("searchId2.do", receipt_user_id);
	}

	// 대기
	@Override
	public boolean insertLostProperty(ReportDto dto) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean insertKey(RFIDDto dto) {
		return (boolean) helper.request("insertKey.do", dto);
	}


	@Override
	public boolean updateKey(RFIDDto dto) {
		return (boolean) helper.request("updateKey.do", dto);
	}


	//페이징 
	@Override
	public int countReport() {
		return (int)helper.request("countReport.do");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportDto> selectReport(PagingVO vo) {
		return (List<ReportDto>) helper.request("selectReport.do",vo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LostPropertyDto> selectAllPagingLostProperty(PagingVO vo) {
		return (List<LostPropertyDto>) helper.request("selectAllPagingLostProperty.do",vo);
	}

	@Override
	public int countLostProperty() {
		return (int)helper.request("countLostProperty.do");
	}



	
}
