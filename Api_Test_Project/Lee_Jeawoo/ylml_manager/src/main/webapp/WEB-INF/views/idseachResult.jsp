<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 검색결과</title>
</head>
<body>
idseachResult.jsp<br>
${searchResult}

<!-- <script type="text/javascript"> -->

<!-- 컨트롤러에서 model로 넘어온 값(list)의 null 판단 -->
<!-- null이 아니라면 값을 집어넣어 주고 null이라면 alert을 띄운다 -->


<!-- </script> -->
	<c:if test=""></c:if>
	



<hr>
<input style="font-size: large;" type="button" value="돌아가기" onclick="history.back(-1)">
<%-- <p><a title="${list}" href="#">id 검색 결과 : ${list}</a></p> --%>
</body>
</html>