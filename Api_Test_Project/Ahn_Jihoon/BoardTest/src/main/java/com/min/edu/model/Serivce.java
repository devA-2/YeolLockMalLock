package com.min.edu.model;

import java.util.List;

import com.min.edu.dto.LostPropertyDto;
import com.min.edu.dto.ReportDto;

public interface Serivce {

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
	
}
