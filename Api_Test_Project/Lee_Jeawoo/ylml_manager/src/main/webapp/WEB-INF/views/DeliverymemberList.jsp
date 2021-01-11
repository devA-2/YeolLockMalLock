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
담당자 및 배송원 전체 정보조회

	<table border="1">
		<tr>
<!-- 			<th><input type="checkbox"  id="allCheck" onclick="checkAll(this.checked)"> </th> -->
			<th>이 름</th>
			<th>전화번호</th>
			<th>배송코드</th>
			<th>현재위치</th>
		</tr>
		<c:forEach varStatus="vs" items="${lists}" var="dto">
			<tr>
<%-- 				<td><input type="checkbox" name="ch" value="${dto.getSeq()}"> </td> --%>
				<td>${dto.name}</td>
				<td>${dto.phone_num}</td>
				<td>${dto.delivery_code}</td>
				<td>${dto.current_loc}</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<div>
		<input type="button" class="insert" value="글쓰기" onclick="location.href='./insertboard.do'"> 
		<input class="del" type="submit" value="다중삭제">
	</div>
	

</body>
</html>