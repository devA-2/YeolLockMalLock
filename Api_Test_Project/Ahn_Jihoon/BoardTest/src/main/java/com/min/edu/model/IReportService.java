package com.min.edu.model;

import java.util.List;

import com.min.edu.dto.ReportDto;

public interface IReportService {

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
	
}
