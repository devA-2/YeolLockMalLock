<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="../js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="../js/signUp.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/common.css">
</head>
<style type="text/css">

#header {
 	width: 100%; 
 	text-align: center;
}

#leftSpace{
	width: 5%;
	flaot: left;
}

#rightSpace{
	width: 5%;
	flaot: right;
}

#btnArea{
	text-align: center;
}

h1.info{
	font-weight: 600;
}

</style>
<body>
<!-- 인증하기 부분은 숨길것 -->
	<div id="container">
		<div id="header"><h1 class="info">회원가입</h1></div>
		<div id="leftSpace"></div>
		<div id="content">
			<form action="./insertMember.do" id="joinChkFrm" method="post">
					<input type="hidden" name="auth" value="89">
			테스트용 고유식별번호<input type="text" name="idNum" size="15" maxlength="15"><br><br>
				<label>아이디</label><br>
					<input type="text" id="email" name="email" size="30" maxlength="40"><br>
					<div class="checkFont" id="mailChk"></div>
				<label>비밀번호</label><br>
					<input type="password" id="pw" name="pw" maxlength="20" size="20"><br>
					<div class="check_font" id="pwChk"></div>
			
				<label>비밀번호 확인</label><br>
					<input type="password" id="pw2" name="pw2" maxlength="20" size="20"><br>
					<div class="check_font" id="pw2Chk"></div>
					
				<label>이름</label><br>
					<input type="text" name="name" maxlength="6" size="10"><br>
				<label>휴대폰 번호</label><br>
					<input type="text" name="phoneNum" id="phone_num" maxlength="11" size="11">
					<input type="button" id="sendPhone_num" class="btn btn-primary" value="인증번호 전송"><br>
				<div class="check_font" id="phoneChk"></div><br>
				<div id="checkSMS">	
					<input type="text" id="certified_num" size="5">
					<input type="button" id="checkBtn" class="btn btn-primary" value="확인"><br>
				<div class="time"></div>
				</div><br>
				<div id="btnArea">
				<input type="submit" value="회원가입완료" id="signUpBtn" class="btn btn-info">	
				</div>
			</form>
		</div>
		<div id="rightSpace"></div>
	</div>
</body>
</html>