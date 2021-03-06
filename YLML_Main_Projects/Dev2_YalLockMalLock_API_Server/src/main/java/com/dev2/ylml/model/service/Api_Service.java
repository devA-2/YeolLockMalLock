package com.dev2.ylml.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import com.dev2.ylml.util.ApiServerHelper;
import com.dev2.ylml.util.PagingVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.ReportDto;
import com.dev2.ylml.dto.LostPropertyDto;
import com.dev2.ylml.dto.CostDto;
import com.dev2.ylml.dto.DeliveryDto;
import com.dev2.ylml.dto.StorageGoodsDto;
import com.dev2.ylml.dto.StorageListDto;
import com.dev2.ylml.dto.UserStorageListDto;
import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.Manager_MemberDto;
import com.dev2.ylml.dto.Manager_StorageDto;

import com.dev2.ylml.model.dao.StorageDeliveryIDao;
import com.dev2.ylml.model.dao.LostPropertyIDao;
import com.dev2.ylml.model.dao.MemberIDao;
import com.dev2.ylml.model.dao.RFIDIDao;
import com.dev2.ylml.model.dao.ReportIDao;
import com.dev2.ylml.model.dao.SearchIDao;
import com.dev2.ylml.model.dao.StorageIDao;
import com.dev2.ylml.model.dao.Manager_MemberIDao;
import com.dev2.ylml.model.dao.Manager_StorageIDao;

@Service
@Slf4j
public class Api_Service implements Api_IService{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ApiServerHelper helper;
	
	@Autowired
	private Manager_StorageIDao manager_storageDao;
  
	@Autowired
	private Manager_MemberIDao manager_memberDao;
  
  
	@Autowired
	private StorageIDao storageDao;

	@Autowired
	private LostPropertyIDao lostPropertyDao;
	
	@Autowired
	private ReportIDao reportDao;
	
	@Autowired
	private SearchIDao searchDao;

	@Autowired
	private StorageDeliveryIDao StorageDeliveryDao;
  
	@Autowired
	private MemberIDao memberDao;
	
	@Autowired
	private RFIDIDao rfidDao;
	
	//Certification -> 데이터의 0번째는 key 값 -> 틀리면 Certification:false로 return  한다
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSampleData(Map<String, Object> map){
		//무조건 if(checkKey) return문을 추가해줘야함
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, String> reciveData=(Map<String, String>) helper.getData(map);
		
		Map<String, String> sendData = reciveData;
		//logger.trace(sendData.get("id")+" / "+ sendData.get("pw"));
		
		
		//dao에서 받아온 데이터는 helper.generateData()에 담아서 전달함
		return helper.generateData(sendData);
	}
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSampleData2(Map<String, Object> map){
		//무조건 if(checkKey) return문을 추가해줘야함
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		
		
		//dao에서 받아온 데이터는 helper.generateData()에 담아서 전달함
		return helper.generateData();
	}

	@Override
	public Map<String, Object> insertMember(Map<String, Object> map) {
		/*사용 예제*//*service.insertMember -> insertMember.do 와 매핑*/
		
		/* if문은 무조건 있어야함 */
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		/* 데이터는 helper.getData로 웹에서 받은 데이터를 가져옴 */
		MemberDto dto = (MemberDto)helper.getData(map);
		/* 전달할 값은 그냥 편하게 작성*/

		boolean isc=memberDao.insertMember(dto);

		
		
		/* 전달할 값을 helper.generateData(-----)에 넣어서 return 하면 됨 */
		return helper.generateData(isc);
		
//		return null;
	}

	@Override
	public Map<String, Object> idCheck(Map<String, Object> map) {
		System.out.println("idCheck -> 맴버디티오 : "+map);
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email = (String)helper.getData(map);
		int isc= memberDao.idCheck(email);
		
		return helper.generateData(isc);
	}

	@Override
	public Map<String, Object> phoneCheck(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String phoneNum = (String)helper.getData(map);
		int isc= memberDao.phoneCheck(phoneNum);
		
		return helper.generateData(isc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> login(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> res = (Map<String, Object>) helper.getData(map);
			MemberDto dto = (memberDao.enPw(res))?memberDao.login(res):null;
			return helper.generateData(dto);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> delLogin(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> res = (Map<String, Object>) helper.getData(map);
			MemberDto dto = (memberDao.enPw(res))?memberDao.delLogin(res):null;
			return helper.generateData(dto);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> adminLogin(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> res = (Map<String, Object>) helper.getData(map);
			MemberDto dto = (memberDao.enPw(res))?memberDao.adminLogin(res):null;
			return helper.generateData(dto);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> apiLogin(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> res = (Map<String, Object>) helper.getData(map);
		MemberDto dto = memberDao.apiLogin(res);
		
		return helper.generateData(dto);
	}

	@Override
	public Map<String, Object> authUpdate(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		MemberDto dto = (MemberDto)helper.getData(map);
		boolean isc=memberDao.authUpdate(dto);
		return helper.generateData(isc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> idSearch(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> res = (Map<String, Object>) helper.getData(map);
		String isc = memberDao.idSearch(res);
		
		return helper.generateData(isc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> pwSearch(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, String> res = (Map<String, String>) helper.getData(map);
		int isc=memberDao.pwSearch(res);
		return helper.generateData(isc);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> updateInfo(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> res = (Map<String, Object>) helper.getData(map);
		int isc=memberDao.updateInfo(res);
		return helper.generateData(isc);
	}

	@Override
	public Map<String, Object> updatePw(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		MemberDto dto = (MemberDto)helper.getData(map);
		int isc=memberDao.updatePw(dto);
		return helper.generateData(isc);
	}

	@Override
	public Map<String, Object> usingCheck(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email = (String) helper.getData(map);
		int isc = memberDao.usingCheck(email);
		
		return helper.generateData(isc);
	}

	@Override
	public Map<String, Object> quitMember(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email = (String) helper.getData(map);
		int isc = memberDao.quitMember(email);
		
		return helper.generateData(isc);
	}

	@Override
	public Map<String, Object> selectMap(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		List<Map<String, Object>> list = storageDao.selectMap();
		return helper.generateData(list);
		
	}

	@Override
	public Map<String, Object> selectStorageList(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}		
		List<Map<String, String>> list = storageDao.selectStorageList();
		return helper.generateData(list);
	}

	@Override
	public Map<String, Object> ajaxCountStorage(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String id = (String) helper.getData(map);
		StorageListDto dto =storageDao.ajaxCountStorage(id);
		return helper.generateData(dto);
	}

	@Override
	public Map<String, Object> selectStorageStatus(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String id = (String) helper.getData(map);
		List<StorageBoxDto> list =storageDao.selectStorageStatus(id);
		return helper.generateData(list);
	}
	@Override
	public Map<String, Object> tagNFC(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		MemberDto mem = (MemberDto) helper.getData(map);
		int cnt =storageDao.tagNFC(mem);
		return helper.generateData(cnt);
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Map<String, Object> insertGoods(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> box = (Map<String, Object>) helper.getData(map);
		boolean isc1 = storageDao.insertGoods(box);
		log.info("보관 등록 : "+isc1+" - map :"+ box);
		boolean isc2 = storageDao.updateStatus(box);
		log.info("보관함 사용중 처리 : "+isc2+" - map :"+ box);
		boolean isc3 = storageDao.insertCost(box);
		log.info("결제코드 생성 : "+isc3+" - map :"+ box);
		boolean isc4 = storageDao.updateCostCode(box);
		log.info("결제코드 수정 : "+isc4+" - map :"+ box);
		boolean isc5 = rfidDao.insertKey(box);
		log.info("키 생성하여 입력 : "+isc5+" - map :"+ box);
		boolean isc =  isc1 && isc2 && isc3 && isc4 && isc5;
		return helper.generateData(isc);
	}
	
	@Transactional
	@Override
	public Map<String, Object> scheduledForMidnight(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
//		List<String> list = (List<String>) helper.getData(map); // storage_id 담겨있음
		List<StorageGoodsDto> list = lostPropertyDao.selectInsertLostPropertyList();
//		List<StorageGoodsDto> list = (List<StorageGoodsDto>) helper.getData(map);
		log.info(list.size()+"");
		int cnt1 = lostPropertyDao.insertLostProperty(list);
		log.info("보관물품->유실물으로 등록 갯수 : "+cnt1);
		int cnt2 = storageDao.updateAllStatus(list); // 보관함 사용 가능 여부 상태변경
		log.info("사용중/대기 보관함 사용가능/불가 처리 갯수 : "+ cnt2);
		int cnt3 = storageDao.deleteAllGoods();
//		int cnt3=0;
		log.info("보관물품 전체삭제 갯수 : "+cnt3);
		int cnt = cnt1 + cnt2+cnt3;
		
		
		
		
		// ------------------------------------
		
//		for (int i = 0; i < list.size(); i++) {
//			lostPropertyDao.selectInsertLostPropertyList(list);
//		}
//		Calendar calendar = new GregorianCalendar();
		
//		// Date 입력할거 예제
//		String testDate = "2021-01-27 00:00:00";
//		SimpleDateFormat test = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			Date transFormat = test.parse(testDate);
//			System.out.println(transFormat);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		MemberDto mDto = (MemberDto) helper.getData(map);
//		LostPropertyDto lDto = (LostPropertyDto)helper.getData(map);
		// 보류
//		String email = mDto.getEmail();
//		String cost_code = (String) map.get("cost_code");
//		String lost_keep_location = "";
		// 유실물 입력시 dto에 담겨있어야 함, 전달받아야 할 것들 : email, cost_code, lost_regdate(유실물 접수 일자, 직접입력),
		// lost_keep_location, lost_status, and_date(등록일자 + 3일), disposal_date(and_date와 같음)
//		lostPropertyDao.insertLostProperty(lDto);
		return helper.generateData(cnt);
	}
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> updateExtend(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> box = (Map<String, Object>) helper.getData(map);
		boolean isc1 = storageDao.updateExtend(box);
		log.info("연장 시간, 횟수 수정 결과 : " +isc1);
		boolean isc2 = storageDao.updateExtendCost(box);
		log.info("연장 금액 수정 결과 : " +isc2);
		boolean isc =  isc1 && isc2;
		return helper.generateData(isc);
	}

	@Override
	public Map<String, Object> compareKey(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String key = (String) helper.getData(map);
		CostDto costDto = storageDao.compareKey(key);
		return helper.generateData(costDto);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> updateExtraCost(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> box = (Map<String, Object>) helper.getData(map);
		boolean isc = storageDao.updateExtraCost(box);
		return helper.generateData(isc);
	}
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> afterPayment(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, String> box = (Map<String, String>) helper.getData(map);
		boolean isc1 = storageDao.updateStatusCheck(box);
		log.info("결제코드로 해당 보관함 사용가능/사용불가 처리후 결과 : "+isc1);
		boolean isc2 = storageDao.deleteGoods(box.get("costCode"));
		log.info("결제 완료된 물품 정보 삭제 결과 : "+isc2);
		boolean isc = isc1 && isc2;
		return helper.generateData(isc);
	}

	@Override
	public Map<String, Object> checkOutEmail(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email = (String) helper.getData(map);
		String chkEmail = storageDao.checkOutEmail(email);
		return helper.generateData(chkEmail);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> updateOutUser(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> box = (Map<String, Object>) helper.getData(map);

		boolean isc1 = storageDao.updateOutUser(box);
		log.info("수령사용자 update :" + isc1);
		boolean isc2 = rfidDao.updateKey(box);
		log.info("수령사용자 key update :"+isc2);
		return helper.generateData(isc1&&isc2);
	}
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> insertReturn(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String,String> box = (Map<String, String>) helper.getData(map);
		StorageGoodsDto goodsDto = storageDao.selectForReturn(box.get("costCode"));
		boolean isc1 = storageDao.deleteGoods(box.get("costCode"));
		log.info("결제 완료된 물품 정보 삭제 결과 : "+ isc1);
		goodsDto.setMessage(box.get("message"));
		log.info("반품 하기 위해 받아온 Dto : "+ goodsDto);
		boolean isc2 = storageDao.insertReturn(goodsDto);
		log.info("반품 등록 결과 : "+isc2);
		Map<String,Object> box2 = new HashMap<String, Object>();
		box2.put("id", goodsDto.getStorageId());
		box2.put("boxSeq", goodsDto.getBoxSeq());
		boolean isc3 = storageDao.insertCost(box2);
		log.info("반품 결제코드 생성 : "+isc3);
		boolean isc4 = storageDao.updateCostCode(box2);
		log.info("반품 결제코드 수정 : "+isc4);
		boolean isc5 = rfidDao.insertKey(box2);
		log.info("키 생성하여 입력 : "+isc5+" - map :"+ box);
		boolean isc =  isc1 && isc2 && isc3 && isc4 && isc5;
		return helper.generateData(isc);
	}

	@Transactional
	@Override
	public Map<String, Object> selectUserStorageList(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email = (String) helper.getData(map);
		log.info("email? "+email);
		List<UserStorageListDto> list = StorageDeliveryDao.selectUserStorageList(email);
		List<CostDto> cost = StorageDeliveryDao.selectCost(email);
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < cost.size(); j++) {
				if (list.get(i).getCostCode().equals(cost.get(j).getCostCode())) {
					list.get(i).setCost(cost.get(j).getCost());
				}
			}
			int overTime = list.get(i).getOverTime();
			int overH = overTime/60;
			int overM= (overH==0)?0:(overTime%overH);
			int overCost = overH * 1000;
			list.get(i).setOverH(overH);
			list.get(i).setOverM(overM);
			list.get(i).setOverCost(overCost);
		}
		return helper.generateData(list);
	}

	@Override
	public Map<String, Object> selectStorageBoxList(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String storageId = (String) helper.getData(map);
		StorageListDto storageListDto = StorageDeliveryDao.selectStorageBoxList(storageId);
		return helper.generateData(storageListDto);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> selectStorageGoods(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> info = (Map<String, Object>) helper.getData(map);
		StorageGoodsDto storageGoodsDto = StorageDeliveryDao.selectStorageGoods(info);
		return helper.generateData(storageGoodsDto);
	}

	@Override
	public Map<String, Object> selectTimeTableSeq(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String subway = (String) helper.getData(map);
		int seq = StorageDeliveryDao.selectTimeTableSeq(subway);
		return helper.generateData(seq);
	}
	
	@Override
	public Map<String, Object> selectSubwayCnt(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		int cnt = StorageDeliveryDao.selectSubwayCnt();
		return helper.generateData(cnt);
	}

	@Override
	public Map<String, Object> selectDeliveryMan(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		List<MemberDto> deliverymanList = StorageDeliveryDao.selectDeliveryMan();
		return helper.generateData(deliverymanList);
	}

	@Override
	public Map<String, Object> selectDeliveryLoc(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String deliverymanId = (String) helper.getData(map);
		String deliveryLoc = StorageDeliveryDao.selectDeliveryLoc(deliverymanId);
		return helper.generateData(deliveryLoc);
	}

	@Override
	public Map<String, Object> selectDeliveryQty(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String deliverymanId = (String) helper.getData(map);
		int deliveryQty = StorageDeliveryDao.selectDeliveryQty(deliverymanId);
		return helper.generateData(deliveryQty);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> selectDeliveryTime(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Integer> subwaySeqs = (Map<String, Integer>) helper.getData(map);
		int time = StorageDeliveryDao.selectDeliveryTime(subwaySeqs);
		return helper.generateData(time);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> insertDelivery(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> dtos = (Map<String, Object>) helper.getData(map);
		ObjectMapper mapper = new ObjectMapper();
		DeliveryDto deliveryDto = mapper.convertValue(dtos.get("DeliveryDto"), DeliveryDto.class);
		StorageGoodsDto storageGoodsDto = mapper.convertValue(dtos.get("StorageGoodsDto"), StorageGoodsDto.class);
		boolean isc1 = StorageDeliveryDao.insertDelivery(deliveryDto);
		storageGoodsDto.setDeliveryCode(deliveryDto.getDeliveryCode());
		if(storageGoodsDto.getCategoryCode().equals("R")) {
			storageGoodsDto.setCategoryCode("RD");
		}else {
			storageGoodsDto.setCategoryCode("D");
		}
		boolean isc2 = StorageDeliveryDao.updateDeliveryCode(storageGoodsDto);
		boolean isc3 = StorageDeliveryDao.updateDeliveryCost(storageGoodsDto);
		return helper.generateData((isc1 || isc2 || isc3)? true:false);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> selectDeliveryList(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, String> info = (Map<String, String>) helper.getData(map);
		System.out.println("info 확인 " + info);
		String email = info.get("email");
		String auth = info.get("auth");
		List<DeliveryDto> deliveryDto = new ArrayList<DeliveryDto>();
		if(auth.equals("10")) {
			deliveryDto = StorageDeliveryDao.selectUserDeliveryList(email);
		}else if(auth.equals("80")) {
			deliveryDto = StorageDeliveryDao.selectDelmanDeliveryList(email);
		}
		return helper.generateData(deliveryDto);
	}
	
	@Override
	public Map<String, Object> updatedeliveryStrat(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String deliveryCode = (String) helper.getData(map);
		boolean isc = StorageDeliveryDao.updatedeliveryStrat(deliveryCode);
		return helper.generateData(isc);
	}
	
	@Override
	public Map<String, Object> updateCostStatus(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String costCode = (String) helper.getData(map);
		boolean isc = StorageDeliveryDao.updateCostStatus(costCode);
		return helper.generateData(isc);
	}
	
// --------------------------------------------- 유실물 게시판
	@Override
	public Map<String, Object> selectAllLostProperty(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		return helper.generateData(lostPropertyDao.selectAllLostProperty());
	}

	@Override
	public Map<String, Object> selectOneLostProperty(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String seq = (String)helper.getData(map);
		return helper.generateData(lostPropertyDao.selectOneLostProperty(seq));
	}

	// --------------------------------------------- 신고글 게시판
	@Override
	public Map<String, Object> insertReport(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		ReportDto dto = (ReportDto)helper.getData(map);
		boolean isc = reportDao.insertReport(dto);
		return helper.generateData(isc);
	}

	@Override
	public Map<String, Object> replyReport(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		ReportDto dto = (ReportDto)helper.getData(map);
		boolean isc = reportDao.replyReport(dto);
		return helper.generateData(isc?true:false);
	}

	@Override
	public Map<String, Object> reply(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		ReportDto dto = (ReportDto)helper.getData(map);
		return helper.generateData(reportDao.reply(dto));
	}

	@Override
	public Map<String, Object> modifyReport(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		ReportDto dto = (ReportDto)helper.getData(map);
		return helper.generateData(reportDao.modifyReport(dto));
	}

	@Override
	public Map<String, Object> selectAllReport(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		return helper.generateData(reportDao.selectAllReport());
	}

	@Override
	public Map<String, Object> selectDetailReport(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String refer = (String)helper.getData(map);
		return helper.generateData(reportDao.selectDetailReport(refer));
	}

	@Override
	public Map<String, Object> updateProcessStatus(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		ReportDto dto = (ReportDto)helper.getData(map);
		return helper.generateData(reportDao.updateProcessStatus(dto));
	}

	@Override
	public Map<String, Object> selectDetailGoReply(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String seq = (String)helper.getData(map);
		return helper.generateData(reportDao.selectDetailGoReply(seq));
	}

	// --------------------------------------------- 게시판 검색 기능
	@Override
	public Map<String, Object> searchId(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email = (String)helper.getData(map);
		return helper.generateData(searchDao.searchId(email));
	}

	@Override
	public Map<String, Object> searchId2(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String receipt_user_id = (String)helper.getData(map);
		return helper.generateData(searchDao.searchId2(receipt_user_id));
	}

	// ------------------------------------------------------페이징 기능

	@Override
	public Map<String, Object> countReport(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		int cnt = reportDao.countReport();
		return helper.generateData(cnt);
	}
	@Override
	public Map<String, Object> selectReport(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		PagingVO vo = (PagingVO)helper.getData(map);
		List<ReportDto> list = reportDao.selectReport(vo);
		return helper.generateData(list);
	}
	
	/************************************관리자************************************/
	/************************************재우************************************/
	

	// 담당자 및 배송원 전체 정보조회
	@Override
	public Map<String, Object> selectAllDelivery(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		List<Manager_MemberDto> dto = manager_memberDao.selectAllDelivery();
		return helper.generateData(dto);
	}

	// 담당자 및 배송원 상세 정보조회 
	@Override
	public Map<String, Object> selectDetail(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email = (String)helper.getData(map);
		Manager_MemberDto dto = manager_memberDao.selectDetail(email);
		return helper.generateData(dto);
	}

	// 담당자 및 배송원 배송 정보조회 
	@Override
	public Map<String, Object> deliveryInfo(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email = (String)helper.getData(map);
		Manager_MemberDto dto = manager_memberDao.deliveryInfo(email);
		return helper.generateData(dto);
	}

	// 임시권한(89,99)을 가진 담당지 및 배송원 조회
	@Override
	public Map<String, Object> selectTempDelivery(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		List<Manager_MemberDto> dto = manager_memberDao.selectTempDelivery();
		return helper.generateData(dto);
	}

	// 담당자 및 배송원 임시권한 변경
	@Override
	public Map<String, Object> modifyAuth(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email = (String)helper.getData(map);
		boolean isc = manager_memberDao.modifyAuth(email);
		return helper.generateData(isc);
	}

	// 담당자 및 배송원의 아이디를 통해 정보 조회
	@Override
	public Map<String, Object> selectIdDelivery(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email = (String)helper.getData(map);
		Manager_MemberDto lists = manager_memberDao.selectIdDelivery(email);
		return helper.generateData(lists);
	}

	// 보관함 전체 조회
	@Override
	public Map<String, Object> selectAllStorage(Map<String, Object> map) {
		List<Manager_StorageDto> lists = manager_storageDao.selectAllStorage();
		return helper.generateData(lists);
	}

	// 보관함 ID로 조회
	@Override
	public Map<String, Object> selectIdStorage(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String storage_id = (String)helper.getData(map);
		Manager_StorageDto dto = manager_storageDao.selectIdStorage(storage_id);
		return helper.generateData(dto);
	}

	// 보관함 지하철역으로 조회
	@Override
	public Map<String, Object> selectSubwayStorage(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String subway = (String)helper.getData(map);
		List<Manager_StorageDto> lists = manager_storageDao.selectSubwayStorage(subway);
		return helper.generateData(lists);
	}

	// 보관함 상세정보 조회
	@Override
	public Map<String, Object> selectDetailStorage(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String storage_id = (String)helper.getData(map);
		Manager_StorageDto dto = manager_storageDao.selectDetailStorage(storage_id);
		return helper.generateData(dto);
	}

//	보관함 상태 조회
	@Override
	public Map<String, Object> selectBoxStatus(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String storage_id = (String)helper.getData(map);
		List<Manager_StorageDto> lists = manager_storageDao.selectBoxStatus(storage_id);
		return helper.generateData(lists);
	}

	// 보관함 등록
	@Override
	public Map<String, Object> registStorage(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Manager_StorageDto dto = (Manager_StorageDto)helper.getData(map);
		boolean isc = manager_storageDao.registStorage(dto);
		return helper.generateData(isc);
	}

	// 보관함 수정
	@Override
	public Map<String, Object> modifyStorage(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Manager_StorageDto dto = (Manager_StorageDto)helper.getData(map);
		boolean isc = manager_storageDao.modifyStorage(dto);
		return helper.generateData(isc);
	}

	// 사용불가 보관함이 해결되었을때 관리자가 사용 가능 보관함으로 변경
	@Override
	public Map<String, Object> activateStorage(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Manager_StorageDto dto = (Manager_StorageDto)helper.getData(map);
		boolean isc = manager_storageDao.activateStorage(dto);
		return helper.generateData(isc);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> selectAll(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> map2 = (Map<String, Object>) helper.getData(map);
		List<MemberDto> list = memberDao.selectAll(map2);
		return helper.generateData(list);
	}
	@Override
	public Map<String, Object> countMember(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email = (String)helper.getData(map);
		int cnt = memberDao.countMember(email);
		return helper.generateData(cnt);
	}
	@Override
	public Map<String, Object> memberIdSearch(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		List<String> list = memberDao.memberIdSearch();
		return helper.generateData(list);
	}

	@Override
	public Map<String, Object> detailMember(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email =  (String) helper.getData(map);
		MemberDto dto = memberDao.detailMember(email);
		return helper.generateData(dto);
	}

	@Override
	public Map<String, Object> memberUsing(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email =  (String) helper.getData(map);
		List<StorageGoodsDto> dto = memberDao.memberUsing(email);
		return helper.generateData(dto);
	}
	

//	@SuppressWarnings("unchecked")
//	@Override
//	public Map<String, Object> insertLostProperty(Map<String, Object> map) {
//		if(!helper.checkKey(map)) {
//			return helper.keyFailed();
//		}
//		List<StorageGoodsDto> list = (List<StorageGoodsDto>) helper.getData(map);
//		int cnt = lostPropertyDao.insertLostProperty(list);
//		return helper.generateData(cnt);
//	}
//	@Override
//	public Map<String, Object> selectInsertLostPropertyList(Map<String, Object> map) {
//		if(!helper.checkKey(map)) {
//			return helper.keyFailed();
//		}
//		List<StorageGoodsDto> list = lostPropertyDao.selectInsertLostPropertyList();
//		return helper.generateData(list);
//	}

	
//	@Override
//	public Map<String, Object> insertKey(Map<String, Object> map) {
//		if(!helper.checkKey(map)) {
//			return helper.keyFailed();
//		}
//		RFIDDto dto = (RFIDDto) helper.getData(map);
//		return helper.generateData(rfidDao.insertKey(dto));
//	}
	
//	@Override
//	public Map<String, Object> updateKey(Map<String, Object> map) {
//		if(!helper.checkKey(map)) {
//			return helper.keyFailed();
//		}
//		RFIDDto dto = (RFIDDto) helper.getData(map);
//		return helper.generateData(rfidDao.updateKey(dto));
//	}

	
	@Override
	public Map<String, Object> selectAllPagingLostProperty(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		PagingVO vo = (PagingVO)helper.getData(map);
		List<LostPropertyDto> list = lostPropertyDao.selectAllPagingLostProperty(vo);
		return helper.generateData(list);
	}

	
	@Override
	public Map<String, Object> countLostProperty(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		int cnt = lostPropertyDao.countLostProperty();
		return helper.generateData(cnt);
	}
	

}
