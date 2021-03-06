<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도착 지점 선택</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- kakaoMap API 고유키 설정 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=17fa16b302b947b735f86c5f96eb39b1"></script>
</head>
<body>
	<!-- 검색창 div -->
	<div class="input-group">
		<input type="text" class="form-control" placeholder="보관함 검색" id="search">
		<div class="input-group-btn">
			<button class="btn btn-default">
				<i class="glyphicon glyphicon-search"></i>
            </button>
		</div>
	</div>
	
	<!-- 지도 표시하는 div  -->
	<div id="map" style="width: 600px; height: 600px;"></div>	
	
	<form action="./delivery.do" method="post">
		<div id="result"></div>
	</form>
</body>
<script type="text/javascript" src="js/deliveryMap.js"></script>
</html>