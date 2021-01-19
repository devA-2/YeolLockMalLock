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
<style type="text/css">
#search {
	width: 358px;
}
.container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
   padding: 0px;
}
</style>

</head>
<body>
   <!-- kakaoMap API 고유키 설정 -->
   <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=17fa16b302b947b735f86c5f96eb39b1"></script>

      <div class="container">
		<!-- 검색창 div -->
         <div class="input-group">
            <input type="text" class="form-control" placeholder="보관함 검색" id="search">
<!-- 			<div class="input-group-btn"> -->
<!--                <button class="btn btn-default"> -->
<!--                   <i class="glyphicon glyphicon-search"></i> -->
<!--                </button> -->
<!--             </div> -->
         </div>
		<!-- 지도 표시하는 div  -->
        <div id="map" style="width:358px; height: 500px;"></div>
      </div>
<!-- 자동완성, map 자바스크립트파일 -> 지도 div 밑에 있어야함 -->
<script src="../js/map.js"></script>

결제완료후 흐름
<div>
	<form action="./afterPayment.do" method="post">
		<input type="text" name="costCode" placeholder="결제코드">
		<input type="submit">
	</form>
</div>
수령 사용자 이메일 입력하기
<div>
	<form action="./updateOutUser.do" method="get">
		<input type="text" name="storageId" value="CITYHALL_B">
		<input type="text" name="boxSeq" value="2">
		<input type="submit">
	</form>
</div>
</body>
</html>