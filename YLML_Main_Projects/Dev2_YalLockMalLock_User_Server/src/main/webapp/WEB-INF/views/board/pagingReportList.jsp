<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>신고글 게시판 목록</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="./css/common.css">
</head>
<script>
function chkAuth(memEmail, voEmail, auth, refer){
		if (memEmail == voEmail) {
			location.href="./selectDetailReport.do?refer="+refer;
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
			<form action="./searchIdReport.do" method="post">
				<input id="searchInput" class="inputForm" type="text" value="USER02@NAVER.COM" name="email">
				<input id="searchBtn" class="btn btn-info"" type="submit" value="검색">
			</form>
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
	</div> <!-- 옵션선택 끝 -->
	<table class="table table-hover">
		<tr>
			<th>SEQ</th>
			<th>작성 일자</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
	<c:forEach items="${viewAll}" var="list">
	<tr onclick="chkAuth('${mem.email}', '${list.email}', '${mem.auth}', '${list.refer}')">
		<td>${list.seq}</td>
		<td>${list.regdate}</td>
		<td>${list.title}</td>
		<td>${list.email}</td>
	</tr>
</c:forEach>
	</table>
	
	<div style="display: block; text-align: center;">		
		<c:if test="${paging.startPage != 1 }">
			<a href="./pagingReportList.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != paging.nowPage }">
					<a href="./pagingReportList.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="./pagingReportList.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
		</c:if>
	</div>
	<div class="reportBtn">
		<button class="btn btn-info" onclick="location.href = './insertReport.do'">신고 글 작성</button><br>
	</div>
	
	
</div>
</body>
</html>