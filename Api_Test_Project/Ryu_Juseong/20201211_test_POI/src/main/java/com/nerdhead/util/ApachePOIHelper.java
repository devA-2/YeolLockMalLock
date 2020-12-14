package com.nerdhead.util;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.nerdhead.dto.ExcelDto;

public class ApachePOIHelper {
	// 굳이 DTO로 뺄 이유가 없어서 냅둠
	/*
	 * // DTO로 세팅 파일 던지는것도 고려 할 것 private String version; private String file_name;
	 * private String sheet_name = "Sheet1"; private String[] keys; private
	 * List<HashMap<String, Object>> data_list;
	 */

	private ExcelDto dto;

	public ApachePOIHelper() {
		// 버전 설정 -> 디폴트 = xls
		// 파일 이름 설정
		// 저장 경로 설정
		// 시트 이름 설정 -> 디폴트 = Sheet1
		// 데이터 -> String[] keys, List<Hashmap<String, Object>>
		// Object에는 String, Integer, Calendar 등이 가능함
	}

	public void setExcelDto(ExcelDto dto) {
		if (checkDto(dto)) {
			this.dto = dto;
		} else {
			// TODO : 일단 귀찬아서 아무 예외 처리로 던지게 함 -> 추후 적당한 예외 처리 Class 만들 것
			throw new NoClassDefFoundError();
		}
	}

	private boolean checkDto(ExcelDto dto) {
		return ("xls".equals(dto.getVersion()) || "xlsx".equals(dto.getVersion())) 
				&& dto.getFile_name() != null
				&& dto.getKeys() != null 
				&& dto.getData_list() != null;
	}

	public Workbook getExcel() {
		return new ApachePOI().getExcel(dto);
	}

	public String getFileName() {
		return dto.getFile_name() + "." + dto.getVersion();
	}
}
