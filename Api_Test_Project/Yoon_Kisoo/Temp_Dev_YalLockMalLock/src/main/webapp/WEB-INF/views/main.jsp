<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${mem != null }">
		<p>${mem.name }님 안녕하세요.</p>
		<p><a href="./logout.do">로그아웃</a></p>
		<p><a href="./myPage.do">마이페이지</a>
	</c:if>				

</body>
</html>