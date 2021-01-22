<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<script type="text/javascript">
$( function() {
	//자동완성 ajax
	$.ajax({
		url : './memberIdSearch.do',
		type : 'get',
		dataType : 'json',
		success : function(emailList) {
// 			console.log(emailList);
			$( "#emailSearch" ).autocomplete({
			      source: emailList
			    });
		},
		error : function() {
			console.log("ajax 오류");
		}
	});//ajax
}); 

function searchUser(){
	email = document.getElementById('emailSearch').value;
	location.href='./allMember.do?email='+email;
}
</script>
<input type="text" id="emailSearch">
<input type="button" value="검색" onclick="searchUser()">
<table>
	<tr>
		<td>이메일</td>
		<td>이름</td>
		<td>번호</td>
		<td>권한</td>
		<td>가입일</td>
		<td>탈퇴일</td>
	</tr>
	<c:forEach var="mem" items="${list }">
		<tr>
			<td><a href="./memberDetail.do?email=${mem.email}">${mem.email}</a></td>
			<td>${mem.name}</td>
			<td>${mem.phoneNum }</td>
			<td>
				<c:choose>
					<c:when test="${mem.auth==10 }">회원</c:when>
					<c:otherwise>대기</c:otherwise>
				</c:choose>
			</td>
			<td>${mem.regDate }</td>
			<td>
				<c:if test="${mem.delFlag=='Y' }">
					${mem.levDate }
				</c:if>
			</td>
		</tr>	
	</c:forEach>
</table>
</body>
</html>