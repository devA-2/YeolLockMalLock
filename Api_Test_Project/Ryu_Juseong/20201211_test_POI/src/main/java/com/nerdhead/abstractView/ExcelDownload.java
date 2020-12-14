package com.nerdhead.abstractView;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.nerdhead.dto.ExcelDto;
import com.nerdhead.util.ApachePOIHelper;

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
			ApachePOIHelper helper=new ApachePOIHelper(dto);
			
			setContentType(helper.getContentType());
			response.setContentType(getContentType());
			
			
			helper.downloadExel(request, response);
			
			
	}

}
