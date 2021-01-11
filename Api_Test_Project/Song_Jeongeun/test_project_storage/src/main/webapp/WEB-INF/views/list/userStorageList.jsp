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
	function deliveryForm() {
		var storageId = document.getElementById("storageId").value
		var boxSeq = document.getElementById("boxSeq").value
		
		$.ajax({
			url: "./idSeq.do",
			data: {"storageId":storageId, "boxSeq":boxSeq},
			type: "POST",
			success : function(msg){
				console.log("성공")
				location.href = "./deliveryForm.do"
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
				<input type="hidden" id="storageId" value="${list.storageId}">
				<input type="hidden" id="boxSeq" value="${list.boxSeq}">
				<span>${list.storageName}-${list.boxSeq} /</span>
				<span>${list.subway} ${list.detail} /</span>
				<span>보관 시작 시간 ${list.inTime} /</span>
				<span>수령 사용자 ${list.outUser} /</span>
				<span>배송 여부 
					<c:if test="${list.categoryCode eq 'D' || 'RD'}">O</c:if>
					<c:if test="${list.categoryCode ne 'D' || 'RD'}">X</c:if>
				</span>
			</div>
			<div>
				<button>교환</button>
				<button onclick="deliveryForm()">배송</button>
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
					<c:if test="${list.categoryCode eq 'D' || 'RD'}">O</c:if>
					<c:if test="${list.categoryCode ne 'D' || 'RD'}">X</c:if>
				/</span>
				<span>보관 만료 시간 ${list.exTime} /</span>
				<span>
					<c:if test="${list.overTime eq 0}"></c:if>
					<c:if test="${list.overTime ne 0}">${list.overTime}시간 초과 /</c:if>
				</span>
				<span>연장 횟수 ${list.extendCnt}</span>
			</div>
			<div>
				<button>연장</button>
				<button>결제</button>
			</div>
			<hr>
		</c:if>
	</c:forEach>
</body>
</html>