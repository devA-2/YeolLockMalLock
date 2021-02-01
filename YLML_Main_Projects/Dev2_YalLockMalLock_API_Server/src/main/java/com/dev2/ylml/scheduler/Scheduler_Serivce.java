package com.dev2.ylml.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dev2.ylml.dto.StorageGoodsDto;
import com.dev2.ylml.model.dao.LostPropertyIDao;
import com.dev2.ylml.model.dao.StorageIDao;

@Component
public class Scheduler_Serivce implements Scheduler_IService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StorageIDao storageDao;

	@Autowired
	private LostPropertyIDao lostPropertyDao;
	
	@Scheduled(cron = "59 59 23 * * *")
	@Override
	public void schedulerStorage() {
		List<StorageGoodsDto> list = lostPropertyDao.selectInsertLostPropertyList();
		
		lostPropertyDao.insertLostProperty(list);
		log.info("보관물품->유실물으로 등록");
		
		storageDao.updateAllStatus(list); // 보관함 사용 가능 여부 상태변경
		log.info("사용중/대기 보관함 사용가능/불가 처리");
		
		storageDao.deleteAllGoods();
		log.info("보관물품 전체삭제");
//		System.out.println("------------ 스케쥴링 테스트 ------------");
	}

//	@Scheduled(fixedRate = 1000)
//	@Override
//	public void schedulerMember() {
//		System.out.println("------------ 스케쥴링 테스트 ------------");
//	}

	
	
}
