<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<title>보관 내역</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="../js/userStorageList.js"></script>
<style type="text/css">
	.panel-group{
		width: 100%;
		height: 520px;
		overflow-y: auto;
	}
	.form{
		margin-top: 5%;
		position: relative;
	}
	.panel-body{
		position: relative;
	}
	.panel-left{
		width: 80%;
	}
	.panel-left span{
		font-size: 10px;
	}
	.panel-right{
		width: 60px;
		position: absolute;
		top: 50%;
		right: 2%;
		margin-top: -14%;
	}
	.panel-right button{
		margin-top: 7%;
		margin-bottom: 7%;
	}
</style>
</head>
<body>
	<div id="container" class="container">
		<jsp:include page="../menu.jsp"/>
		<div id="content">
			<div class="panel-group">
			<c:forEach var="list" items="${list}" varStatus="vs">
				<c:if test="${list.inUser eq mem.email}">
					<form id="storeForm${vs.count}" class="form" method="post">
						<div class="panel panel-success">
							<div class="panel-heading">
								<span>보관</span>
								<input type="hidden" name="boxSeq" value="${list.boxSeq}">
								<input type="hidden" name="storageId" value="${list.storageId}">
							</div>
			      			<div class="panel-body">
			      				<div class="panel-left">
				      				<span>${list.storageName}(${list.subway} ${list.detail})  ${list.boxSeq}번 보관함</span><br>
									<span>보관 시작 시간 : ${list.inTime}</span><br>
									<span>수령 사용자 : ${list.outUser}</span><br>
									<span>배송 여부 : 
										<span id="OX${vs.count}">
											<c:choose>
												<c:when test="${list.categoryCode eq 'D' || list.categoryCode eq 'RD'}">O</c:when>
												<c:when test="${list.categoryCode eq 'S' || list.categoryCode eq 'R'}">X</c:when>
											</c:choose>
										</span>
									</span>
								</div>
								<div class="panel-right">
									<button class="btn btn-info" onclick="exchangeBtn(this.form)">교환</button>
									<button class="btn btn-info" onclick="deliveryBtn(${vs.count})">배송</button>
								</div>
			      			</div>
						</div>
					</form>
				</c:if>
				<c:if test="${list.inUser eq mem.email}">
					<form id="storeForm${vs.count}" class="form" method="post">
						<div class="panel panel-info">
							<div class="panel-heading">
								<span>수령</span>
								<input type="hidden" name="boxSeq" value="${list.boxSeq}">
								<input type="hidden" name="storageId" value="${list.storageId}">
							</div>
			      			<div class="panel-body">
			      				<div class="panel-left">
				      				<span>${list.storageName}(${list.subway} ${list.detail})  ${list.boxSeq}번 보관함</span><br>
									<span>보관 사용자 : ${list.inUser}</span><br>
									<span>수령 사용자 : ${list.outUser}</span><br>
									<span>배송 여부 : 
										<span id="OX${vs.count}">
											<c:choose>
												<c:when test="${list.categoryCode eq 'D' || list.categoryCode eq 'RD'}">O</c:when>
												<c:when test="${list.categoryCode eq 'S' || list.categoryCode eq 'R'}">X</c:when>
											</c:choose>
										</span>
									</span><br>
									<span>연장 횟수 : ${list.extendCnt}회</span><br>
									<span>보관 비용 : ${list.cost}원</span><br>
									<span>보관 만료 시간 : ${list.exTime}</span><br>
									<span>
										<c:choose>
											<c:when test="${list.overTime eq 0}"></c:when>
											<c:when test="${list.overTime ne 0}">${list.overH}시간 ${list.overM}분 초과(결제시 ${list.overCost}원 추가 결제)</c:when>
										</c:choose>
										<input type="hidden" name="overCost" value="${list.overCost}">
									</span>
								</div>
								<div class="panel-right">
									<c:if test="${list.extendCnt<2}">
										<input type="button" class="btn btn-info" value="연장" onclick="extendBtn(${vs.count})">
									</c:if>
									<button class="btn btn-info" onclick="paymentBtn(this.form)">결제</button>
								</div>
			      			</div>
						</div>
					</form>
				</c:if>
			</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
