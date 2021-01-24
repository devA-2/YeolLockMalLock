<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
	
  	// 로그아웃
  	function logOut() {
  		sessionStorage.clear();
  		location.href="index.do"
	}
  
//   	var auth = "${mem.auth}";
  	var auth = "100";
		
      function accessDeliveryList() {
		console.log(auth);
		if(auth != "100"){
			alert("해당 메뉴의 접근권한이 없습니다.");
		}
		else {
			location.href="allDeleveryList.do";
		}
	} 
      
      function accessStorageList() {
    	  console.log(auth);
  		if(auth != "100"){
  			alert("해당 메뉴의 접근권한이 없습니다.");
  		}
  		else {
  			location.href="allStorageList.do";
  		}
	}
      
      function accessStorageRegist() {
    	  console.log(auth);
  		if(auth != "100"){
  			alert("해당 메뉴의 접근권한이 없습니다.");
  		}
  		else {
  			location.href="registStorage.do";
  		}
	}
  </script>
</head>
<body>
	현재페이지 : managerMain.jsp<br>
	접속ID : ${mem.email}<br>
        접속비번 : ${mem.pw}<br>
	접속권한 : ${mem.auth}<br> 

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
         <li><a href="#">게시판1</a></li>
         <li><a href="#">게시판2</a></li>
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