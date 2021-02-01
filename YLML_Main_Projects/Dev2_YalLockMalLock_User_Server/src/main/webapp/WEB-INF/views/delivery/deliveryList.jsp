<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<title>배송 조회</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/common.css">
<script type="text/javascript" src="../js/userStorageList.js"></script>
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
	.menu {
		width: 100%;
		display: flex;
	}
	
	.panel-group {
		width: 100%;
		height: 520px;
		overflow-y: auto;
		font-size: 10px;
		margin-top: 5%;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="menu">
			<jsp:include page="../menu.jsp"/>
		</div>
		<div class="content">
			<div class="panel-group">
				<c:choose>
					<c:when test="${auth eq '10'}">
						<c:forEach var="list" items="${deliveryList}">
							<div class="panel panel-success">
								<div class="panel-heading">
									<span>배송</span>
								</div>
				      			<div class="panel-body">
				      				<span>보관 보관함 : ${list.storageName}(${list.subway} ${list.detail})</span><br>
				      				<span>배송 보관함 : ${list.subwayArr}</span><br>
									<span>배송 담당자 : ${list.deliverymanName}</span><br>
									<fmt:formatDate var="deliveryArrive" value="${list.deliveryArrive}" pattern="yyyy년 MM월 dd일 H시 mm분"/>
									<span>도착 예정 시간 : ${deliveryArrive}</span>
								</div>
							</div>
						</c:forEach>
					</c:when>
					
					<c:when test="${auth eq '80'}">
					<c:forEach var="list" items="${deliveryList}">
							<div class="panel panel-success">
								<div class="panel-heading">
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
							</div>
						</c:forEach>
					</c:when>
					
					<c:otherwise>
						<span>내역이 없습니다.</span>
					</c:otherwise>
				</c:choose>
			</div>
		</div>		
	</div>
</body>
</html>