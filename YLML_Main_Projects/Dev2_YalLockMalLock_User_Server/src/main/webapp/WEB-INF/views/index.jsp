<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="./css/common.css">
<style type="text/css">
#search {
	width: 86.6%;
	height: 48px;
}

#map {
	width: 100%;
	height: 480px;
	z-index: 1;
}

.menu{
	width:100%;
	display: flex;
}
</style>

</head>
<body>
   <!-- kakaoMap API 고유키 설정 -->
   <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=17fa16b302b947b735f86c5f96eb39b1"></script>

      <div id="container">
		<!-- 검색창 div -->
         <div class="menu">
			<jsp:include page="./menu.jsp"/>
            <input type="text" class="form-control" placeholder="보관함 검색" id="search">
         </div>
         
		<!-- 지도 표시하는 div  -->
        <div id="map" ></div>
        
      </div>
<!-- 자동완성, map 자바스크립트파일 -> 지도 div 밑에 있어야함 -->
<script src="./js/map.js"></script>

</body>
</html>