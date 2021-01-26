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

#signUp-form{
	width: 80%;
	margin-left: 10%; 
 	margin-right: 10%; 
	
}

#short-input{
	width: 80%;
	margin-right: 30%

}

#btnArea{
	text-align: center;
}

h1.info{
	font-weight: 600;
}

</style>
<body>
	<div id="container">
		<div id="header"><h1 class="info">회원가입</h1></div>
		<div id="content">
			<div id="signUp-form">
				<form action="./insertMember.do" id="joinChkFrm" method="post">
				
						<input type="hidden" name="auth" value="19">
						
				테스트용 고유식별번호<input type="text" name="idNum" size="15" maxlength="15"><br>
				
					<label>아이디</label><br>
						<input type="text" id="email" class="form-control" name="email" size="30" maxlength="40">
						<div class="checkFont" id="mailChk"></div>
						
					<label>비밀번호</label><br>
						<input type="password" id="pw" class="form-control" name="pw" maxlength="20" size="20">
						<div class="check_font" id="pwChk"></div>
				
					<label>비밀번호 확인</label><br>
						<input type="password" id="pw2" class="form-control" name="pw2" maxlength="20" size="20">
						<div class="check_font" id="pw2Chk"></div>
						
					<div id="short-input">
					<label>이름</label><br>
						<input type="text" class="form-control" name="name" maxlength="6" size="10">
						
					<label>휴대폰 번호</label><br>
						<input type="text" name="phoneNum" id="phone_num" class="form-control" maxlength="11" size="11">
						
					<div class="check_font" id="phoneChk"></div><br>
						<input type="button" id="sendPhone_num" class="btn btn-primary" value="인증하기"><br>
						
					<div id="checkSMS"><br>
						<input type="text" id="certified_num" class="form-control" size="5">
						<div class="time"></div>
						<input type="button" id="checkBtn" class="btn btn-primary" value="확인">
					</div>
					
					</div><br>
					<div id="btnArea">
					<input type="submit" value="회원가입" id="signUpBtn" class="btn btn-info">	
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>