<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">

<style type="text/css">
#container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
   width: device-width;
}
</style>

<title>Test Index</title>
</head>
<body>
<div>
   <div id="container">
	   <!-- 여기서부터 필요한 화면은 각자 작성 및 테스트 하시면 됩니다 -->
	   <hr>
	   <h2>신고게시판 테스트용</h2>
	   <form action="./login.do" method="post" name="frm">
			<input type="text" value="USER02@NAVER.COM" name="email">
			<input type="password" value="PW1234" name="pw">
			<input type="submit" value="확인" >
		</form>
			   <h2>신고게시판 Admin 테스트용</h2>
	   <form action="./adminLogin.do" method="post" name="frm">
			<input type="text" value="ADMIN02" name="email">
			<input type="password" value="PW1234" name="pw">
			<input type="submit" value="확인" >
		</form>
		<h2>유실물게시판 테스트용</h2>
		<form action="./login2.do" method="post" name="frm">
			<input type="text" value="USER02@NAVER.COM" name="email">
			<input type="password" value="PW1234" name="pw">
			<input type="submit" value="확인" >
		</form>
	   <a href="#">회원가입</a><br>
	   <hr>
	   <a href="./adminReportList.do">신고 글 게시판</a><br>
	   <hr>
	   <a href="#">유실물 게시판</a><br>
	   <hr>
	   <a href="#">보관함</a><br>
	   <hr>
	   <a href="#">RFID/NFC</a><br>
	   <hr>
	   <a href="#">결제</a>
	   <hr>
   </div>
</div>
</body>
</html>