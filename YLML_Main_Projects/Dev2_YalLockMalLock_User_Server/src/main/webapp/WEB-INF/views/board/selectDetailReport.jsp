<%@page import="com.dev2.ylml.dto.ReportDto"%>
<%@page import="com.dev2.ylml.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">

<title>상세 글</title>
<style type="text/css">
	#container{
		width: 600px;
		height: 300px;
 		border: 1px solid black; 
		text-align: center;
		margin: auto;
	}
	
</style>
<script type="text/javascript">
	function goHome(){
		location.href="./reportList.do?";
	}
</script>

</head>
<body>
	<div style="width: 360px; height: 600px; border: 1px solid black; margin:auto;">
			<c:forEach items="${lists}" var="vo">
					<span>작성일 : ${vo.regdate}</span><br>
					<span>제목 : ${vo.title}</span><br>
					<span>작성자 : ${vo.email}</span><br>
					<span>내용 : ${vo.content }</span><br>
			</c:forEach>
		<button onclick="goHome()">Home</button>
</body>
</html>
