<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<title>배송 조회</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="../js/userStorageList.js"></script>
<link rel="stylesheet" href="../css/common.css">
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
<style type="text/css">
	.panel-body{
		font-size: 10px;
	}
</style>
</head>
<body>
	<div id="container" class="container">
		<jsp:include page="../menu.jsp"/>
		<div id="content">
			<div class="panel-group">
				<c:choose>
					<c:when test="${auth eq '10'}">
						<c:forEach var="list" items="${deliveryList}">
							<div class="panel panel-success">
								<span>배송</span>
							</div>
							<div class="panel-body">
								<span>${list.storageName}(${list.subway} ${list.detail})  ${list.boxSeq}번 보관함</span><br>
								<span>배송 담당자 : ${list.deliverymanId}</span>
								<c:if test="${list.deliveryArrive ne null}">
									<span>물품 도착시간 : ${list.deliveryArrive}</span>
								</c:if>
							</div>
						</c:forEach>
					</c:when>
					<c:when test="${auth eq '80'}">
						<c:forEach var="list" items="${deliveryList}">
							<div class="panel panel-success">
								<span>배송</span>
							</div>
							<div class="panel-body">
								<div class="panel-left">
									<span>${list.storageName}(${list.subway} ${list.detail})  ${list.boxSeq}번 보관함</span><br>
									<span>배송역 : ${list.outboxId}</span>
								</div>
								<div class="panel-right">
									<button class="btn btn-info" onclick="receiptBtn('${list.deliveryCode}')">수령</button>
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						내역이 없습니다.
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>