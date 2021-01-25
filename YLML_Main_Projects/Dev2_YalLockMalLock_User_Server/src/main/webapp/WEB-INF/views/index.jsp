<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="./css/common.css">
<style type="text/css">
.input-group {
	width: 100%;
	display: flex;
	z-index: 1;
}

#search {
	width: 86.6%;
	height: 48px;
}

.sidepanel {
	width: 0;
	position: fixed;
	z-index: 2;
	height: 250px;
	top: 0;
	left: 0;
	background-color: #5a5a5a;
	overflow-x: hidden;
	transition: 0.5s;
	padding-top: 60px;
}

.sidepanel a {
	padding: 8px 8px 8px 32px;
	text-decoration: none;
	font-size: 15px;
	color: #c3c4c5;
	display: block;
	transition: 0.3s;
}

.sidepanel a:hover {
	color: #ffffff;
}

.sidepanel .closebtn {
	position: absolute;
	top: 0;
	right: 25px;
	font-size: 36px;
}

.openbtn {
	width: 13.4%;
	font-size: 20px;
	cursor: pointer;
	background-color: #5a5a5a;
	color: white;
	padding: 10px 15px;
	border: none;
}

.openbtn:hover {
	background-color: #444;
}

#map {
	width: 100%;
	height: 480px;
}
</style>

</head>
<body>
   <!-- kakaoMap API 고유키 설정 -->
   <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=17fa16b302b947b735f86c5f96eb39b1"></script>

      <div id="container">

		<div id="mySidepanel" class="sidepanel">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
			<c:choose>
				<c:when test="${mem == null}">
					<a href="./member/loginForm.do">로그인</a> 
					<a href="./member/infoAgree.do">회원가입</a>
				</c:when>
				<c:otherwise>
					<a>${mem.name }님 안녕하세요.</a>
					<a href="./member/logout.do">로그아웃</a>
					<a href="./member/myPage.do">마이페이지</a>
					<a href="./storage/userStorageList.do">보관 조회</a> 
				</c:otherwise>
			</c:choose>
			<a href="./reportList.do">신고</a> 
		</div>
		<!-- 검색창 div -->
         <div class="input-group">
			<button class="openbtn" onclick="openNav()">☰ </button>  
            <input type="text" class="form-control" placeholder="보관함 검색" id="search">
         </div>
         
		<!-- 지도 표시하는 div  -->
        <div id="map" ></div>
        
      </div>
<!-- 자동완성, map 자바스크립트파일 -> 지도 div 밑에 있어야함 -->
<script src="./js/map.js"></script>

</body>
</html>