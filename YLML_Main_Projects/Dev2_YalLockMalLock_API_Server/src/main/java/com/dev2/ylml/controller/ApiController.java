package com.dev2.ylml.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public Map<String, Object> insertMember(Map<String, Object> map) {
		return service.insertMember(map);
	}
	@ResponseBody
	@RequestMapping(value = "idCheck.do")
	public Map<String, Object> idCheck(Map<String, Object> map) {
		return service.idCheck(map);
	}
	@ResponseBody
	@RequestMapping(value = "phoneCheck.do")
	public Map<String, Object> phoneCheck(Map<String, Object> map) {
		return service.phoneCheck(map);
	}
	@ResponseBody
	@RequestMapping(value = "login.do")
	public Map<String, Object> login(Map<String, Object> map) {
		return service.login(map);
	}
	@ResponseBody
	@RequestMapping(value = "apiLogin.do")
	public Map<String, Object> apiLogin(Map<String, Object> map) {
		return service.apiLogin(map);
	}
	@ResponseBody
	@RequestMapping(value = "authUpdate.do")
	public Map<String, Object> authUpdate(Map<String, Object> map) {
		return service.authUpdate(map);
	}
	@ResponseBody
	@RequestMapping(value = "idSearch.do")
	public Map<String, Object> idSearch(Map<String, Object> map) {
		return service.idSearch(map);
	}
	@ResponseBody
	@RequestMapping(value = "pwSearch.do")
	public Map<String, Object> pwSearch(Map<String, Object> map) {
		return service.pwSearch(map);
	}
	@ResponseBody
	@RequestMapping(value = "updateInfo.do")
	public Map<String, Object> updateInfo(Map<String, Object> map) {
		return service.updateInfo(map);
	}
	@ResponseBody
	@RequestMapping(value = "updatePw.do")
	public Map<String, Object> updatePw(Map<String, Object> map) {
		return service.updatePw(map);
	}
	@ResponseBody
	@RequestMapping(value = "usingCheck.do")
	public Map<String, Object> usingCheck(Map<String, Object> map) {
		return service.usingCheck(map);
	}
	@ResponseBody
	@RequestMapping(value = "quitMember.do")
	public Map<String, Object> quitMember(Map<String, Object> map) {
		return service.quitMember(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectMap.do")
	public Map<String, Object> selectMap(Map<String, Object> map) {
		return service.selectMap(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectStorageList.do")
	public Map<String, Object> selectStorageList(Map<String, Object> map) {
		return service.selectStorageList(map);
	}
	@ResponseBody
	@RequestMapping(value = "ajaxCountStorage.do")
	public Map<String, Object> ajaxCountStorage(Map<String, Object> map) {
		return service.ajaxCountStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectStorageStatus.do")
	public Map<String, Object> selectStorageStatus(Map<String, Object> map) {
		return service.selectStorageStatus(map);
	}
	@ResponseBody
	@RequestMapping(value = "insertGoods.do")
	public Map<String, Object> insertGoods(Map<String, Object> map) {
		return service.insertGoods(map);
	}
	@ResponseBody
	@RequestMapping(value = "updateAllStatus.do")
	public Map<String, Object> updateAllStatus(Map<String, Object> map) {
		return service.updateAllStatus(map);
	}
	@ResponseBody
	@RequestMapping(value = "updateExtend.do")
	public Map<String, Object> updateExtend(Map<String, Object> map) {
		return service.updateExtend(map);
	}
	@ResponseBody
	@RequestMapping(value = "compareKey.do")
	public Map<String, Object> compareKey(Map<String, Object> map) {
		return service.compareKey(map);
	}
	@ResponseBody
	@RequestMapping(value = "updateExtraCost.do")
	public Map<String, Object> updateExtraCost(Map<String, Object> map) {
		return service.updateExtraCost(map);
	}
	@ResponseBody
	@RequestMapping(value = "afterPayment.do")
	public Map<String, Object> afterPayment(Map<String, Object> map) {
		return service.afterPayment(map);
	}
	@ResponseBody
	@RequestMapping(value = "checkOutEmail.do")
	public Map<String, Object> checkOutEmail(Map<String, Object> map) {
		return service.checkOutEmail(map);
	}
	@ResponseBody
	@RequestMapping(value = "updateOutUser.do")
	public Map<String, Object> updateOutUser(Map<String, Object> map) {
		return service.updateOutUser(map);
	}
	@ResponseBody
	@RequestMapping(value = "insertReturn.do")
	public Map<String, Object> insertReturn(Map<String, Object> map) {
		return service.insertReturn(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectUserStorageList.do")
	public Map<String, Object> selectUserStorageList(Map<String, Object> map) {
		return service.selectUserStorageList(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectStorageBoxList.do")
	public Map<String, Object> selectStorageBoxList(Map<String, Object> map) {
		return service.selectStorageBoxList(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectTimeTableSeq.do")
	public Map<String, Object> selectTimeTableSeq(Map<String, Object> map) {
		return service.selectTimeTableSeq(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDeliveryMan.do")
	public Map<String, Object> selectDeliveryMan(Map<String, Object> map) {
		return service.selectDeliveryMan(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDeliveryLoc.do")
	public Map<String, Object> selectDeliveryLoc(Map<String, Object> map) {
		return service.selectDeliveryLoc(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDeliveryQty.do")
	public Map<String, Object> selectDeliveryQty(Map<String, Object> map) {
		return service.selectDeliveryQty(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDeliveryTime.do")
	public Map<String, Object> selectDeliveryTime(Map<String, Object> map) {
		return service.selectDeliveryTime(map);
	}
	@ResponseBody
	@RequestMapping(value = "insertDelivery.do")
	public Map<String, Object> insertDelivery(Map<String, Object> map) {
		return service.insertDelivery(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectUserDeliveryList.do")
	public Map<String, Object> selectUserDeliveryList(Map<String, Object> map) {
		return service.selectUserDeliveryList(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectAllLostProperty.do")
	public Map<String, Object> selectAllLostProperty(Map<String, Object> map) {
		return service.selectAllLostProperty(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectOneLostProperty.do")
	public Map<String, Object> selectOneLostProperty(Map<String, Object> map) {
		return service.selectOneLostProperty(map);
	}
	@ResponseBody
	@RequestMapping(value = "insertReport.do")
	public Map<String, Object> insertReport(Map<String, Object> map) {
		return service.insertReport(map);
	}
	@ResponseBody
	@RequestMapping(value = "replyReport.do")
	public Map<String, Object> replyReport(Map<String, Object> map) {
		return service.replyReport(map);
	}
	@ResponseBody
	@RequestMapping(value = "reply.do")
	public Map<String, Object> reply(Map<String, Object> map) {
		return service.reply(map);
	}
	@ResponseBody
	@RequestMapping(value = "modifyReport.do")
	public Map<String, Object> modifyReport(Map<String, Object> map) {
		return service.modifyReport(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectAllReport.do")
	public Map<String, Object> selectAllReport(Map<String, Object> map) {
		return service.selectAllReport(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDetailReport.do")
	public Map<String, Object> selectDetailReport(Map<String, Object> map) {
		return service.selectDetailReport(map);
	}
	@ResponseBody
	@RequestMapping(value = "updateProcessStatus.do")
	public Map<String, Object> updateProcessStatus(Map<String, Object> map) {
		return service.updateProcessStatus(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDetailGoReply.do")
	public Map<String, Object> selectDetailGoReply(Map<String, Object> map) {
		return service.selectDetailGoReply(map);
	}
	@ResponseBody
	@RequestMapping(value = "searchId.do")
	public Map<String, Object> searchId(Map<String, Object> map) {
		return service.searchId(map);
	}
	@ResponseBody
	@RequestMapping(value = "searchId2.do")
	public Map<String, Object> searchId2(Map<String, Object> map) {
		return service.searchId2(map);
	}
	@ResponseBody
	@RequestMapping(value = "loginMember.do")
	public Map<String, Object> loginMember(Map<String, Object> map) {
		return service.loginMember(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectAllDelivery.do")
	public Map<String, Object> selectAllDelivery(Map<String, Object> map) {
		return service.selectAllDelivery(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDetail.do")
	public Map<String, Object> selectDetail(Map<String, Object> map) {
		return service.selectDetail(map);
	}
	@ResponseBody
	@RequestMapping(value = "deliveryInfo.do")
	public Map<String, Object> deliveryInfo(Map<String, Object> map) {
		return service.deliveryInfo(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectTempDelivery.do")
	public Map<String, Object> selectTempDelivery(Map<String, Object> map) {
		return service.selectTempDelivery(map);
	}
	@ResponseBody
	@RequestMapping(value = "modifyAuth.do")
	public Map<String, Object> modifyAuth(Map<String, Object> map) {
		return service.modifyAuth(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectIdDelivery.do")
	public Map<String, Object> selectIdDelivery(Map<String, Object> map) {
		return service.selectIdDelivery(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectAllStorage.do")
	public Map<String, Object> selectAllStorage(Map<String, Object> map) {
		return service.selectAllStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectIdStorage.do")
	public Map<String, Object> selectIdStorage(Map<String, Object> map) {
		return service.selectIdStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectSubwayStorage.do")
	public Map<String, Object> selectSubwayStorage(Map<String, Object> map) {
		return service.selectSubwayStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectDetailStorage.do")
	public Map<String, Object> selectDetailStorage(Map<String, Object> map) {
		return service.selectDetailStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectBoxStatus.do")
	public Map<String, Object> selectBoxStatus(Map<String, Object> map) {
		return service.selectBoxStatus(map);
	}
	@ResponseBody
	@RequestMapping(value = "registStorage.do")
	public Map<String, Object> registStorage(Map<String, Object> map) {
		return service.registStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "modifyStorage.do")
	public Map<String, Object> modifyStorage(Map<String, Object> map) {
		return service.modifyStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "activateStorage.do")
	public Map<String, Object> activateStorage(Map<String, Object> map) {
		return service.activateStorage(map);
	}
	@ResponseBody
	@RequestMapping(value = "selectAll.do")
	public Map<String, Object> selectAll(Map<String, Object> map) {
		return service.selectAll(map);
	}
	@ResponseBody
	@RequestMapping(value = "memberIdSearch.do")
	public Map<String, Object> memberIdSearch(Map<String, Object> map) {
		return service.memberIdSearch(map);
	}
	@ResponseBody
	@RequestMapping(value = "detailMember.do")
	public Map<String, Object> detailMember(Map<String, Object> map) {
		return service.detailMember(map);
	}
	@ResponseBody
	@RequestMapping(value = "memberUsing.do")
	public Map<String, Object> memberUsing(Map<String, Object> map) {
		return service.memberUsing(map);
	}
}
