<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송 조회</title>
</head>
<body>
	<c:forEach var="list" items="${deliveryList}">
		<div>
			<span>${list.storageName}-${list.boxSeq} /</span>
			<span>${list.subway} ${list.detail} /</span>
			<span>배송 담당자 ${list.deliverymanId} /</span>
			<span>물품 도착시간 ${list.deliveryArrive}</span>
		</div>
		<hr>
	</c:forEach>
</body>
</html>