package com.nerdhead.abstractView;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.AbstractView;

import com.nerdhead.dto.ExcelDto;

public class ExcelDownload extends AbstractView {
	public ExcelDownload() {
	}

	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			ExcelDto dto = (ExcelDto)model.get("excelDto");
			//나중에 @Autowired 처리 할것
			
			
	}

}
