<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 회원 보기</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="./css/common.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="./js/managerAuth.js"></script>
 <style type="text/css">
 </style>
</head>
<body>
<div id="container">
	<div id="detailMember" >
	이메일 : ${dto.email }<br>
	이름 : ${dto.name }<br>
	번호 : ${dto.phoneNum }<br>
	권한 : ${dto.auth }<br>
	가입일 :${dto.regDate}<br>
	탈퇴일 :${dto.levDate }<br>
	</div>
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
		<tr>
			<td>${goods.storageId}</td>
			<td>${goods.boxSeq}</td>
			<td>${goods.inUser}</td>
			<td>${goods.outUser}</td>
			<td>${goods.inTime}</td>
			<td>${goods.exTime }</td>
			<td>${goods.cost}</td>
		</tr>	
	</c:forEach>
</table>
</body>
</html>