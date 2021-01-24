<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보관 내역</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="../js/userStorageList.js"></script>
</head>
<body>
	<c:forEach var="list" items="${list}" varStatus="vs">
			<c:if test="${list.inUser eq mem.email}">
				<form id="storeForm${vs.count}" method="get">
					<div>
						<span>보관</span><br>
						<input type="hidden" name="boxSeq" value="${list.boxSeq}">
						<input type="hidden" name="storageId" value="${list.storageId}">
						<span>${list.storageName}-${list.boxSeq}</span>
						<span>${list.subway} ${list.detail}</span><br>
						<span>보관 시작 시간 ${list.inTime}</span><br>
						<span>수령 사용자 ${list.outUser}</span><br>
						<span>배송 여부
							<span id="OX${vs.count}">
								<c:choose>
									<c:when test="${list.categoryCode eq 'D' || list.categoryCode eq 'RD'}">O</c:when>
									<c:when test="${list.categoryCode eq 'S' || list.categoryCode eq 'R'}">X</c:when>
								</c:choose>
							</span>
						</span>
					</div>
					<div>
						<button onclick="exchangeBtn(this.form)">교환</button>
						<button onclick="deliveryBtn(${vs.count})">배송</button>
					</div>
					<hr>
				</form>
			</c:if>
			<c:if test="${list.outUser eq mem.email}">
				<form id="receiptForm${vs.count}" method="post">
					<div>
						<span>수령</span><br>
						<input type="hidden" name="boxSeq" value="${list.boxSeq}">
						<input type="hidden" name="storageId" value="${list.storageId}">
						<span>${list.storageName}-${list.boxSeq}</span>
						<span>${list.subway} ${list.detail}</span><br>
						<span>보관 사용자 ${list.inUser}</span>
						<span>수령 사용자 ${list.outUser}</span><br>
						<span>보관 비용 ${list.cost}원</span><br>
						<span>배송 여부 
							<c:choose>
								<c:when test="${list.categoryCode eq 'D' || list.categoryCode eq 'RD'}">O</c:when>
								<c:when test="${list.categoryCode eq 'S' || list.categoryCode eq 'R'}">X</c:when>
							</c:choose>
						</span><br>
						<span>보관 만료 시간 ${list.exTime}</span>
						<span>연장 횟수 ${list.extendCnt}</span><br>
						<span>
							<c:choose>
								<c:when test="${list.overTime eq 0}"></c:when>
								<c:when test="${list.overTime ne 0}">${list.overH}시간 ${list.overM}분 초과(결제시 ${list.overCost}원 추가 결제)</c:when>
							</c:choose>
							<input type="hidden" name="overCost" value="${list.overCost}">
						</span>
					</div>
					<div>
						<c:if test="${list.extendCnt<2}">
<!-- 						버튼 태그가 폼전체를 넘어가게 하는듯 -> input으로 수정함  -->
							<input type="button" value="연장" onclick="extendBtn(${vs.count})">
						</c:if>
						<button onclick="paymentBtn(this.form)">결제</button>
					</div>
					<hr>
				</form>
			</c:if>
	</c:forEach>
</body>
</html>
