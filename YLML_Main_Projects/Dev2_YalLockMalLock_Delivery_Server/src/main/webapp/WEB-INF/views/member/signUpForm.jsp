<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="../js/signUp.js"></script>
</head>
<style type="text/css">
#container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
<body>
<!-- 인증하기 부분은 숨길것 -->
	<div id="container">
		<h1>회원가입</h1>
		<form action="./insertMember.do" id="joinChkFrm" method="post">
		<input type="hidden" name="auth" value="89">
		테스트용 고유식별번호<input type="text" name="idNum" >
			<label>아이디 : </label>
				<input type="text" id="email" name="email" size="40" maxlength="40"><br>
				<div class="checkFont" id="mailChk"></div>
			<label>비밀번호 : </label>
				<input type="password" id="pw" name="pw" maxlength="20" size="20"><br>
				<div class="check_font" id="pwChk"></div>
		
			<label>비밀번호 확인: </label>
				<input type="password" id="pw2" name="pw2" maxlength="20" size="20"><br>
				<div class="check_font" id="pw2Chk"></div>
			<label>이름 :</label>
				<input type="text" name="name" maxlength="6" size="10"><br>
		
			<label>핸드폰 번호 : </label>
				<input type="text" name="phoneNum" id="phone_num" maxlength="11" size="15">
			<div class="check_font" id="phoneChk"></div>
				<input type="button" id="sendPhone_num" value="인증번호 전송"><br>
				<input type="text" id="certified_num" size="5">
				<input type="button" id="checkBtn" value="확인">
			<div class="time"></div>
			<input type="submit" value="회원가입완료" id="signUpBtn">	
		</form>
	</div>
		<script>
</script>
</body>
</html>