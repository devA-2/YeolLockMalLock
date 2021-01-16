<%@page import="com.min.edu.dto.ReportDto"%>
<%@page import="com.min.edu.dto.MemberDto"%>
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
		location.href="./reportList.do";
	}
	function replyGo(seq){
		location.href='./replyReport.do?seq='+seq;
	}
</script>

</head>
<%
	Object obj = session.getAttribute("mem");
	MemberDto mem = (MemberDto)obj;
	
	Object obj2 = session.getAttribute("dto");
	ReportDto rDto = (ReportDto)obj2;
%>
<body>
	<div id="container">
		<table>
			<c:forEach items="${dto}" var="vo" varStatus="vs">
				<tr>
					<td>${vo.regdate}</td>
					<td>${vo.title}</td>
					<td>${vo.email}</td>
				</tr>
			</c:forEach>
		</table>
		<button onclick="historys()">뒤로 가기</button>
		<%
			
			if(Integer.parseInt(mem.getAuth()) == 20){
				%>
					<c:forEach items="${dto}" var="vo" varStatus="vs">
					<button onclick="replyGo(${vo.seq})">답변 글 작성</button>
					</c:forEach>
				<%
			}
			
		%>
	</div>
	<div>
<%-- 		<button onclick="replyGo(${rDto.seq})">답변 글 작성</button> --%>
	</div>
</body>
</html>
