<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">

<style type="text/css">
#container{
   width : 300px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>

<title>Test Index</title>
</head>
<body>
<div>
   <div id="container">
   <!-- 여기서부터 필요한 화면은 각자 작성 및 테스트 하시면 됩니다 -->
   <hr>
   <form action="./login.do" method="post" name="frm">
		<input type="text" value="USER02@NAVER.COM" name="email">
		<input type="password" value="PW1234" name="pw">
		<input type="submit" value="확인" >
	</form>
   <a href="#">회원가입</a><br>
   <hr>
   <a href="./reportList.do">신고 글 게시판</a><br>
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