<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원상세정보</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="./css/common.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="./js/managerAuth.js"></script>
 <style type="text/css">
 h1.info{
	font-weight: 600;
	text-align: center;
}

#detailMember{
	margin-left: 20%;
}
 </style>
</head>
<body>
<div id="container">
<%@include file="./header.jsp" %>
<h1 class="info">회원 상세조회</h1>
	<div id="detailMember">
		<label>이메일</label>
			<div>${dto.email }</div><br>
		<label>이름</label>
			<div>${dto.name }</div><br>
		<label>휴대폰 번호</label>
			<div>${dto.phoneNum }</div><br>
		<label>권한</label>
		 <div>${dto.auth }</div><br>
		<label>가입일</label>
		<div>${dto.phoneNum }</div><br>
		<label>탈퇴일</label>
		<div>${dto.levDate }</div><br>
	</div>
	<table class="table table-hover">
		<tr>
			<td>보관함</td>
			<td>보관함번호</td>
			<td>보관한 사용자</td>
			<td>수령할 사용자</td>
			<td>보관시간</td>
			<td>만료시간</td>
			<td>비용</td>
		</tr>
		<c:forEach var="goods" items="${usingList }">
			<c:choose>
				<c:when test="${usingList eq null}" >
				<tr>
					<td colspan="7">이용 중인 서비스가 없습니다.</td>
				</tr>
				</c:when>
					<c:otherwise>
						<tr>
							<td>${goods.storageId}</td>
							<td>${goods.boxSeq}</td>
							<td>${goods.inUser}</td>
							<td>${goods.outUser}</td>
							<td>${goods.inTime}</td>
							<td>${goods.exTime }</td>
							<td>${goods.cost}</td>
						</tr>	
					</c:otherwise>
			</c:choose>
		</c:forEach>
	</table>
</div>
</body>
</html>