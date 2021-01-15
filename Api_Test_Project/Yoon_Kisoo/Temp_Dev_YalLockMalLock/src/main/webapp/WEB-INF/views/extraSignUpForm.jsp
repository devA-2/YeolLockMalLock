<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="./js/signUp.js"></script>
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
<!-- 휴대폰 인증 유효성 검사를 세션에 담아서 체크하는 거 구현해야함... 메일 보내는 것도.. -->
	<div id="container">
		<h1>회원가입</h1>
		<form action="./insertMember.do" id="joinChkFrm" method="post">
			<label>아이디: </label>
				${email }<br>
				<input type="hidden" id="email" name="email" value="${email}" >
			<label>비밀번호 : </label>
				<input type="password" id="pw" name="pw" maxlength="20" size="20"><br>
				<div class="check_font" id="pwChk"></div>
			<label>비밀번호 확인: </label>
				<input type="password" id="pw2" name="pw2" maxlength="20" size="20"><br>
				<div class="check_font" id="pw2Chk"></div>
			<label>이름: </label>
				${name }<br>
				<input type="hidden" name="name" value="${name}" >
			<label>핸드폰 번호 : </label>
				<input type="text" name="phone_num" id="phone_num" maxlength="11" size="15">
			<div class="check_font" id="phoneChk"></div>
				<input type="button" id="sendPhone_num" value="인증번호 전송"><br>
				<input type="text" id="certified_num" value='인증번호' size="5">
				<input type="button" id="checkBtn" value="확인">
			<div class="time"></div>
			<input type="submit" value="회원가입완료" id="signUpBtn">	
		</form>
	</div>
</body>
</html>