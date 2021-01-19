package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.dev2.ylml.util.ApiServerHelper;


import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.ReportDto;
import com.dev2.ylml.dto.LostPropertyDto;
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
import com.dev2.ylml.model.dao.ReportDao;
import com.dev2.ylml.model.dao.ReportIDao;
import com.dev2.ylml.model.dao.SearchIDao;
import com.dev2.ylml.model.dao.StorageIDao;
import com.dev2.ylml.model.dao.ManagerLoginIDao;
import com.dev2.ylml.model.dao.Manager_MemberIDao;
import com.dev2.ylml.model.dao.Manager_StorageIDao;

@Service
@Slf4j
public class Api_Service implements Api_IService{
  
	@Autowired
	ApiServerHelper helper;
	
	@Autowired
	private Manager_StorageIDao manager_storageService;
  
	@Autowired
	private Manager_MemberIDao manager_memberService;
  
	@Autowired
	private ManagerLoginIDao managerLoginService;
  
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

	
	//Certification -> 데이터의 0번째는 key 값 -> 틀리면 Certification:false로 return  한다
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSampleData(Map<String, Object> map){
		//무조건 if(checkKey) return문을 추가해줘야함
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, String> reciveData=(Map<String, String>) helper.getData(map);
		
		Map<String, String> sendData = reciveData;
		System.out.println(sendData.get("id")+" / "+ map.get("pw"));
		
		
		//dao에서 받아온 데이터는 helper.generateData()에 담아서 전달함
		return helper.generateData(sendData);
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
		MemberDto dto = memberDao.login(res);
		
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
		int isc = memberDao.usingCheck(email);
		
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

	@SuppressWarnings("unchecked")
	@Override
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
		boolean isc =  isc1 && isc2 && isc3 && isc4;
		return helper.generateData(isc);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> updateAllStatus(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		List<String> list = (List<String>) helper.getData(map);
		int result = storageDao.updateAllStatus(list);
		return helper.generateData(result);
	}
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
		String code = storageDao.compareKey(key);
		return helper.generateData(code);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> updateExtraCost(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> cost = (Map<String, Object>) helper.getData(map);
		boolean isc = storageDao.updateExtendCost(cost);
		return helper.generateData(isc);
	}
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
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> updateOutUser(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> box = (Map<String, Object>) helper.getData(map);
		boolean isc = storageDao.updateOutUser(box);
		return helper.generateData(isc);
	}
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
		boolean isc =  isc1 && isc2 && isc3 && isc4;
		return helper.generateData(isc);
	}

	// TODO : 체크해야 하는 부분
	@Override
	public Map<String, Object> selectUserStorageList(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		return null;
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
	
	@Override
	public Map<String, Object> insertDelivery(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		DeliveryDto delDto = (DeliveryDto) helper.getData(map);
		StorageGoodsDto goodsDto = (StorageGoodsDto) helper.getData(map);
		boolean isc1 = StorageDeliveryDao.insertDelivery(delDto);
		goodsDto.setDeliveryCode(delDto.getDeliveryCode());
		if(goodsDto.getCategoryCode().equals("R")) {
			goodsDto.setCategoryCode("RD");
		}else {
			goodsDto.setCategoryCode("D");
		}
		boolean isc2 = StorageDeliveryDao.updateDeliveryCode(goodsDto);
		boolean isc3 = StorageDeliveryDao.updateDeliveryCost(goodsDto);
		return helper.generateData((isc1 || isc2 || isc3)? true:false);
	}

	// TODO : 체크해야 하는 부분
	@Override
	public Map<String, Object> selectUserDeliveryList(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		return null;
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
		return helper.generateData(reportDao.insertReport(dto));
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

	// -----------------------------------------------------------------

	
	/************************************관리자************************************/
	/************************************재우************************************/
	/*사용 예제*//*service.insertMember -> insertMember.do 와 매핑*/
	
	/* if문은 무조건 있어야함 */
	//if(!helper.checkKey(map)) {
	//	return helper.keyFailed();
	//}
	/* 데이터는 helper.getData로 웹에서 받은 데이터를 가져옴 */
	//MemberDto dto = (MemberDto)helper.getData(map);
	/* 전달할 값은 그냥 편하게 작성*/
	//boolean isc=memberDao.insertMember(dto);
	
	
	/* 전달할 값을 helper.generateData(-----)에 넣어서 return 하면 됨 */
	//return helper.generateData(isc);
	
	@SuppressWarnings("unchecked")
	// 관리자 로그인
	@Override
	public Map<String, Object> loginMember(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> login = (Map<String, Object>)helper.getData(map);
		Manager_MemberDto dto = managerLoginService.loginMember(login);
		return helper.generateData(dto);
	}

	// 담당자 및 배송원 전체 정보조회
	@Override
	public Map<String, Object> selectAllDelivery(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		List<Manager_MemberDto> dto = manager_memberService.selectallDelivery();
		return helper.generateData(dto);
	}

	// 담당자 및 배송원 상세 정보조회 
	@Override
	public Map<String, Object> selectDetail(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email = (String)helper.getData(map);
		Manager_MemberDto dto = manager_memberService.selectDetail(email);
		return helper.generateData(dto);
	}

	// 담당자 및 배송원 배송 정보조회 
	@Override
	public Map<String, Object> deliveryInfo(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email = (String)helper.getData(map);
		Manager_MemberDto dto = manager_memberService.DeliveryInfo(email);
		return helper.generateData(dto);
	}

	// 임시권한(89,99)을 가진 담당지 및 배송원 조회
	@Override
	public Map<String, Object> selectTempDelivery(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		List<Manager_MemberDto> dto = manager_memberService.selectTempDelivery();
		return helper.generateData(dto);
	}

	// 담당자 및 배송원 임시권한 변경
	@Override
	public Map<String, Object> modifyAuth(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email = (String)helper.getData(map);
		boolean isc = manager_memberService.modifyAuth(email);
		return helper.generateData(isc);
	}

	// 담당자 및 배송원의 아이디를 통해 정보 조회
	@Override
	public Map<String, Object> selectIdDelivery(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email = (String)helper.getData(map);
		Manager_MemberDto lists = manager_memberService.selectIdDelivery(email);
		return helper.generateData(lists);
	}

	// 보관함 전체 조회
	@Override
	public Map<String, Object> selectAllStorage(Map<String, Object> map) {
		List<Manager_StorageDto> lists = manager_storageService.selectAllStorage();
		return helper.generateData(lists);
	}

	// 보관함 ID로 조회
	@Override
	public Map<String, Object> selectIdStorage(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String storage_id = (String)helper.getData(map);
		Manager_StorageDto dto = manager_storageService.selectIdStorage(storage_id);
		return helper.generateData(dto);
	}

	// 보관함 지하철역으로 조회
	@Override
	public Map<String, Object> selectSubwayStorage(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String subway = (String)helper.getData(map);
		Manager_StorageDto dto = manager_storageService.selectSubwayStorage(subway);
		return helper.generateData(dto);
	}

	// 보관함 상세정보 조회
	@Override
	public Map<String, Object> selectDetailStorage(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String storage_id = (String)helper.getData(map);
		Manager_StorageDto dto = manager_storageService.selectDetailStorage(storage_id);
		return helper.generateData(dto);
	}

//	보관함 상태 조회
	@Override
	public Map<String, Object> selectBoxStatus(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String storage_id = (String)helper.getData(map);
		List<Manager_StorageDto> lists = manager_storageService.selectBoxStatus(storage_id);
		return helper.generateData(lists);
	}

	// 보관함 등록
	@Override
	public Map<String, Object> registStorage(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Manager_StorageDto dto = (Manager_StorageDto)helper.getData(map);
		boolean isc = manager_storageService.registStorage(dto);
		return helper.generateData(isc);
	}

	// 보관함 수정
	@Override
	public Map<String, Object> modifyStorage(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Manager_StorageDto dto = (Manager_StorageDto)helper.getData(map);
		boolean isc = manager_storageService.modifyStorage(dto);
		return helper.generateData(isc);
	}

	// 사용불가 보관함이 해결되었을때 관리자가 사용 가능 보관함으로 변경
	@Override
	public Map<String, Object> activateStorage(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String seq = (String)helper.getData(map);
		boolean isc = manager_storageService.ActivateStorage(seq);
		return helper.generateData(isc);
	}

	
	@Override
	public Map<String, Object> selectAll(Map<String, Object> map) {
		//파라미터 없어서 냅둠 // 기수
		return null;
	}

	@Override
	public Map<String, Object> memberIdSearch(Map<String, Object> map) {
		//파라미터 없어서 냅둠 // 기수
		return null;
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

	// TODO : list는 어떻게 해야하는지 몰라 냅둠
	@Override
	public Map<String, Object> memberUsing(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String email =  (String) helper.getData(map);
		List<StorageGoodsDto> dto = memberDao.memberUsing(email);
		
		return helper.generateData(dto);
	}

	
	
}
