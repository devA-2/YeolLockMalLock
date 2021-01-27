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


#cotnent{
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

h1.info{
	font-weight: 600;
	text-align: center;
}

#btn-group{
	text-align: center;
}
</style>
<body>
<!-- 휴대폰 인증 유효성 검사를 세션에 담아서 체크하는 거 구현해야함... 메일 보내는 것도.. -->
	<div id="container">
		<jsp:include page="../menu.jsp"/>
		<h1 class="info">회원가입</h1>
		<div id="content">
			<div id="signUp-form">
				<form action="./insertMember.do" id="joinChkFrm" method="post">
				<input type="hidden" name="auth" value="10">
					<label>테스트용 고유식별번호</label>
						<input type="text" name="idNum" size="15" maxlength="15"><br><br>
					<label>아이디: </label>
						${email }<br><br>
						<input type="hidden" id="email" name="email" value="${email}" >
					<label>비밀번호 : </label>
						<input type="password" id="pw" name="pw" class="form-control" maxlength="20" size="20">
						<div class="check_font" id="pwChk"></div>
					<label>비밀번호 확인: </label>
						<input type="password" id="pw2" name="pw2" class="form-control" maxlength="20" size="20">
						<div class="check_font" id="pw2Chk"></div>
					<div id="short-input">
						<label>이름: </label>
							${name }<br><br>
							<input type="hidden" name="name" value="${name}" >
						<label>핸드폰 번호 : </label>
							<input type="text" name="phoneNum" id="phone_num" class="form-control" maxlength="11" size="15">
						
							<div class="check_font" id="phoneChk"></div><br>
							<input type="button" id="sendPhone_num" class="btn btn-primary" value="인증하기"><br>
							
						<div id="checkSMS"><br>
							<input type="text" id="inputCertified_num" size="5" class="form-control"> 
						<div class="time"></div>
						<input type="button" id="checkBtn" class="btn btn-primary" value="확인">
						</div>
					</div><br>
					<div id="btn-group">
					<input type="submit" value="회원가입" id="signUpBtn" class="btn btn-info">	
					</div>
				</form>
			</div>
		</div>	
	</div>
</body>
</html>