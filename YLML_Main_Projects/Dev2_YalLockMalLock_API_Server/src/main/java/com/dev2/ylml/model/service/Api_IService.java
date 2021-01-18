package com.dev2.ylml.model.service;

import java.util.Map;

public interface Api_IService {
	/**
	 * Sample 참고용 method!!!
	 * 
	 * @param id
	 * @param pw
	 * @return
	 */
	public Map<String, Object> getSampleData(Map<String, Object> map);

	/************************************회원관리************************************/
	/************************************기수************************************/
	/**
	 * 회원가입
	 * 
	 * @param dto
	 * @return boolean
	 */
	public Map<String, Object> /* boolean */ insertMember(Map<String, Object> map/* MemberDto dto */);

	/**
	 * 아이디 중복검사
	 * 
	 * @param email
	 * @return int
	 */
	public Map<String, Object>/* int */ idCheck(Map<String, Object> map/* String email */);

	/**
	 * 휴대폰 중복검사
	 * 
	 * @param phoneNum
	 * @return
	 */
	public Map<String, Object>/* int */ phoneCheck(Map<String, Object> map/* String phoneNum */);

	/**
	 * 로그인
	 * 
	 * @param map
	 * @return dto
	 */
	public Map<String, Object>/* MemberDto */ login(Map<String, Object> map/* Map<String, Object> map */);

	/**
	 * api 간편 로그인
	 * 
	 * @param map
	 * @return dto
	 */
	public Map<String, Object>/* MemberDto */ apiLogin(Map<String, Object> map/* Map<String, Object> map */);

	/**
	 * 일반회원 임시권한 변경
	 * 
	 * @param dto
	 * @return boolean
	 */
	public Map<String, Object>/* boolean */ authUpdate(Map<String, Object> map/* MemberDto dto */);

	/**
	 * 아이디 찾기
	 * 
	 * @param map
	 * @return String
	 */
	public Map<String, Object>/* String */ idSearch(Map<String, Object> map/* Map<String, Object> map */);

	/**
	 * 비밀번호 찾기 (입력한 정보 유효한지 검사)
	 * 
	 * @param map
	 * @return int
	 */
	public Map<String, Object>/* int */ pwSearch(Map<String, Object> map/* Map<String, String> map */);

	/**
	 * 개인정보 변경(지금은 휴대폰 번호만 변경 가능하지만, 추후에 추가 될 수 있음)
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object>/* int */ updateInfo(Map<String, Object> map/* Map<String, Object> map */);

	/**
	 * 비밀번호 변경
	 * 
	 * @param dto
	 * @return int
	 */
	public Map<String, Object>/* int */ updatePw(Map<String, Object> map/* MemberDto dto */);

	/**
	 * 탈퇴전 서비스 사용여부확인
	 * 
	 * @param email
	 * @return int
	 */
	public Map<String, Object>/* int */ usingCheck(Map<String, Object> map/* String email */);

	/**
	 * 회원탈퇴
	 * 
	 * @param email
	 * @return int
	 */
	public Map<String, Object>/* int */ quitMember(Map<String, Object> map/* String email */);
	
	/************************************보관함************************************/
	/************************************다은************************************/

	/**
	 * 전체 보관함 정보 출력해서 마커표시
	 * 
	 * @return storage_id,lng,lat
	 */
	public Map<String, Object>/* List<Map<String, Object>> */ selectMap(Map<String, Object> map);

	/**
	 * 검색창 자동완성 위한 보관함 리스트 정보 받아오기
	 * 
	 * @return List<StorageListDto>
	 */
	public Map<String, Object>/* List<Map<String, String>> */ selectStorageList(Map<String, Object> map);

	/**
	 * ajax로 해당 보관함 정보, 사용가능한 갯수 가져오기
	 * 
	 * @param id
	 * @return id,name,subway,address,detail,cnt
	 */
	public Map<String, Object>/* StorageListDto */ ajaxCountStorage(Map<String, Object> map/* String id */);

	/**
	 * 해당 보관함의 사용여부 가져오기
	 * 
	 * @param id
	 * @return box_seq , box_status
	 */
	public Map<String, Object>/* List<StorageBoxDto> */ selectStorageStatus(Map<String, Object> map/* String id */);

	/**
	 * 보관함seq, id, 사용자email 받아서 보관 등록 보관 정보 등록 + 해당보관함 사용중처리 + 결제코드 등록 + 결제코드 수정
	 * 
	 * @param boxSeq, id, email
	 */
	public Map<String, Object>/* boolean */ insertGoods(Map<String, Object> map/* Map<String, Object> map */);

	/**
	 * 0시 기준 모든 보관함 사용중,사용대기->사용가능,사용불가로 변경
	 * 
	 * @param list
	 * @return int
	 */
	public Map<String, Object>/* int */ updateAllStatus(Map<String, Object> map/* List<String> list */);

	/**
	 * 연장시간, 연장횟수 수정 + 연장 금액 수정
	 * 
	 * @param map
	 */
	public Map<String, Object>/* boolean */ updateExtend(Map<String, Object> map/* Map<String, Object> map */);

	/**
	 * 회수전 키 대조
	 * 
	 * @param key
	 * @return cost_code, out_user
	 */
	public Map<String, Object>/* UserGoodsDto */ compareKey(Map<String, Object> map/* String key */);

	/**
	 * 키 일치할때 결제 전에 할증비용 추가
	 * 
	 * @param costCode, overCost
	 */
	public Map<String, Object>/* boolean */ updateExtraCost(Map<String, Object> map/* Map<String, Object> map */);

	/**
	 * 결제완료 후 사용중인 보관함 대기여부 확인하고 사용가능 처리 + 물품 정보 삭제
	 * 
	 * @param costCode + selectKey id 저장해야해서 map 사용
	 */
	public Map<String, Object>/* boolean */ afterPayment(Map<String, Object> map/* Map<String, String> map */);

	/**
	 * 수령 사용자 아이디 확인하기
	 * 
	 * @param email
	 * @return email
	 */
	public Map<String, Object>/* String */ checkOutEmail(Map<String, Object> map/* String email */);

	/**
	 * 입력한 수령 사용자 보관물품에 등록
	 * 
	 * @param map
	 */
	public Map<String, Object>/* boolean */ updateOutUser(Map<String, Object> map/* Map<String, Object> map */);

	/**
	 * 결제 완료후 반품 신청 물품 정보 받아옴 + 물품정보 삭제 + 반품 등록 + 결제코드 등록 + 결제코드 수정
	 * 
	 * @param costCode
	 * @param message
	 */
	public Map<String, Object>/* boolean */ insertReturn(Map<String, Object> map/* String costCode, String message */);

	/************************************정은************************************/

	/**
	 * 보관 정보 조회(사용자)
	 */
	public Map<String, Object>/* List<UserStorageListDto> */ selectUserStorageList(
			Map<String, Object> map/* Map<String, String> map */);

	/**
	 * 보관함ID로 보관함 정보 조회
	 */
	public Map<String, Object>/* List<StorageBoxListDto> */ selectStorageBoxList(
			Map<String, Object> map/* String storageId */);

	/**
	 * 타임테이블 SEQ 조회
	 */
	public Map<String, Object>/* int */ selectTimeTableSeq(Map<String, Object> map/* String subway */);

	/**
	 * 전체 배송원 조회
	 */
	public Map<String, Object>/* List<MemberDto> */ selectDeliveryMan(Map<String, Object> map);

	/**
	 * 배송원 현재 위치 조회
	 */
	public Map<String, Object>/* String */ selectDeliveryLoc(Map<String, Object> map/* String deliverymanId */);

	/**
	 * 배송 물량 확인
	 */
	public Map<String, Object>/* int */ selectDeliveryQty(Map<String, Object> map/* String deliverymanId */);

	/**
	 * 배송 소요 시간 계산
	 */
	public Map<String, Object>/* int */ selectDeliveryTime(
			Map<String, Object> map/* Map<String, Integer> subwaySeqs */);

	/**
	 * 배송 등록
	 */
	public Map<String, Object>/* boolean */ insertDelivery(
			Map<String, Object> map/* DeliveryDto delDto, StorageGoodsDto goodsDto */);

	/**
	 * 배송 정보 조회(사용자)
	 */
	public Map<String, Object>/* List<UserDeliveryListDto> */ selectUserDeliveryList(
			Map<String, Object> map/* String email */);
	/************************************게시판************************************/
	/************************************지훈************************************/
	/**
	 * 유실물 전체 리스트 조회
	 * 
	 * @return
	 */
	public Map<String, Object>/* List<LostPropertyDto> */ selectAllLostProperty(Map<String, Object> map);

	/**
	 * 유실물 상세 조회
	 * 
	 * @param seq
	 * @return
	 */
	public Map<String, Object>/* LostPropertyDto */ selectOneLostProperty(Map<String, Object> map/* String seq */);

	/**
	 * 신고 글 작성
	 * 
	 * @param dto
	 * @return
	 */
	public Map<String, Object>/* boolean */ insertReport(Map<String, Object> map/* ReportDto dto */);

	/**
	 * 답변 글 작성 페이지 이동
	 * 
	 * @param dto
	 * @return
	 */
	public Map<String, Object>/* boolean */ replyReport(Map<String, Object> map/* ReportDto dto */);

	/**
	 * 답변 글 작성 실행
	 * 
	 * @param dto
	 * @return
	 */
	public Map<String, Object>/* boolean */ reply(Map<String, Object> map/* ReportDto dto */);

	/**
	 * 신고 글 수정 페이지 이동
	 * 
	 * @param dto
	 * @return
	 */
	public Map<String, Object>/* boolean */ modifyReport(Map<String, Object> map/* ReportDto dto */);

	/**
	 * 전체 신고 글 리스트 조회
	 * 
	 * @return
	 */
	public Map<String, Object>/* List<ReportDto> */ selectAllReport(Map<String, Object> map);

	/**
	 * 신고 글 상세 조회
	 * 
	 * @param refer
	 * @return
	 */
	public Map<String, Object>/* List<ReportDto> */ selectDetailReport(Map<String, Object> map/* String refer */);

	/**
	 * 처리 상태 변경
	 * 
	 * @param dto
	 * @return
	 */
	public Map<String, Object>/* boolean */ updateProcessStatus(Map<String, Object> map/* ReportDto dto */);

	/**
	 * 상세 조회한 신고 글로 다시 접근
	 * 
	 * @param seq
	 * @return
	 */
	public Map<String, Object>/* ReportDto */ selectReportDetail(Map<String, Object> map/* String seq */);

	/**
	 * 신고 글을 email로 검색
	 * 
	 * @param email
	 * @return
	 */
	public Map<String, Object>/* List<ReportDto> */ searchId(Map<String, Object> map/* String email */); // 신고글 검색

	/**
	 * 유실물 글을 email로 검색
	 * 
	 * @param receipt_user_id
	 * @return
	 */
	public Map<String, Object>/* List<LostPropertyDto> */ searchId2(
			Map<String, Object> map/* String receipt_user_id */); // 유실물 검색

	/**
	 * 유실물 입력
	 * 
	 * @param dto
	 * @return
	 */
	public Map<String, Object>/* boolean */ insertLostProperty(Map<String, Object> map/* ReportDto dto */);
	/************************************관리자************************************/
	/************************************재우************************************/
	/**
	 * 관리자 로그인
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object>/* Manager_MemberDto */ loginMember(Map<String, Object> map/* Map<String, Object> map */);

	/**
	 * 담당자 및 배송원 전체 정보조회
	 * 
	 * @return
	 */
	public Map<String, Object>/* List<Manager_MemberDto> */ selectAllDelivery(Map<String, Object> map);

	/**
	 * 상세 정보조회
	 * 
	 * @param email
	 * @return
	 */
	public Map<String, Object>/* Manager_MemberDto */ selectDetail(Map<String, Object> map /* String email */);

	/**
	 * 배송 정보조회
	 * 
	 * @param email
	 * @return
	 */
	public Map<String, Object>/* Manager_MemberDto */ deliveryInfo(Map<String, Object> map/* String email */);

	/**
	 * 임시권한 담당자 및 배송원 정보조회
	 * 
	 * @return
	 */
	public Map<String, Object>/* List<Manager_MemberDto> */ selectTempDelivery(Map<String, Object> map);

	/**
	 * 담당자 및 배송원 임시권한 변경
	 * 
	 * @param dto
	 * @return
	 */
	public Map<String, Object>/* boolean */ modifyAuth(Map<String, Object> map/* String email */);

	/**
	 * 담당자 및 배송원의 아이디를 통해 정보 조회
	 * 
	 * @param email
	 * @return
	 */
	public Map<String, Object>/* Manager_MemberDto */ selectIdDelivery(Map<String, Object> map/* String email */);

	/**
	 * 보관함 전체조회 보관함 ID, 보관함 지하철역으로 검색하여 리스트 출력 (보관함 ID, 이름, 지하철역, 좌표, 좌표)
	 * 
	 * @return
	 */
	public Map<String, Object>/* List<Manager_StorageDto> */ selectAllStorage(Map<String, Object> map);

	/**
	 * 보관함 ID 조회 보관함 ID로 검색하여 리스트 출력 (보관함 ID, 이름, 지하철역, 좌표, 좌표)
	 * 
	 * @param storage_id
	 * @return
	 */
	public Map<String, Object>/* Manager_StorageDto */ selectIdStorage(Map<String, Object> map/* String storage_id */);

	/**
	 * 보관함 지하철역으로 조회 보관함 지하철역 검색 리스트 출력 (보관함 ID, 이름, 지하철역, 좌표, 좌표)
	 * 
	 * @param subway
	 * @return
	 */
	public Map<String, Object>/* Manager_StorageDto */ selectSubwayStorage(Map<String, Object> map/* String subway */);

	/**
	 * 보관함 상세정보 조회 (보관함 ID, 이름, 지하철역, 좌표, 좌표, 보관함 갯수, 보관함 상태, 담당자)
	 * 
	 * @return
	 */
	public Map<String, Object>/* Manager_StorageDto */ selectDetailStorage(
			Map<String, Object> map/* String storage_id */);

	/**
	 * 보관함 상태조회
	 * 
	 * @return
	 */
	public Map<String, Object>/* List<Manager_StorageDto> */ selectBoxStatus(
			Map<String, Object> map/* String storage_id */);

	/**
	 * 보관함 등록 신규 보관함 등록 (보관함 ID, 이름, 지하철역, 실제주소, 상세주소, LNG, LAT, 담당자)
	 * 
	 * @param dto
	 * @return
	 */
	public Map<String, Object>/* boolean */ registStorage(Map<String, Object> map/* Manager_StorageDto dto */);

	/**
	 * 보관함 수정 변동사항이 있다면 물품 보관함에서 조회 (보관함ID로 검색) 후 선택하여 수정
	 * 
	 * @param dto
	 * @return
	 */
	public Map<String, Object>/* boolean */ modifyStorage(Map<String, Object> map/* Manager_StorageDto dto */);

	/**
	 * 사용불가 보관함이 해결되었을때 관리자가 사용 가능 보관함으로 변경
	 * 
	 * @param seq
	 * @return
	 */
	public Map<String, Object>/* boolean */ activateStorage(Map<String, Object> map/* String seq */);

	// 기수
	/**
	 * 회원 전체 목록 조회
	 * 
	 * @param email
	 * @return list
	 */
	public Map<String, Object>/* List<MemberDto> */ selectAll(Map<String, Object> map/* String email */);

	/**
	 * 회원 아이디로 검색
	 * 
	 * @return list
	 */
	public Map<String, Object>/* List<String> */ memberIdSearch(Map<String, Object> map);

	/**
	 * 회원 상세 정보 조회
	 * 
	 * @param email
	 * @return dto
	 */
	public Map<String, Object>/* MemberDto */ detailMember(Map<String, Object> map/* String email */);

	/**
	 * 회원 사용중인 서비스 조회
	 * 
	 * @param email
	 * @return list
	 */
	public Map<String, Object>/* List<UserGoodsDto> */ memberUsing(Map<String, Object> map/* String email */);

}
