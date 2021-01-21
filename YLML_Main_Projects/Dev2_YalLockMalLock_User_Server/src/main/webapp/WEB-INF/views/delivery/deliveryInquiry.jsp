<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>미리 조회</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- kakaoMap API 고유키 설정 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=17fa16b302b947b735f86c5f96eb39b1"></script>
</head>
<style type="text/css">
	.container {
		width: 360px;
		height: 600px;
		border: 1px solid black;
		margin: auto;
		padding: 0px;
	}
	
	.content {
		width: 100%;
		height: 100%;
		margin-top: 80px;
		padding: auto;
	}
	
	.input-group {
		width: 100%;
	}
	
	.map{
		width: 100%;
		height: 50%;
		margin-top: 10px;
		margin-bottom: 10px;
	}
</style>
<body>
	<div class="container">
		<div class="content">
		
			<!-- 검색창 div -->
			<div class="input-group">
				<input type="text" class="form-control" placeholder="보관함 검색"
					id="search">
			</div>

			<!-- 지도 표시하는 div  -->
			<div id="map" class="map"></div>

			<div>
				<div>
					<label for="start">출발 : </label> <span id="start"></span>
				</div>
				<div>
					<label for="arrive">도착 : </label> <span id="arrive"></span>
				</div>
			</div>
			<div id="result"></div>
			<div>
				<button onclick="calTime()">계산</button>
				<button onclick="reset()">다시 선택</button>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="../js/inquiryMap.js"></script>
</html>