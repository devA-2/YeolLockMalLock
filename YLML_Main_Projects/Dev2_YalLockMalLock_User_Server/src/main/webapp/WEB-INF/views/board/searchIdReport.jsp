<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./css/common.css">
<link rel="stylesheet" href="./css/boardCss.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>ID 검색 페이지</title>
<script type="text/javascript">
	function goDetail(refer){
		location.href="./selectDetailReport.do?refer="+refer;
	}
</script>
</head>
<body>
	<div class="container">
	
		<div style="text-align: center;">
			<span>${mem.email} 님에 대한검색 목록입니다.</span>
		</div>
		<br><br>
		<table class="table table-hover">
			<c:forEach items="${lists}" var="vo">
				<tr onclick="goDetail(${vo.refer})">
					<td>${vo.seq}</td>
					<td>${vo.regdate}</td>
					<td>${vo.title}</td>
					<td>${vo.email}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="reportBtn">
			<button class="btn btn-info" onclick="location.href='./pagingReportList.do'">전체 목록으로</button>
			<button class="btn btn-info" onclick="location.href = './insertReport.do'">신고 글 작성</button>
		</div>
	</div>
</body>
</html>