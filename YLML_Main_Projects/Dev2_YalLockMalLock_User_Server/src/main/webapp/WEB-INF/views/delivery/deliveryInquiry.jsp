<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>미리 조회</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/common.css"></head>
</head>
<style type="text/css">
	#search{
		width: 75%;
		display: flex;
		z-index: 1;
		position: absolute;
		left: 18%;
		top: 0;
	}
	#map {
		width: 100%;
		height: 300px;
		margin-top: 5%;
	}
	#station{
		font-size: 12px;
		margin-top: 5%;
	}
	#result{
		font-size: 12px;
		position: relative;
	}
	#result label{
		width: 20%;
	}
	#result span{
		width: 60%;
	}
	#btn{
		width:  100%;
		position: absolute;
		left: 25%;
		top: 85%;
	}
	#btn button{
		margin-left: 3%;
	}
	#msg{
		font-size: 8px;
		color: red;
	}
</style>
<body>
	<div id="container">
		<jsp:include page="../menu.jsp"/>
		<div id="content">
		
			<!-- 검색창 div -->
			<div class="input-group">
				<input type="text" class="form-control" placeholder="보관함 검색"
					id="search">
			</div>

			<!-- 지도 표시하는 div  -->
			 <div id="map" ></div>
			<!-- kakaoMap API 고유키 설정 -->
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=17fa16b302b947b735f86c5f96eb39b1"></script>
			<script type="text/javascript" src="../js/inquiryMap.js"></script>
			
			<div id="station">
				<label for="start">출발 : </label> <span id="start"></span><br>
				<label for="arrive">도착 : </label> <span id="arrive"></span>
			</div>
			<div id="result"></div>
			<div id="btn">
				<button class="btn btn-info" onclick="calTime()">계산</button>
				<button class="btn btn-info" onclick="reset()">다시 선택</button>
			</div>
		</div>
	</div>
</body>

</html>