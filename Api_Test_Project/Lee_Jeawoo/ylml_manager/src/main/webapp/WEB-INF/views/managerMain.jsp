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
	
  	var auth = ${mangerLogin.auth};
		
      function accessDeliveryList() {
		console.log(auth);
		if(auth != "90"){
			alert("해당 메뉴에 접근하실수 있는 권한이 없습니다.");
		}
		else {
			location.href="allDeleveryList.do";
		}
	} 
      
      function accessStorageList() {
    	  console.log(auth);
  		if(auth != "99"){
  			alert("해당 메뉴에 접근하실수 있는 권한이 없습니다.");
  		}
  		else {
  			location.href="allStorageList.do";
  		}
	}
      
      function accessStorageRegist() {
    	  console.log(auth);
  		if(auth != "99"){
  			alert("해당 메뉴에 접근하실수 있는 권한이 없습니다.");
  		}
  		else {
  			location.href="registStorage.do";
  		}
	}
  </script>
</head>
<body>
	managerMain.jsp<br>
	${mangerLogin.email}<br>
    ${mangerLogin.name}<br>
	${mangerLogin.auth}<br> 

	<div id="container">
	<a href="./index.do">처음으로</a><br><br>
	<p id="managerLogInfo">${mangerLogin.email}님 접속하셨습니다.</p>
	
	
	<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
<!--       <a class="navbar-brand" href="#">관리자메인페이지</a> -->
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
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