<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <style type="text/css">
#table{
  	width:70%;
  	margin: auto;
  }
 #searchBar{
 width:70%;
 display: flex;
text-align: center;
 }
 #container{
 	padding : 30px;
 }
  
</style>
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

function selChange() {
	var sel = document.getElementById('cntPerPage').value;
	location.href="./allMember.do?nowPage=${paging.nowPage}&email=${email}&cntPerPage="+sel;
}

function searchUser(){
	email = document.getElementById('emailSearch').value;
	location.href='./allMember.do?nowPage=1&cntPerPage=${paging.cntPerPage}&email='+email;
}
function allUser(){
	location.href='./allMember.do?nowPage=1&cntPerPage=${paging.cntPerPage}';
}
</script>

<div id="container">
<div id="searchBar">
<input type="text" id="emailSearch" class="form-control">
<input type="button" value="검색" onclick="searchUser()" class="btn btn-info">
<input type="button" value="전체보기" onclick="allUser()" class="btn btn-default">
<div style="float: right;">
		<select id="cntPerPage" name="sel" onchange="selChange()">
			<option value="5"
				<c:if test="${paging.cntPerPage == 5}">selected</c:if>>5명씩 보기</option>
			<option value="10"
				<c:if test="${paging.cntPerPage == 10}">selected</c:if>>10명씩 보기</option>
			<option value="15"
				<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15명씩 보기</option>
			<option value="20"
				<c:if test="${paging.cntPerPage == 20}">selected</c:if>>20명씩 보기</option>
		</select>
	</div> <!-- 옵션선택 끝 -->

</div>
	<div id="table">
	<table class="table table-hover">
	<tr>
		<td>이메일</td>
		<td>이름</td>
		<td>번호</td>
		<td>권한</td>
		<td>가입일</td>
		<td>탈퇴일</td>
	</tr>
	<c:forEach var="mem" items="${viewAll }">
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
	</div>

	<div style="display: block; text-align: center;">		
		<c:if test="${paging.startPage != 1 }">
			<a href="./allMember.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}&email=${email}">&lt;</a>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != paging.nowPage }">
					<a href="./allMember.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}&email=${email}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="./allMember.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}&email=${email}">&gt;</a>
		</c:if>
	</div>
</div>
</body>
</html>