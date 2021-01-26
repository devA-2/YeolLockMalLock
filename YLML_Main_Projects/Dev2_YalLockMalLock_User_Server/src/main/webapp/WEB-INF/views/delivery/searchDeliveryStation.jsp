<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>도착 지점 선택</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="../js/deliveryMap.js"></script>
<link rel="stylesheet" href="../css/common.css"></head>
<body>
	
	<div id="container">
		<jsp:include page="../menu.jsp"/>
		<div id="content">
			<!-- 검색창 div -->
			<div class="input-group">
				<input type="text" id="search" class="form-control" placeholder="보관함 검색">
			</div>
			
			<!-- 지도 표시하는 div  -->
			<div id="map"></div>
			<!-- kakaoMap API 고유키 설정 -->
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=17fa16b302b947b735f86c5f96eb39b1"></script>
			<script type="text/javascript" src="../js/deliveryMap.js"></script>

			<form action="./delivery.do" method="post">
				<div id="result"></div>
			</form>
		</div>
	</div>
</body>
</html>