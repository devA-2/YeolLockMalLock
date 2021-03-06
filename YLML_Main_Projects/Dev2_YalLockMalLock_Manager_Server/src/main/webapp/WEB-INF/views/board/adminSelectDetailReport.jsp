<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="./css/common.css">
<title>상세 글</title>
<script type="text/javascript">
	function historys(){
		location.href="./adminReportList.do";
	}
	
	function replyGo(seq){
		location.href='./selectDetailGoReply.do?seq='+seq;
	}
</script>

</head>
<body>
<!-- 	<div id="container"> -->
<!-- 		<table> -->
<%-- 			<c:forEach items="${dto}" var="vo" varStatus="vs"> --%>
<!-- 				<tr> -->
<%-- 					<td>${vo.regdate}</td> --%>
<%-- 					<td>${vo.title}</td> --%>
<%-- 					<td>${vo.email}</td> --%>
<!-- 				</tr> -->
<%-- 				<c:choose> --%>
<%-- 					<c:when test="${vo.step eq 0}"> --%>
<%-- 						<button id="replyBtn" onclick="replyGo(${vo.seq})">답변 글 작성</button> --%>
<%-- 					</c:when> --%>
<%-- 				</c:choose> --%>
<%-- 			</c:forEach> --%>
<!-- 		</table> -->

<%-- 		<button onclick="replyGo(${vo.seq})">답변 글 작성</button> --%>
<%-- 		<button onclick="replyGo(${rDto.seq})">답변 글 작성</button> --%>
<!-- 	</div> -->
<div id="container">
	<div>
		<table class="table table-hover">
			<c:forEach items="${dto}" var="vo" varStatus="vs">
				<c:choose>
					<c:when test="${vo.step eq 0}">
						<button id="replyBtn" class="btn btn-primary" onclick="replyGo(${vo.seq})">답변 글 작성</button>
					</c:when>
				</c:choose>
				<br><br>
				<tr>
					<td>작성일 : ${vo.regdate}</td>
					<td>작성자 : ${vo.email}</td>
					<td>제목 : ${vo.title}</td>
					<td>내용 : ${vo.content}</td>
				</tr>
			</c:forEach>
		</table>
		<div>
			<button class="btn btn-primary" onclick="historys()">뒤로 가기</button>
		</div>
	</div>
</div>
</body>
</html>
