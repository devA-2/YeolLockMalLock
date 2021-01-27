<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	#mainP{
		text-align: center;
	}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="./css/header.css">
<title>관리자메인페이지</title>
<script type="text/javascript" src="./js/managerAuth.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  
</head>
<body>
<div id="container">
<%@include file="./header.jsp" %>
	<br><br><br>
  <div id="mainP"><h1>관리자메인페이지 입니다.</h1>
  	<br>
  	<h1>상단의 메뉴를 이용해주세요.</h1>
  </div>	
  <%@include file="./footer.jsp" %>
</div>

</body>
</html>