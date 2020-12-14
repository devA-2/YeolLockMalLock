package com.nerdhead.dto;

import java.util.HashMap;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ExcelDto {
	private String version;
	private String file_name;
	private String sheet_name = "Sheet1";
	private String[] keys;
	private List<HashMap<String, Object>> data_list;
	
	public void setVersion_XLS() {
		version="xls";
	}
	public void setVersion_XLSX() {
		version="xlsx";
	}
}
