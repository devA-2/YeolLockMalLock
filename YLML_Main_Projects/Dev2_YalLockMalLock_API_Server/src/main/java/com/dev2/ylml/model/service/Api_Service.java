package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageGoodsDto;
import com.dev2.ylml.dto.StorageListDto;
import com.dev2.ylml.model.dao.StorageDao;
import com.dev2.ylml.model.dao.StorageIDao;
import com.dev2.ylml.util.ApiServerHelper;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class Api_Service implements Api_IService{
	@Autowired
	ApiServerHelper helper;
	
	@Autowired
	StorageIDao dao;
	
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
		//if(!helper.checkKey(map)) {
		//	return helper.keyFailed();
		//}
		/* 데이터는 helper.getData로 웹에서 받은 데이터를 가져옴 */
		//MemberDto dto = (MemberDto)helper.getData(map);
		/* 전달할 값은 그냥 편하게 작성*/
		
		
		//boolean isc=memberDao.insertMember(dto);
		
		
		/* 전달할 값을 helper.generateData(-----)에 넣어서 return 하면 됨 */
		//return helper.generateData(isc);
		
		
		
		return null;
	}

	@Override
	public Map<String, Object> idCheck(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> phoneCheck(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> login(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> apiLogin(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> authUpdate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> idSearch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> pwSearch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> updateInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> updatePw(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> usingCheck(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> quitMember(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectMap(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		List<Map<String, Object>> list = dao.selectMap();
		return helper.generateData(list);
		
	}

	@Override
	public Map<String, Object> selectStorageList(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}		
		List<Map<String, String>> list = dao.selectStorageList();
		return helper.generateData(list);
	}

	@Override
	public Map<String, Object> ajaxCountStorage(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String id = (String) helper.getData(map);
		StorageListDto dto =dao.ajaxCountStorage(id);
		return helper.generateData(dto);
	}

	@Override
	public Map<String, Object> selectStorageStatus(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		String id = (String) helper.getData(map);
		List<StorageBoxDto> list =dao.selectStorageStatus(id);
		return helper.generateData(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> insertGoods(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> box = (Map<String, Object>) helper.getData(map);
		boolean isc1 = dao.insertGoods(box);
		log.info("보관 등록 : "+isc1+" - map :"+ box);
		boolean isc2 = dao.updateStatus(box);
		log.info("보관함 사용중 처리 : "+isc2+" - map :"+ box);
		boolean isc3 = dao.insertCost(box);
		log.info("결제코드 생성 : "+isc3+" - map :"+ box);
		boolean isc4 = dao.updateCostCode(box);
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
		int result = dao.updateAllStatus(list);
		return helper.generateData(result);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> updateExtend(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> box = (Map<String, Object>) helper.getData(map);
		boolean isc1 = dao.updateExtend(box);
		log.info("연장 시간, 횟수 수정 결과 : " +isc1);
		boolean isc2 = dao.updateExtendCost(box);
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
		String code = dao.compareKey(key);
		return helper.generateData(code);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> updateExtraCost(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> cost = (Map<String, Object>) helper.getData(map);
		boolean isc = dao.updateExtendCost(cost);
		return helper.generateData(isc);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> afterPayment(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, String> box = (Map<String, String>) helper.getData(map);
		boolean isc1 = dao.updateStatusCheck(box);
		log.info("결제코드로 해당 보관함 사용가능/사용불가 처리후 결과 : "+isc1);
		boolean isc2 = dao.deleteGoods(box.get("costCode"));
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
		String chkEmail = dao.checkOutEmail(email);
		return helper.generateData(chkEmail);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> updateOutUser(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, Object> box = (Map<String, Object>) helper.getData(map);
		boolean isc = dao.updateOutUser(box);
		return helper.generateData(isc);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> insertReturn(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String,String> box = (Map<String, String>) helper.getData(map);
		StorageGoodsDto goodsDto = dao.selectForReturn(box.get("costCode"));
		boolean isc1 = dao.deleteGoods(box.get("costCode"));
		log.info("결제 완료된 물품 정보 삭제 결과 : "+ isc1);
		goodsDto.setMessage(box.get("message"));
		log.info("반품 하기 위해 받아온 Dto : "+ goodsDto);
		boolean isc2 = dao.insertReturn(goodsDto);
		log.info("반품 등록 결과 : "+isc2);
		Map<String,Object> box2 = new HashMap<String, Object>();
		box2.put("id", goodsDto.getStorageId());
		box2.put("boxSeq", goodsDto.getBoxSeq());
		boolean isc3 = dao.insertCost(box2);
		log.info("반품 결제코드 생성 : "+isc3);
		boolean isc4 = dao.updateCostCode(box2);
		log.info("반품 결제코드 수정 : "+isc4);
		boolean isc =  isc1 && isc2 && isc3 && isc4;
		return helper.generateData(isc);
	}

	@Override
	public Map<String, Object> selectUserStorageList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectStorageBoxList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectTimeTableSeq(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectDeliveryMan(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectDeliveryLoc(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectDeliveryQty(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectDeliveryTime(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> insertDelivery(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectUserDeliveryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectAllLostProperty(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectOneLostProperty(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> insertReport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> replyReport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> reply(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> modifyReport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectAllReport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectDetailReport(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> updateProcessStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectReportDetail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> searchId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> searchId2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> insertLostProperty(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> loginMember(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectAllDelivery(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectDetail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> deliveryInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectTempDelivery(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> modifyAuth(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectIdDelivery(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectAllStorage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectIdStorage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectSubwayStorage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectDetailStorage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectBoxStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> registStorage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> modifyStorage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> activateStorage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> memberIdSearch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> detailMember(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> memberUsing(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
