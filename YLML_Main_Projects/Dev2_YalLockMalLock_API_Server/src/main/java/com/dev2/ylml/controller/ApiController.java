package com.dev2.ylml.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev2.ylml.model.service.Api_IService;

@Controller
public class ApiController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	Api_IService service;
	
	
	@ResponseBody
	@RequestMapping(value = "insertMember.do")
	public Map<String, Object> insertMember(@RequestBody Map<String, Object> map) {
		return service.insertMember(map);
	}
	@ResponseBody
	@RequestMapping(value = "idCheck.do")
	public Map<String, Object> idCheck(@RequestBody Map<String, Object> map) {
		return service.idCheck(map);
	}
	@ResponseBody
	@RequestMapping(value = "phoneCheck.do")
	public Map<String, Object> phoneCheck(@RequestBody Map<String, Object> map) {
		return service.phoneCheck(map);
	}
	@ResponseBody
	@RequestMapping(value = "login.do")
	public Map<String, Object> login(@RequestBody Map<String, Object> map) {
		return service.login(map);
	}
	
	@ResponseBody
	@RequestMapping(value = "delLogin.do")
	public Map<String, Object> delLogin(@RequestBody Map<String, Object> map) {
		return service.delLogin(map);
	}
	
	@ResponseBody
	@RequestMapping(value = "adminLogin.do")
	public Map<String, Object> adminLogin(@RequestBody Map<String, Object> map) {
		return service.adminLogin(map);
	}
	@ResponseBody
	@RequestMapping(value = "apiLogin.do")
	public Map<String, Object> apiLogin(@RequestBody Map<String, Object> map) {
		return service.apiLogin(map);
	}
	@ResponseBody
	@RequestMapping(value = "authUpdate.do")
	public Map<String, Object> authUpdate(@RequestBody Map<String, Object> map) {
		return service.authUpdate(map);
	}
	@ResponseBody
	@RequestMapping(value = "idSearch.do")
	public Map<String, Object> idSearch(@RequestBody Map<String, Object> map) {
		return service.idSearch(map);
	}
	@ResponseBody
	@RequestMapping(value = "pwSearch.do")
	public Map<String, Object> pwSearch(@RequestBody Map<String, Object> map) {
		return service.pwSearch(map);
	}
	@ResponseBody
	@RequestMapping(value = "updateInfo.do")
	public Map<String, Object> updateInfo(@RequestBody Map<String, Object> map) {
		return service.updateInfo(map);
	}
	@ResponseBody
	@RequestMapping(value = "updatePw.do")
	public Map<String, Object> updatePw(@RequestBody Map<String, Object> map) {
		return service.updatePw(map);
	}
	@ResponseBody
	@RequestMapping(value = "usingCheck.do")
	public Map<String, Object> usingCheck(@RequestBody Map<String, Object> map) {
		return service.usingCheck(map);
	}
	@ResponseBody
	@RequestMapping(value = "quitMember.do")
	public Map<String, Object> quitMember(@RequestBody Map<String, Object> map) {
		return service.quitMember(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectMap.do")
	public Map<String, Object> selectMap(@RequestBody Map<String, Object> map) {
		return service.selectMap(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectStorageList.do")
	public Map<String, Object> selectStorageList(@RequestBody Map<String, Object> map) {
		return service.selectStorageList(map);
	}
	@ResponseBody
	@RequestMapping(value = "ajaxCountStorage.do")
	public Map<String, Object> ajaxCountStorage(@RequestBody Map<String, Object> map) {
		return service.ajaxCountStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectStorageStatus.do")
	public Map<String, Object> selectStorageStatus(@RequestBody Map<String, Object> map) {
		return service.selectStorageStatus(map);
	}
	@ResponseBody
	@RequestMapping(value = "tagNFC.do")
	public Map<String, Object> tagNFC(@RequestBody Map<String, Object> map) {
		return service.tagNFC(map);
	}
	@ResponseBody
	@RequestMapping(value = "insertGoods.do")
	public Map<String, Object> insertGoods(@RequestBody Map<String, Object> map) {
		return service.insertGoods(map);
	}
	@ResponseBody
	@RequestMapping(value = "scheduledForMidnight.do")
	public Map<String, Object> scheduledForMidnight(@RequestBody Map<String, Object> map) {
		return service.scheduledForMidnight(map);
	}

	@ResponseBody
	@RequestMapping(value = "updateExtend.do")
	public Map<String, Object> updateExtend(@RequestBody Map<String, Object> map) {
		return service.updateExtend(map);
	}
	@ResponseBody
	@RequestMapping(value = "compareKey.do")
	public Map<String, Object> compareKey(@RequestBody Map<String, Object> map) {
		return service.compareKey(map);
	}
	@ResponseBody
	@RequestMapping(value = "updateExtraCost.do")
	public Map<String, Object> updateExtraCost(@RequestBody Map<String, Object> map) {
		return service.updateExtraCost(map);
	}
	@ResponseBody
	@RequestMapping(value = "afterPayment.do")
	public Map<String, Object> afterPayment(@RequestBody Map<String, Object> map) {
		return service.afterPayment(map);
	}
	@ResponseBody
	@RequestMapping(value = "checkOutEmail.do")
	public Map<String, Object> checkOutEmail(@RequestBody Map<String, Object> map) {
		return service.checkOutEmail(map);
	}
	@ResponseBody
	@RequestMapping(value = "updateOutUser.do")
	public Map<String, Object> updateOutUser(@RequestBody Map<String, Object> map) {
		return service.updateOutUser(map);
	}
	@ResponseBody
	@RequestMapping(value = "insertReturn.do")
	public Map<String, Object> insertReturn(@RequestBody Map<String, Object> map) {
		return service.insertReturn(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectUserStorageList.do")
	public Map<String, Object> selectUserStorageList(@RequestBody Map<String, Object> map) {
		return service.selectUserStorageList(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectStorageBoxList.do")
	public Map<String, Object> selectStorageBoxList(@RequestBody Map<String, Object> map) {
		return service.selectStorageBoxList(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectStorageGoods.do")
	public Map<String, Object> selectStorageGoods(@RequestBody Map<String, Object> map) {
		return service.selectStorageGoods(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectTimeTableSeq.do")
	public Map<String, Object> selectTimeTableSeq(@RequestBody Map<String, Object> map) {
		logger.trace("@@@@@@ selectTimeTableSeq @@@@@@@ "+ map);
		return service.selectTimeTableSeq(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectSubwayCnt.do")
	public Map<String, Object> selectSubwayCnt(@RequestBody Map<String, Object> map) {
		return service.selectSubwayCnt(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDeliveryMan.do")
	public Map<String, Object> selectDeliveryMan(@RequestBody Map<String, Object> map) {
		return service.selectDeliveryMan(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDeliveryLoc.do")
	public Map<String, Object> selectDeliveryLoc(@RequestBody Map<String, Object> map) {
		return service.selectDeliveryLoc(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDeliveryQty.do")
	public Map<String, Object> selectDeliveryQty(@RequestBody Map<String, Object> map) {
		return service.selectDeliveryQty(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDeliveryTime.do")
	public Map<String, Object> selectDeliveryTime(@RequestBody Map<String, Object> map) {
		return service.selectDeliveryTime(map);
	}
	@ResponseBody
	@RequestMapping(value = "insertDelivery.do")
	public Map<String, Object> insertDelivery(@RequestBody Map<String, Object> map) {
		return service.insertDelivery(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDeliveryList.do")
	public Map<String, Object> selectDeliveryList(@RequestBody Map<String, Object> map) {
		return service.selectDeliveryList(map);
	}
	@ResponseBody
	@RequestMapping(value = "updatedeliveryStrat.do")
	public Map<String, Object> updatedeliveryStrat(@RequestBody Map<String, Object> map) {
		return service.updatedeliveryStrat(map);
	}
	@ResponseBody
	@RequestMapping(value = "updateCostStatus.do")
	public Map<String, Object> updateCostStatus(@RequestBody Map<String, Object> map) {
		return service.updateCostStatus(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectAllLostProperty.do")
	public Map<String, Object> selectAllLostProperty(@RequestBody Map<String, Object> map) {
		return service.selectAllLostProperty(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectOneLostProperty.do")
	public Map<String, Object> selectOneLostProperty(@RequestBody Map<String, Object> map) {
		return service.selectOneLostProperty(map);
	}
	@ResponseBody
	@RequestMapping(value = "insertReport.do")
	public Map<String, Object> insertReport(@RequestBody Map<String, Object> map) {
		return service.insertReport(map);
	}
	@ResponseBody
	@RequestMapping(value = "replyReport.do")
	public Map<String, Object> replyReport(@RequestBody Map<String, Object> map) {
		return service.replyReport(map);
	}
	@ResponseBody
	@RequestMapping(value = "reply.do")
	public Map<String, Object> reply(@RequestBody Map<String, Object> map) {
		return service.reply(map);
	}
	@ResponseBody
	@RequestMapping(value = "modifyReport.do")
	public Map<String, Object> modifyReport(@RequestBody Map<String, Object> map) {
		return service.modifyReport(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectAllReport.do")
	public Map<String, Object> selectAllReport(@RequestBody Map<String, Object> map) {
		return service.selectAllReport(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDetailReport.do")
	public Map<String, Object> selectDetailReport(@RequestBody Map<String, Object> map) {
		return service.selectDetailReport(map);
	}
	@ResponseBody
	@RequestMapping(value = "updateProcessStatus.do")
	public Map<String, Object> updateProcessStatus(@RequestBody Map<String, Object> map) {
		return service.updateProcessStatus(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDetailGoReply.do")
	public Map<String, Object> selectDetailGoReply(@RequestBody Map<String, Object> map) {
		return service.selectDetailGoReply(map);
	}
	@ResponseBody
	@RequestMapping(value = "searchId.do")
	public Map<String, Object> searchId(@RequestBody Map<String, Object> map) {
		return service.searchId(map);
	}
	@ResponseBody
	@RequestMapping(value = "searchId2.do")
	public Map<String, Object> searchId2(@RequestBody Map<String, Object> map) {
		return service.searchId2(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectAllDelivery.do")
	public Map<String, Object> selectAllDelivery(@RequestBody Map<String, Object> map) {
		return service.selectAllDelivery(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDetail.do")
	public Map<String, Object> selectDetail(@RequestBody Map<String, Object> map) {
		return service.selectDetail(map);
	}
	@ResponseBody
	@RequestMapping(value = "deliveryInfo.do")
	public Map<String, Object> deliveryInfo(@RequestBody Map<String, Object> map) {
		return service.deliveryInfo(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectTempDelivery.do")
	public Map<String, Object> selectTempDelivery(@RequestBody Map<String, Object> map) {
		return service.selectTempDelivery(map);
	}
	@ResponseBody
	@RequestMapping(value = "modifyAuth.do")
	public Map<String, Object> modifyAuth(@RequestBody Map<String, Object> map) {
		return service.modifyAuth(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectIdDelivery.do")
	public Map<String, Object> selectIdDelivery(@RequestBody Map<String, Object> map) {
		return service.selectIdDelivery(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectAllStorage.do")
	public Map<String, Object> selectAllStorage(@RequestBody Map<String, Object> map) {
		return service.selectAllStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectIdStorage.do")
	public Map<String, Object> selectIdStorage(@RequestBody Map<String, Object> map) {
		return service.selectIdStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectSubwayStorage.do")
	public Map<String, Object> selectSubwayStorage(@RequestBody Map<String, Object> map) {
		return service.selectSubwayStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDetailStorage.do")
	public Map<String, Object> selectDetailStorage(@RequestBody Map<String, Object> map) {
		return service.selectDetailStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectBoxStatus.do")
	public Map<String, Object> selectBoxStatus(@RequestBody Map<String, Object> map) {
		return service.selectBoxStatus(map);
	}
	@ResponseBody
	@RequestMapping(value = "registStorage.do")
	public Map<String, Object> registStorage(@RequestBody Map<String, Object> map) {
		return service.registStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "modifyStorage.do")
	public Map<String, Object> modifyStorage(@RequestBody Map<String, Object> map) {
		return service.modifyStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "activateStorage.do")
	public Map<String, Object> activateStorage(@RequestBody Map<String, Object> map) {
		return service.activateStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectAll.do")
	public Map<String, Object> selectAll(@RequestBody Map<String, Object> map) {
		return service.selectAll(map);
	}
	@ResponseBody
	@RequestMapping(value = "countMember.do")
	public Map<String, Object> countMember(@RequestBody Map<String, Object> map) {
		return service.countMember(map);
	}
	@ResponseBody
	@RequestMapping(value = "memberIdSearch.do")
	public Map<String, Object> memberIdSearch(@RequestBody Map<String, Object> map) {
		return service.memberIdSearch(map);
	}
	@ResponseBody
	@RequestMapping(value = "detailMember.do")
	public Map<String, Object> detailMember(@RequestBody Map<String, Object> map) {
		return service.detailMember(map);
	}
	@ResponseBody
	@RequestMapping(value = "memberUsing.do")
	public Map<String, Object> memberUsing(@RequestBody Map<String, Object> map) {
		return service.memberUsing(map);
	}
	
	@ResponseBody
	@RequestMapping(value = "countReport.do")
	public Map<String, Object> countReport(@RequestBody Map<String, Object> map) {
		return service.countReport(map);
	}

	@ResponseBody
	@RequestMapping(value = "selectReport.do")
	public Map<String, Object> selectReport(@RequestBody Map<String, Object> map) {
		return service.selectReport(map);
	}
	
//	@ResponseBody
//	@RequestMapping(value = "insertKey.do")
//	public Map<String, Object> insertKey(@RequestBody Map<String, Object> map) {
//		return service.insertKey(map);
//	}
//	
//	@ResponseBody
//	@RequestMapping(value = "updateKey.do")
//	public Map<String, Object> updateKey(@RequestBody Map<String, Object> map) {
//		return service.updateKey(map);
//	}
	

	
}
