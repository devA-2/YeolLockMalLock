<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="./css/common.css">
<link rel="stylesheet" href="./css/boardCss.css">
</head>
<script type="text/javascript">
	function chkAuth(memEmail, voEmail, seq){
			if (voEmail == memEmail) {
// 				alert("동작");
				location.href='./selectOneLostProperty.do?seq='+seq;
			}else{
				alert("자신의 게시물만 확인할 수 있습니다.");
			}
		
		}
	
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="./pagingReportList.do?nowPage=${paging.nowPage}&cntPerPage="+sel;
	}
</script>
<body>
<div class="container">
	<div class="searchDiv">
		<form class="lostForm" action="./searchIdLostProperty.do" method="post">
		<input type="text" placeholder="검색할 ID를 입력하세요" name="receipt_user_id">
		<input class="btn btn-info" type="submit" value="검색">
	</div>

	<div style="float: right;">
		<select id="cntPerPage" name="sel" onchange="selChange()">
			<option value="5"
				<c:if test="${paging.cntPerPage == 5}">selected</c:if>>5줄 보기</option>
			<option value="10"
				<c:if test="${paging.cntPerPage == 10}">selected</c:if>>10줄 보기</option>
			<option value="15"
				<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15줄 보기</option>
			<option value="20"
				<c:if test="${paging.cntPerPage == 20}">selected</c:if>>20줄 보기</option>
		</select>
	</div>

	</form>
	<table class="table table-hover">
		<tr>
			<th>연번</th>
			<th>소유자</th>
			<th>등록일</th>
		</tr>
		<c:forEach items="${viewAll}" var="list">
			<tr onclick="chkAuth('${mem.email}','${list.receiptUserId}','${list.seq}')">
				<td>${list.seq}</td>
				<td>${list.receiptUserId}</td>
				<td>${list.lostRegdate}</td>
			</tr>
		</c:forEach>
	</table>
	
	<div style="display: block; text-align: center;">		
		<c:if test="${paging.startPage != 1 }">
			<a href="./pagingLostPropertyList.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != paging.nowPage }">
					<a href="./pagingLostPropertyList.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="./pagingLostPropertyList.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
		</c:if>
	</div>
	
	<div class="reportBtn">
		<button class="btn btn-info" onclick="location.href='./goMainPage.do'">메인으로</button>
	</div>
</div>
</body>
</html>