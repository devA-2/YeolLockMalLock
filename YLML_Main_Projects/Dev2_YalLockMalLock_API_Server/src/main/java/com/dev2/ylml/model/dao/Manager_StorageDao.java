package com.dev2.ylml.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev2.ylml.dto.Manager_StorageDto;

@Repository
public class Manager_StorageDao implements Manager_StorageIDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final String NS = "com.dev2.ylml.model.dao.Manager_StorageIDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 보관함 전체조회
	@Override
	public List<Manager_StorageDto> selectAllStorage() {
		logger.info("API서버 ManagerStorage_Daoimpl + selectAllStorage 실행");
		return sqlSession.selectList(NS+"selectAllStorage");
	}
	
	// 보관함 ID 조회
	// 보관함 ID로 검색하여 리스트 출력
	// (보관함 ID, 이름, 지하철역, 좌표, 좌표)
	@Override
	public Manager_StorageDto selectIdStorage(String storage_id) {
		logger.info("API서버 ManagerStorage_Daoimpl + "
				+ "selectIdStorage 실행 + 입력값 : "+storage_id);
		return sqlSession.selectOne(NS+"selectIdStorage", storage_id);
	}
	
	// 보관함 지하철역 조회
	// 보관함 지하철역으로 검색하여 리스트 출력
	// (보관함 ID, 이름, 지하철역, 좌표, 좌표)
	@Override
	public List<Manager_StorageDto> selectSubwayStorage(String subway) {
		logger.info("API서버 ManagerStorage_Daoimpl + "
				+ "selectSubwayStorage 실행 + 입력값 : "+subway);
		return sqlSession.selectList(NS+"selectSubwayStorage", subway);
	}

	//	보관함 상세정보 조회
	//	(보관함 ID, 이름, 지하철역, 좌표, 좌표, 보관함 갯수, 보관함 상태, 담당자)
	@Override
	public Manager_StorageDto selectDetailStorage(String storage_id) {
		logger.info("API서버 ManagerStorage_Daoimpl + "
				+ "selectDetailStorage 실행 + 입력값 : "+storage_id);
		return  sqlSession.selectOne(NS+"selectDetailStorage", storage_id);
	}
	
	//	보관함 상태조회
	@Override
	public List<Manager_StorageDto> selectBoxStatus(String storage_id) {
		logger.info("API서버 ManagerStorage_Daoimpl + selectBoxStatus 실행");
		return sqlSession.selectList(NS+"selectBoxStatus",storage_id);
	}

	//  보관함 등록 
	//	신규 보관함 등록(보관함 ID, 이름, 지하철역, 
	//	실제주소, 상세주소, LNG, LAT, 담당자)
	@Override
	public boolean registStorage(Manager_StorageDto dto) {
		logger.info("API서버 ManagerStorage_Daoimpl + registStorage 실행");
		int isc = sqlSession.insert(NS+"registStorage", dto);
		return isc>0?true:false;
	}

	//	보관함 수정
	//	변동사항이 있다면 물품 보관함에서 조회
	//	(보관함ID로 검색)후 선택하여 수정
	@Override
	public boolean modifyStorage(Manager_StorageDto dto) {
		logger.info("API서버 ManagerStorage_Daoimpl + modifyStorage 실행");
		int isc = sqlSession.update(NS+"modifyStorage", dto);
		return isc>0?true:false;
	}

	//	사용불가 보관함이 해결되었을때 관리자가 사용 가능 보관함으로 변경
	@Override
	public boolean activateStorage(Manager_StorageDto dto) {
		logger.info("API서버 ManagerStorage_Daoimpl + activateStorage 실행");
		int isc = sqlSession.update(NS+"activateStorage", dto);
		return isc>0?true:false;
	}

	

	

	

}
