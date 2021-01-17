<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>담당자 및 배송원 전체 정보조회</title>
</head>
<body>
allDeleveryList.do<br>
담당자 및 배송원 전체 정보조회<br>
<h1><a href="./managerMain.do">관리자메인페이지로</a></h1>

	<div>
		<form action="./searchId.do" method="post">
		<input type="text"  placeholder="검색할 ID를 입력하세요">
		<input type="button"  value="ID검색" >
		</form> 
	</div>

	<table border="1">
		<tr>
			<th>이메일</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>권한</th>
		</tr>
		<c:forEach varStatus="vs" items="${lists}" var="dto">
			<tr>
				<td><a title="${dto.email}" href="./deliveryDetail.do?email=${dto.email}">${dto.email}</a></td>
				<td>${dto.name}</td>
				<td>${dto.phone_num}</td>
				<td>${dto.auth}</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	
	

</body>
</html>