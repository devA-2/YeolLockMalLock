<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송 조회</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function receiptBtn(deliveryCode) {
		$.ajax({
			url: "./pickUp.do",
			data: {"deliveryCode":deliveryCode},
			type: "POST",
			success : function(){
				console.log("물품 수령")
			},
			error : function(){
				alert("서비스에 문제가 생겼습니다.")		
			}
		});
	}
</script>
</head>
<body>
	<c:forEach var="list" items="${deliveryList}">
		<c:choose>
			<c:when test="${list ne null}">
				<div>
					<span>${list.storageName}-${list.boxSeq}</span>
					<span>/ ${list.subway} ${list.detail}</span>
					<span>/ 배송역 ${list.outboxId}</span>
				</div>
				<div>
					<button onclick="receiptBtn('${list.deliveryCode}')">수령</button>
				</div>
				<hr>
			</c:when>
			<c:otherwise>
			내역이 없습니다.
		</c:otherwise>
		</c:choose>
	</c:forEach>
</body>
</html>