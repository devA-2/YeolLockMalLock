package com.nerdhead.util;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import com.nerdhead.dto.ExcelDto;

public class ApachePOI {
	public Workbook getExcel(ExcelDto dto) {
		return getExcel(
				dto.getVersion(),
				dto.getSheet_name(),
				dto.getFile_name(),
				dto.getKeys(),
				dto.getData_list());
	}
	
	
	public Workbook getExcel(
							String version, 
							String sheet_name, 
							String file_name, 
							String[] keys,
							List<HashMap<String, Object>> data_list
							) {
		
		//Excel 객체 생성
		Workbook workbook = createWorkbook(version);
		//Excel 객체에 넣을 sheet생성
		Sheet sheet = workbook.createSheet(sheet_name);
		
		//Data 넣기
		setDataList(sheet, keys, data_list);
		
		//파일 저장
		return workbook;
	}

	// Workbook 생성
	private Workbook createWorkbook(String version) {
		if ("xls".equals(version)) {
			return new HSSFWorkbook();	// 표준 xls 버젼
		} else {
			return new XSSFWorkbook();	// 확장 xlsx 버젼
		}
	}
	
	private void setDataList(Sheet sheet, String[] keys, List<HashMap<String, Object>> data_list) {
		int row_num=0;
		int cell_num=0;
		Row row;
		Cell cell;
		
		for (String key : keys) {
			row=getRow(sheet, row_num);
			
			//TODO : 작성중
		}
	}
	
	// Sheet로 부터 Row를 취득, 생성하기
		public Row getRow(Sheet sheet, int row_num) {
			Row row = sheet.getRow(row_num);
			if (row == null) {
				row = sheet.createRow(row_num);
			}
			return row;
		}

		// Row로 부터 Cell를 취득, 생성하기
		public Cell getCell(Row row, int cell_num) {
			Cell cell = row.getCell(cell_num);
			if (cell == null) {
				cell = row.createCell(cell_num);
			}
			return cell;
		}
	
	
	
	
	
	//파일 저장
	//TODO : 추후 예외 처리 할 것
	private void writeExcel(Workbook workbook, String filepath) {
		try (FileOutputStream stream = new FileOutputStream(filepath)) {
			workbook.write(stream);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
