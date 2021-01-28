<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보관정보 확인</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
#container{
	text-align: center;	
}
#storageInfo{
	padding: 7%;
}
#storageTime{
	padding: 7%;
}
#storageCost{
	padding: 7%;
}
#storageBtn{
	text-align: center;
 	padding: 7%; 
}
</style>
</head>
<body>
	<script type="text/javascript">
		window.onload = function() {

			var date = new Date();
			var sysdate = date.getFullYear() + '년 ' + (date.getMonth() + 1)
					+ '월 ' + date.getDate()+'일 ';
			var min = date.getMinutes();
			if(min<10){
				min = '0'+min;
			}
			
			var systime = date.getHours() + ' : ' + min;
			if (date.getHours() > 19) {
				var systime4 = '24 : 00';
			} else {
				var systime4 = date.getHours() + 4 + ' : ' + min;
			}
			document.getElementById('storageTime').innerHTML = 
				sysdate + ' ' + systime + '&nbsp&nbsp -> <br><h4>' + sysdate + ' ' + systime4+'</h4>';

		}
	</script>
	<div id="container">
	<jsp:include page="../menu.jsp"/>
		<div id="content"><br>보관 내용을 확인해주세요</div>
		<div id="storageInfo">
			<h4>${storageInfo.label } ${map.boxSeq }번 보관함</h4>
			<br> ${storageInfo.desc }
		</div>
		<hr>
		<div id="storageTime"></div>
		<hr>
		<div id="storageCost">
		기본 4시간
		<h4>2000원</h4>
		</div>
		<hr>
		<div id="storageBtn">
			<input type="button" value="보관"  class="btn btn-info" onclick="location.href='./NFCtagPage.do'">
			<input type="button" value="메인"  class="btn btn-default" onclick="location.href='../index.do'">
		</div>
	</div>
</body>
</html>