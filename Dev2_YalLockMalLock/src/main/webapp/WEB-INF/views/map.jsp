<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
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
</head>
<body>
   <!-- kakaoMap API 고유키 설정 -->
   <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=17fa16b302b947b735f86c5f96eb39b1"></script>

      <div class="container">
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
      </div>
<!-- 자동완성, map 자바스크립트파일 -> 지도 div 밑에 있어야함 -->
<script src="js/map.js"></script>

<div>
	결제눌렀을때 Test
	<form action="./compareKey.do" method="post">
		<input type="text" value="keykey" name="key">
		<input type="text" value="2" name="overTime">
		<input type="submit">
	</form>
</div>
<div>
	결제완료 후  Test
	<form action="./afterPayment.do" method="post">
		<input type="text" value="YMC10002" name="costCode">
		<input type="submit">
	</form>
</div>
<div>
	결제완료후 반품처리 했을때  Test
	<form action="./insertReturn.do" method="post">
		<input type="text" value="YMC10040" name="costCode">
		<input type="text" value="반품합니다" name="message">
		<input type="submit">
	</form>
</div>
</body>
</html>