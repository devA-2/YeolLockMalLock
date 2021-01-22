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
	function historys(){
		window.history.back();
	}
</script>

</head>
<body>
	<div style="width: 360px; height: 600px; border: 1px solid black; margin:auto;">
		<table>
			<c:forEach items="${lists}" var="vo">
				<tr>
					<td>${vo.regdate}</td>
					<td>${vo.title}</td>
					<td>${vo.email}</td>
				</tr>
				<tr>
					<td>${vo.content }</td>
				</tr>
			</c:forEach>
		</table>
		<button onclick="historys()">뒤로 가기</button>
</body>
</html>
