/**
 * 
 */
package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dev2.ylml.dto.LostPropertyDto;
import com.dev2.ylml.dto.Manager_MemberDto;
import com.dev2.ylml.dto.Manager_StorageDto;
import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.ReportDto;
import com.dev2.ylml.dto.StorageGoodsDto;

/**
 * @author nerdhead
 *
 */
public interface ManagerIService {
	/**
	 * Sample 참고용 method!!!
	 * @param id
	 * @param pw
	 * @return
	 */
	HashMap<String, String> getSampleData(String id, String pw);
	
	/**
	 * 로그인
	 * 
	 * @param map
	 * @return MemberDto
	 */
	public MemberDto adminLogin(Map<String, Object> map);
	
	/**
	 * 회원 전체 목록 조회
	 * 
	 * @param email
	 * @return list
	 */
	public List<MemberDto> selectAll(Map<String, Object> map);
	/**
	 * 전체 회원 수 조회
	 * @return
	 */
	public int countMember(String email);

	/**
	 * 회원 아이디로 검색
	 * 
	 * @return list
	 */
	public List<String> memberIdSearch();

	/**
	 * 회원 상세 정보 조회
	 * 
	 * @param email
	 * @return dto
	 */
	public MemberDto detailMember(String email);

	/**
	 * 회원 사용중인 서비스 조회
	 * 
	 * @param email
	 * @return list
	 */
	public List<StorageGoodsDto> memberUsing(String email);
	
	//-------------------------------------------------------------
	
	/** 관리자 로그인 
	 * @param map
	 * @return
	 */
	public Manager_MemberDto loginMember(Map<String, Object> map);
	
	// -------------------------------담당자 및 배송원---------------------------------
	
	/** 담당자 및 배송원 전체 정보조회
	 * @return
	 */
	public List<Manager_MemberDto> selectAllDelivery();
	
	/** 담당자 및 배송원 상세 정보조회
	 * @param email
	 * @return
	 */
	public Manager_MemberDto selectDetail(String email);
	
	/** 배송 정보조회
	 * @param email
	 * @return
	 */
	public Manager_MemberDto DeliveryInfo(String email);
	
	/** 임시권한 담당자 및 배송원 정보조회
	 * @return
	 */
	public List<Manager_MemberDto> selectTempDelivery();
	
	/** 담당자 및 배송원 임시권한 변경
	 * @param dto
	 * @return
	 */
	public boolean modifyAuth(String email);
	
	/** 담당자 및 배송원의 아이디를 통해 정보 조회
	 * @param email
	 * @return
	 */
	public Manager_MemberDto selectIdDelivery(String email);
	
	// -------------------------------보관함---------------------------------
	
	/**	보관함 전체조회
	 * @return
	 */
	public List<Manager_StorageDto> selectAllStorage();
	
	/** 보관함 ID 조회
	 * 보관함 ID, 보관함 지하철역으로 검색하여 리스트 출력
	 * (보관함 ID, 이름, 지하철역, 좌표, 좌표)
	 * @param storage_id
	 * @return
	 */
	public Manager_StorageDto selectIdStorage(String storage_id);
	
	/** 보관함 지하철역으로 조회
	 * 보관함 지하철역 검색 리스트 출력
	 * (보관함 ID, 이름, 지하철역, 좌표, 좌표)
	 * @param subway
	 * @return
	 */
	public List<Manager_StorageDto> selectSubwayStorage(String subway);
	
	/**	보관함 상세정보 조회
	 * (보관함 ID, 이름, 지하철역, 좌표, 좌표, 
	 * 보관함 갯수, 보관함 상태, 담당자)
	 * @return
	 */
	public Manager_StorageDto selectDetailStorage(String storage_id);
	
	/** 보관함 상태조회
	 * @return
	 */
	public List<Manager_StorageDto> selectBoxStatus(String storage_id);
	
	/**	보관함 등록
	 * 신규 보관함 등록
	 * (보관함 ID, 이름, 지하철역, 실제주소, 상세주소, LNG, LAT, 담당자)
	 * @param dto
	 * @return
	 */
	public boolean registStorage(Manager_StorageDto dto);
	
	/**	보관함 수정
	 * 변동사항이 있다면 물품 보관함에서 조회
	 * (보관함ID로 검색) 후 선택하여 수정
	 * @param dto
	 * @return
	 */
	public boolean modifyStorage(Manager_StorageDto dto);
	
	/**	사용불가 보관함이 해결되었을때 관리자가 사용 가능 보관함으로 변경
	 * @param seq
	 * @return
	 */
	public boolean activateStorage(Manager_StorageDto dto);
	
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
	public ReportDto selectDetailReportGo(String seq);
	
	/** 유실물 전체 리스트 조회
	 * @return
	 */
	public List<LostPropertyDto> selectAllLostProperty();
	
	/** 유실물 상세 조회
	 * @param seq
	 * @return
	 */
	public LostPropertyDto selectOneLostProperty(String seq);
	
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
	
}
