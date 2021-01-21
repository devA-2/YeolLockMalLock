/**
 * 
 */
package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dev2.ylml.dto.DeliveryDto;
import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageGoodsDto;
import com.dev2.ylml.dto.StorageListDto;
import com.dev2.ylml.dto.UserStorageListDto;
import com.dev2.ylml.dto.LostPropertyDto;
import com.dev2.ylml.dto.RFIDDto;
import com.dev2.ylml.dto.ReportDto;

/**
 * @author nerdhead
 *
 */
public interface UserIService {
	/**
	 * Sample 참고용 method!!!
	 * @param id
	 * @param pw
	 * @return
	 */
	HashMap<String, String> getSampleData(String id, String pw);
	
	/**
	 * 회원가입 
	 * @param memberDto
	 * @return boolean
	 */
	public boolean insertMember(MemberDto memberDto);
	
	/**
	 * 아이디 중복체크
	 * @param email
	 * @return
	 */
	public int idCheck(String email);
	
	/**
	 * 휴대폰 중복체크
	 * @param phoneNum
	 * @return int
	 */
	public int phoneCheck(String phoneNum);
	
	/**
	 * 로그인
	 * 
	 * @param map
	 * @return MemberDto
	 */
	public MemberDto login(Map<String, Object> map);
	
	/**
	 * api 간편 로그인
	 * 
	 * @param map
	 * @return MemberDto
	 */
	public MemberDto apiLogin(Map<String, Object> map);
	
	/**
	 * 일반회원 임시권한 변경
	 * 
	 * @param dto
	 * @return boolean
	 */
	public boolean authUpdate(MemberDto dto);
	
	/**
	 * 아이디 찾기
	 * 
	 * @param map
	 * @return String(
	 */
	public String idSearch(Map<String, Object> map);
	
	/**
	 * 비밀번호 찾기
	 * 
	 * @param map
	 * @return int
	 */
	public int pwSearch(Map<String, String> map);
	
	/**
	 * 개인정보 변경(지금은 휴대폰 번호만 변경가능하지만, 추후에 추가 가능)
	 * 
	 * @param map
	 * @return int
	 */
	public int updateInfo(Map<String, Object> map);
	
	/**
	 * 비밃번호 변경
	 * 
	 * @param dto
	 * @return int
	 */
	public int updatePw(MemberDto dto);
	
	/**
	 * 탈퇴전 서비스 사용여부 확인
	 * 
	 * @param email
	 * @return int
	 */
	public int usingCheck(String email);
	
	/**
	 * 회원탈퇴
	 * 
	 * @param email
	 * @return int
	 */
	public int quitMember(String email);
	
	
	
	
	
	
	
	/**
	 * 전체 보관함 정보 출력해서 마커표시
	 * @return storage_id,lng,lat
	 */
	public List<Map<String, Object>> selectMap();
	/**
	 * 검색창 자동완성 위한 보관함 리스트 정보 받아오기
	 * @return List<StorageListDto>
	 */
	public List<Map<String,String>> selectStorageList();
	/**
	 * ajax로 해당 보관함 정보, 사용가능한 갯수 가져오기
	 * @param id
	 * @return id,name,subway,address,detail,cnt
	 */
	public StorageListDto ajaxCountStorage(String id);
	/**
	 * 해당 보관함의 사용여부 가져오기
	 * @param id
	 * @return box_seq , box_status
	 */
	public List<StorageBoxDto> selectStorageStatus(String id);
	/**
	 * 보관함seq, id, 사용자email 받아서 보관 등록
	 * 보관 정보 등록 + 해당보관함 사용중처리 + 결제코드 등록 + 결제코드 수정
	 * @param boxSeq, id, email
	 */
	public boolean insertGoods(Map<String, Object> map);
	/**
	 * 0시 기준 모든 보관함 사용중,사용대기->사용가능,사용불가로 변경
	 * @param list
	 * @return int
	 */
	public int updateAllStatus(List<String> list);
	/**
	 * 연장시간, 연장횟수 수정 + 연장 금액 수정
	 * @param map
	 */
	public boolean updateExtend(Map<String, Object> map);
	/**
	 * 회수전 키 대조
	 * @param key
	 * @return cost_code
	 */
	public String compareKey(String key);
	/**
	 * 키 일치할때 결제 전에 할증비용 추가 
	 * @param costCode, overCost
	 */
	public boolean updateExtraCost(Map<String, Object> map);
	/**
	 * 결제완료 후 
	 * 사용중인 보관함 대기여부 확인하고 사용가능 처리 + 물품 정보 삭제 
	 * @param costCode + selectKey id 저장해야해서 map 사용
	 */
	public boolean afterPayment(Map<String,String> map);
	/**
	 * 수령 사용자 아이디 확인하기
	 * @param email
	 * @return email
	 */
	public String checkOutEmail(String email);
	/**
	 * 입력한 수령 사용자 보관물품에 등록
	 * @param map
	 */
	public boolean updateOutUser(Map<String,Object> map);
	/**
	 * 결제 완료후 반품 신청
	 * 물품 정보 받아옴 + 물품정보 삭제 + 반품 등록 + 결제코드 등록 + 결제코드 수정
	 * @param map(costCode,message)
	 */
	public boolean insertReturn(Map<String,String> map);
	
	
	
	/**
	 * 보관 정보 조회(사용자)
	 */
	public List<UserStorageListDto> selectUserStorageList(String email);
	
	/**
	 * 보관함ID로 STORAGEBOX_LIST 조회
	 */
	public StorageListDto selectStorageBoxList(String storageId);
	
	/**
	 * 보관함SEQ, 보관함ID로 STORAGEBOX_GOODS 조회
	 */
	public StorageGoodsDto selectStorageGoods(Map<String, Object> map);
	
	/**
	 * 타임테이블 SEQ 조회
	 */
	public int selectTimeTableSeq(String subway);
	
	/**
	 * 역 갯수 조회
	 */
	public int selectSubwayCnt();
	
	/**
	 * 전체 배송원 조회
	 */
	public List<MemberDto> selectDeliveryMan();
	
	/**
	 * 배송원 현재 위치 조회
	 */
	public String selectDeliveryLoc(String deliverymanId);
	
	/**
	 * 배송 물량 확인
	 */
	public int selectDeliveryQty(String deliverymanId);
	
	/**
	 * 배송 소요 시간 계산
	 */
	public int selectDeliveryTime(Map<String, Integer> subwaySeqs);
	
	/**
	 * 배송 등록
	 */
	public boolean insertDelivery(DeliveryDto delDto, StorageGoodsDto goodsDto);
	
	/**
	 * 배송 정보 조회(사용자, 배송원)
	 */
	public List<DeliveryDto> selectDeliveryList(Map<String, String> map);
	
	
	/**
	 * 결제 상태 업데이트
	 */
	public boolean updateCostStatus(String costCode);
	
//	 --------------------------------------- AJH -------------------------------
	/** 유실물 전체 리스트 조회
	 * @return
	 */
	public List<LostPropertyDto> selectAllLostProperty();
	
	/** 유실물 상세 조회
	 * @param seq
	 * @return
	 */
	public LostPropertyDto selectOneLostProperty(String seq);
	
	/** 신고 글 작성
	 * @param dto
	 * @return
	 */
	public boolean insertReport(ReportDto dto);

	/** 답변 글 작성 페이지 이동
	 * @param dto
	 * @return
	 */
	public boolean replyReport(ReportDto dto);

	/** 답변 글 작성 실행
	 * @param dto
	 * @return
	 */
	public boolean reply(ReportDto dto);
	
	/** 신고 글 수정 페이지 이동
	 * @param dto
	 * @return
	 */
	public boolean modifyReport(ReportDto dto);

	/** 전체 신고 글 리스트 조회
	 * @return
	 */
	public List<ReportDto> selectAllReport();

	/** 신고 글 상세 조회
	 * @param refer
	 * @return
	 */
	public List<ReportDto> selectDetailReport(String refer);

	/** 처리 상태 변경
	 * @param dto
	 * @return
	 */
	public boolean updateProcessStatus(ReportDto dto);
	
	/** 상세 조회한 신고 글로 다시 접근
	 * @param seq
	 * @return
	 */
	public ReportDto selectDetail(String seq);
	
	/** 신고 글을 email로 검색
	 * @param email
	 * @return
	 */
	public List<ReportDto> searchId(String email); // 신고글 검색
	
	/** 유실물 글을 email로 검색
	 * @param receipt_user_id
	 * @return
	 */
	public List<LostPropertyDto> searchId2(String receipt_user_id); // 유실물 검색
	
	/** 유실물 입력
	 * @param dto
	 * @return
	 */
	public boolean insertLostProperty(ReportDto dto);
	
	// ------------------------------- 물품 보관 ----------------------------- 
	/**
	 * 키 제외하고 물품부터 등록
	 * @param dto
	 * @return
	 */
	public boolean insertGoods(RFIDDto dto);
	
	/**
	 * 물품 보관함의 seq, 보관하는 사람의 email, 보관하는 사람의 TAG를 합쳐 키 생성하고 키 등록하기
	 * @param dto
	 * @return
	 */
	public boolean insertKey(RFIDDto dto);
	
// ----------------------------- 키 전송 -----------------------------
	
	/**
	 * STORAGE_GOODS의 OUT_USER를 키의 수신자이자 보관함에서 물품을 꺼내는 사람의 email로 변경
	 * @param dto
	 * @return
	 */
	public boolean updateOutUser(RFIDDto dto);
	
	
	/**
	 * STORAGE_GOODS의 KEY를 보관함에서 물품을 꺼내는 사람의 KEY로 재설정해주기
	 * -> 보관함 seq + 보관함에서 물품 꺼내는 사람의 email + 보관함에서 물품 꺼내는 사람의 tag로 키 생성해서 update.
	 * @param dto
	 * @return
	 */
	public boolean updateKey(RFIDDto dto);
	
}
