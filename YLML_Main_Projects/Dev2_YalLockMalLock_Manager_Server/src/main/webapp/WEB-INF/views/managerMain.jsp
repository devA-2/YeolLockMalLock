
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


	<div id="container">
<!-- 	<a href="./index.do">로그아웃</a><br><br> -->
	<a onclick="logOut()">로그아웃</a><br><br>
	<p id="managerLogInfo">${mem.email}님 접속하셨습니다.</p>
	
	
	<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
<!--       <a class="navbar-brand" href="#">관리자메인페이지</a> -->
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      
       <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">회원 관리 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="./allMember.do">회원조회</a></li>
        </ul>
      </li>
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">담당자 및 배송원 관리 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a onclick="accessDeliveryList()">담당자 및 배송원 전체조회</a></li>
<!--           <li><a href="#">Page 1-2</a></li> -->
        </ul>
      </li>
       <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">보관함 관리 <span class="caret"></span></a>
        <ul class="dropdown-menu">
         <li><a onclick="accessStorageList()">보관함 조회</a></li>
         <li><a onclick="accessStorageRegist()">보관함 등록</a></li>
         </ul>
       </li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">게시판 관리 <span class="caret"></span></a>
        <ul class="dropdown-menu">
         <li><a href="./adminReportList.do">신고글 게시판</a></li>
         <li><a href="./lostPropertyReportList.do">유실물 게시판</a></li>
         </ul>
       </li>
    </ul>
  </div>
</nav>
  
<div class="container">
  <p><h1>관리자메인페이지 입니다.</h1></p>
</div>
	
</div>

</body>

</html>