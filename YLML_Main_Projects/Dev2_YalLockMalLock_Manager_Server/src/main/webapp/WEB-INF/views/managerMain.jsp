<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="./css/header.css">
<title>관리자메인페이지</title>
<script type="text/javascript" src="./js/managerAuth.js"></script>
  
</head>
<body>
<div id="container">
<%@include file="./header.jsp" %>
<!-- 	현재페이지 : managerMain.jsp<br> -->
<%-- 	접속ID : ${mem.email}<br> --%>
<%--         접속비번 : ${mem.pw}<br> --%>
<%-- 	접속권한 : ${mem.auth}<br>  --%>

  <h1>관리자메인페이지 입니다.</h1>
</div>

</body>
</html>