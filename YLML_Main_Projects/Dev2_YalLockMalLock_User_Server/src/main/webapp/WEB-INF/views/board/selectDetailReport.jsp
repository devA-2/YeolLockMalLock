<%@page import="com.dev2.ylml.dto.ReportDto"%>
<%@page import="com.dev2.ylml.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/common.css">
<link rel="stylesheet" href="./css/boardCss.css">
<title>상세 글</title>
<script type="text/javascript">
	function goHome(){
		location.href="./pagingReportList.do";
	}
</script>

</head>
<body>
	<div class="container">
			<c:forEach items="${lists}" var="vo">
					<span>작성일 : ${vo.regdate}</span><br>
					<span>제목 : ${vo.title}</span><br>
					<span>작성자 : ${vo.email}</span><br>
					<span>내용 : ${vo.content }</span><br>
			</c:forEach>
	</div>
	<div class="divCentering">
		<button class="btn btn-info" onclick="goHome()">Home</button>
	</div>
</body>
</html>
