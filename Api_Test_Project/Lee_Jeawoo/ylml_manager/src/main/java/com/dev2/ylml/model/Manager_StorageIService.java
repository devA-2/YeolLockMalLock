package com.dev2.ylml.model;

import java.util.List;

import com.dev2.ylml.dto.Manager_StorageDto;

public interface Manager_StorageIService {
	
	/**	보관함 전체조회
	 * 보관함 ID, 보관함 지하철역으로 검색하여 리스트 출력
	 * (보관함 ID, 이름, 지하철역, 좌표, 좌표)
	 * @return
	 */
	public List<Manager_StorageDto> selectAllStorage();
	
	/**보관함 ID 조회
	 * 보관함 ID로 검색하여 리스트 출력
	 * (보관함 ID, 이름, 지하철역, 좌표, 좌표)
	 * @param storage_id
	 * @return
	 */
	public Manager_StorageDto selectIdStorage(String storage_id);
	
	/**보관함 지하철역으로 조회
	 * 보관함 지하철역 검색 리스트 출력
	 * (보관함 ID, 이름, 지하철역, 좌표, 좌표)
	 * @param subway
	 * @return
	 */
	public Manager_StorageDto selectSubwayStorage(String subway);
	
	/**	보관함 상세정보 조회
	 * (보관함 ID, 이름, 지하철역, 좌표, 좌표, 
	 * 보관함 갯수, 보관함 상태, 담당자)
	 * @return
	 */
	public Manager_StorageDto selectDetailStorage(String storage_id);
	
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
	public boolean ActivateStorage(int seq);
}
