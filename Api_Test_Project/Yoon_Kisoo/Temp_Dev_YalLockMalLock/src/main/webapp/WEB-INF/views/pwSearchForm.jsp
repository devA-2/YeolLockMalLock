<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
<style type="text/css">
#container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
<script type="text/javascript">

//아작스로 이름과 아이디가 일치하는지 검사후 인증버튼을 활성화 시켜줌 
// 일치하는지 검사 후에는 readonly로 수정 못하게 해야함
// 인증하기 부분은 숨겨야함 

	//이메일 검사 정규식
	var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

	$(document).ready(function() {
		$("#pwSearchBtn").attr("disabled", true);
		$("#email").blur(function() {

			if ($('#email').val() != '') {
				$("#pwSearchBtn").removeAttr("disabled");
			} 				
		});

		function sendCodeToMail() {
			location.href = './sendCodeToMail.do'
		}
	});
</script>
</head>
<body>
	<div id="container">
		<h1>비밀번호 찾기</h1>
		<form action="./checkCode.do" method="post">
			<div>이름</div>
				<input type="text" name="name" required="required" value="윤기수"/>
			<div>아이디</div>
				<input type="text" id="email" name="email" size="40" maxlength="40">
				<div class="checkFont" id="mailChk"></div>
			<input type="button" id="pwSearchBtn" name="pwSearchBtn" class="btn btn-success" value="이메일 전송" onclick="sendCodeToMail()">&nbsp;&nbsp;
			<!-- 숨겨야해 -->
			<div>인증번호 입력</div>
			<input type="text" name="code"><br>
			<input type="button" id="checkCodeBtn" name="checkCodeBtn" class="btn btn-success" value="인증하기 " onclick="sendMail()">&nbsp;&nbsp;
		</form>
	</div>
</body>
</html>