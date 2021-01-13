<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보관 내역</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function keyTransBtn(boxSeq, storageId) {
		$.ajax({
			url: "./dtoSession.do",
			data: {"boxSeq":boxSeq, "storageId":storageId},
			type: "POST",
			success : function(){
				location.href = "#"
			},
			error : function(){
				alert("서비스에 문제가 생겼습니다.")		
			}
		});
	}

	function deliveryBtn(boxSeq, storageId, categoryCode) {
		$.ajax({
			url: "./deliveryBtn.do",
			data: {"boxSeq":boxSeq, "storageId":storageId, "categoryCode":categoryCode},
			type: "POST",
			success : function(result){
				if(result == "success"){
					location.href = "./deliveryForm.do"
				}else{
					console.log("이미 배송 중인 물품입니다.")
				}
			},
			error : function(){
				alert("서비스에 문제가 생겼습니다.")		
			}
		});
	}
	
	function extendBtn(boxSeq, storageId) {
		$.ajax({
			url: "./extendBtn.do",
			data: {"boxSeq":boxSeq, "storageId":storageId},
			type: "POST",
			success : function(){
				location.href = "#"
			},
			error : function(){
				alert("서비스에 문제가 생겼습니다.")		
			}
		});
	}
	
	function paymentBtn(boxSeq, storageId) {
		$.ajax({
			url: "./paymentBtn.do",
			data: {"boxSeq":boxSeq, "storageId":storageId},
			type: "POST",
			success : function(){
				location.href = "#"
			},
			error : function(){
				alert("서비스에 문제가 생겼습니다.")		
			}
		});
	}
</script>
</head>
<body>
	<c:forEach var="list" items="${list}">
		<c:if test="${list.inUser eq 'user01@naver.com'}">
			<div>
				<span>보관 /</span>
				<input type="hidden" name="storageId" value="${list.storageId}">
				<input type="hidden" name="boxSeq" value="${list.boxSeq}">
				<span>${list.storageName}-${list.boxSeq} /</span>
				<span>${list.subway} ${list.detail} /</span>
				<span>보관 시작 시간 ${list.inTime} /</span>
				<span>수령 사용자 ${list.outUser} /</span>
				<span>배송 여부
					<c:choose>
						<c:when test="${list.categoryCode eq 'D' || list.categoryCode eq 'RD'}">O</c:when>
						<c:when test="${list.categoryCode eq 'S' || list.categoryCode eq 'R'}">X</c:when>
					</c:choose>
				</span>
			</div>
			<div>
				<button onclick="keyTransBtn('${list.boxSeq}', '${list.storageId}')">교환</button>
				<button onclick="deliveryBtn('${list.boxSeq}', '${list.storageId}', '${list.categoryCode}')">배송</button>
			</div>
			<hr>
		</c:if>
		<c:if test="${list.outUser eq 'user01@naver.com'}">
			<div>
				<span>수령 /</span>
				<input type="hidden" name="storageId" value="${list.storageId}">
				<input type="hidden" name="boxSeq" value="${list.boxSeq}">
				<span>${list.storageName}-${list.boxSeq} /</span>
				<span>${list.subway} ${list.detail} /</span>
				<span>보관 사용자 ${list.inUser} /</span>
				<span>수령 사용자 ${list.outUser} /</span>
				<span>보관 비용 ${list.cost}원 /</span>
				<span>배송 여부 
					<c:choose>
						<c:when test="${list.categoryCode eq 'D' || list.categoryCode eq 'RD'}">O</c:when>
						<c:when test="${list.categoryCode eq 'S' || list.categoryCode eq 'R'}">X</c:when>
					</c:choose>
				/</span>
				<span>보관 만료 시간 ${list.exTime} /</span>
				<span>
					<c:choose>
						<c:when test="${list.overTime eq 0}"></c:when>
						<c:when test="${list.overTime ne 0}">${list.overH}시간 ${list.overM}분 초과(결제시 ${list.overCost}원 추가 결제) /</c:when>
					</c:choose>
				</span>
				<span>연장 횟수 ${list.extendCnt}</span>
			</div>
			<div>
				<button onclick="extendBtn('${list.boxSeq}', '${list.storageId}')">연장</button>
				<button onclick="paymentBtn('${list.boxSeq}', '${list.storageId}')">결제</button>
			</div>
			<hr>
		</c:if>
	</c:forEach>
</body>
</html>