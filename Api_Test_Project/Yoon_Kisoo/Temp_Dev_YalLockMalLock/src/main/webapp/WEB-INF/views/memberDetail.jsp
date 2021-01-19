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

이메일 : ${mDto.email }<br>
이름 : ${mDto.name }<br>
번호 : ${mDto.phoneNum }<br>
권한 : ${mDto.auth }<br>
가입일 :${mDto.regDate}<br>
탈퇴일 :${mDto.levDate }<br>
<table>
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