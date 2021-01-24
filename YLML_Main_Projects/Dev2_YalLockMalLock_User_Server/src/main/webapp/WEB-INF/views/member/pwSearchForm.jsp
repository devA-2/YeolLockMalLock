<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="../js/pwSearch.js"></script>
<style type="text/css">
#container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
</head>
<body>
	<div id="container">
		<h1>비밀번호 찾기</h1>
		<form action="./updatePwForm.do" id="pwSearchForm" method="post">
			<div>이름</div>
				<input type="text" id="name" name="name" required="required" value="admin" size="10"/>
			<div>아이디</div>
				<input type="text" id="email" name="email" size="30" maxlength="40" value="admin@naver.com"><br>
				<div class="checkFont" id="mailChk"></div><br>
			<input type="button" id="pwSearchBtn" name="pwSearchBtn" class="btn btn-success" value="정보확인">&nbsp;&nbsp;
			<div id="sendSMS">
				<label>핸드폰 번호 : </label>
				<input type="text" name="phoneNum" id="phone_num" maxlength="11" size="15">
					<div class="check_font" id="phoneChk"></div>
				<input type="button" id="sendPhone_num" value="인증번호 전송"><br>
			</div>
			<div id="checkSMS">
				<input type="text" id="certified_num" size="5">
				<input type="button" id="checkBtn" value="확인">
				<div class="time"></div>
			<input type="submit" id="pwResetBtn" value="비밀번호 초기화">	
			</div>
		</form>
	</div>
</body>
</html>