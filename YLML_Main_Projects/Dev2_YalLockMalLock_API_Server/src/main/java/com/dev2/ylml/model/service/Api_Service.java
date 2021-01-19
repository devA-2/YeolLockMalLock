package com.dev2.ylml.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.LostPropertyDto;
import com.dev2.ylml.util.ApiServerHelper;

import src.main.java.com.dev2.ylml.model.dao.LostPropertyDao;
import src.main.java.com.dev2.ylml.model.dao.ReportDao;
import src.main.java.com.dev2.ylml.model.dao.SearchDao;
@Service
public class Api_Service implements Api_IService{
	@Autowired
	ApiServerHelper helper;
	
	LostPropertyDao lostPropertyDao;
	ReportDao reportDao;
	SearchDao searchDao;
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectStorageList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> ajaxCountStorage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectStorageStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> insertGoods(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> updateAllStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> updateExtend(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> compareKey(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> updateExtraCost(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> afterPayment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> checkOutEmail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> updateOutUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> insertReturn(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
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

	
// --------------------------------------------- 유실물 게시판
	@Override
	public Map<String, Object> selectAllLostProperty(Map<String, Object> map) {
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		return helper.generateDate(lostPropertyDao.selectAllLostProperty());
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
		3if(!helper.checkKey(map)) {
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
		return helper.generateData(reportDao.selectDetail(seq));
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
