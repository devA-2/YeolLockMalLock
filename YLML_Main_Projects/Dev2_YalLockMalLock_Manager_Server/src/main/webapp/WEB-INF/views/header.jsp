<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
  
 	<div>
	<span id="cntUser">${mem.email}님 접속하셨습니다.</span>
	
	
	<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
<!--       <a class="navbar-brand" href="#">관리자메인페이지</a> -->
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="./managerMain.do">메인으로</a></li>
      
       <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">회원 관리 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="./allMember.do">회원조회</a></li>
        </ul>
      </li>
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">담당자 및 배송원 관리 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a id="acsDvBtn" onclick="accessDeliveryList()">담당자 및 배송원 전체조회</a></li>
<!--           <li><a href="#">Page 1-2</a></li> -->
        </ul>
      </li>
       <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">보관함 관리 <span class="caret"></span></a>
        <ul class="dropdown-menu">
         <li><a id="acsSlBtn" onclick="accessStorageList()">보관함 조회</a></li>
         <li><a id="acsSlRBtn" onclick="accessStorageRegist()">보관함 등록</a></li>
         </ul>
       </li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">게시판 관리 <span class="caret"></span></a>
        <ul class="dropdown-menu">
         <li><a href="#">게시판1</a></li>
         <li><a href="#">게시판2</a></li>
         </ul>
       </li>
    </ul>
    
    <ul class="nav navbar-nav">
      <li class="active">
      	<a id="logOutBtn" onclick="logOut()">로그아웃</a>
      </li>
    </ul>
  </div>
  <span id="cntUser">${mem.email}님 접속하셨습니다.</span>
  <script type="text/javascript">
  	// 권한 값 확인
  	var auth = "${mem.auth}";
  	console.log("현재 로그인된 권한 값 = "+auth);
  
	// 로그아웃
	function logOut() {
		var conFrm = confirm("로그아웃하시겠습니까?");
		if(conFrm == true){
			alert("로그아웃합니다. 처음페이지로 이동합니다.");
			sessionStorage.clear();
			location.href="index.do"
			}
	}
  </script>
</nav>
  

	
</div> 