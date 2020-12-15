package com.min.edu.ctrl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.edu.service.IBoardService;
import com.min.edu.vo.BoardVO;
import com.min.edu.vo.FileVO;

@Controller
public class FileUploadController {

	@Autowired
	public IBoardService service;
	
	@RequestMapping(value = "/boardList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardList(Model model) {
		List<BoardVO> list = service.boardList();
		model.addAttribute("list", list);
		return "boardList";
	}
	
	@RequestMapping(value = "/boardWrite.do", method = RequestMethod.GET)
	public String write() {
		return "boardWrite";
	}
	
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam HashMap<Object, Object> param, MultipartHttpServletRequest mtfRequest) {
		BoardVO bdto = new BoardVO();
		FileVO fdto = new FileVO();
		
		List<MultipartFile> multipartFileList = new ArrayList<>();
		String originFileName = ""; // 파일 이름
		String fileNameExtension; // 파일 확장자 
		String uuidFileName; // 파일 uuid
		String path = mtfRequest.getSession().getServletContext().getRealPath("/upload/");	// 파일 저장 폴더
		System.out.println(path);
		String saveFile; // 경로 + 암호화된 파일 이름
		
		try {
			bdto.setTitle(param.get("title").toString());	// bdto에 title값 저장
			bdto.setContent(param.get("content").toString()); // bdto에 content값 저장
			service.writeBoard(bdto); // content DB에 저장
			MultiValueMap<String, MultipartFile> files = mtfRequest.getMultiFileMap(); //file값 Map으로 받기
			for (Map.Entry<String, List<MultipartFile>> entry : files.entrySet()) { // 메소드 entrySet은 Map의 데이터를 담고 있는 Set을 반환한다. 반환한 Set의 값이 사용할 데이터 탑입은 Map.Entry이다.
				List<MultipartFile> fileList = entry.getValue(); // Map.Entry는 인터페이스로 getKey, getValue API를 지원한다.
				for (MultipartFile mf : fileList) {
					if (mf.isEmpty()) continue;
					multipartFileList.add(mf); // multipartFileList에 fileList값 담기
                }
			}
			if(multipartFileList.size() > 0) {
				for(MultipartFile mf : multipartFileList) {
					originFileName = mf.getOriginalFilename(); // 파일 original명 추출
					fileNameExtension = FilenameUtils.getExtension(originFileName).toLowerCase(); // 파일 확장자 추출
					uuidFileName = UUID.randomUUID().toString(); // 파일명 uuid로 변환
					saveFile = path + uuidFileName + "." + fileNameExtension; // 저장할 파일

					try {
						mf.transferTo(new File(saveFile)); // 업로드 폴더로 파일 전송
						fdto.setImg_uuid(uuidFileName); // FileVo에 값 저장
						fdto.setImg_ori(originFileName);
						fdto.setImg_url(path);
						service.insertFile(fdto); // file db에 저장
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
}
