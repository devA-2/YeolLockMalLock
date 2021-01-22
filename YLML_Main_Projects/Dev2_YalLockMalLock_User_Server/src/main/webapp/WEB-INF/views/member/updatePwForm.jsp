<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-3.5.1.js"></script>
<style type="text/css">
#container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
<script type="text/javascript">

var pwJ = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;

$(document).ready(function(){
var pwChk1 = false;
var pwChk2 = false;

	// 비밀번호 유효성 검사
	$('#pw').blur(function() {
		if (pwJ.test($('#pw').val())) {
			console.log('true');
			$('#pwChk').text('');
			pwChk1 = true;

		} else {
			console.log('false');
			$('#pwChk').text('영문 대소문자와 숫자를 혼용하여 8자 이상 입력해주세요.');
			$('#pwChk').css('color', 'red');
			pwChk1 = false;

		} // else if

	}); // blur (비밀번호 유효성 검사)

	// 비밀번호 일치 확인	
	$("#pw2").blur(function(){
		if(pwChk1){
			if($('#pw').val() == $(this).val()){
				$('#pw2Chk').text('');
				pwChk2 = true;
			}else {
				$('#pw2Chk').text('비밀번호가 일치하지 않습니다.');
				$('#pw2Chk').css('color', 'red');
				pwChk2 = false;
			}// else if
		}
	}); // blur
	
	
	$('#updatePwFrm').submit(function() {
		if(pwChk1 && pwChk2){
			return true;
		}
		alert('다시 시도해주세요.')
		return false;
	});
});
	
</script>
</head>
<body>
	<!-- 이미 마이페이지 진입시 패스워드 일치여부 확인 했으므로, 현재 비밀번호는 입력하지 않음 -->
	<div id="container">
		<h1>비밀번호 변경</h1>
		<form action="./updatePw.do" id="updatePwFrm" method="post">
			<input type="hidden" value="${email }" name="email">
			<div>변경 할 비밀번호: </div><input type="password" id="pw" name="pw" maxlength="20" size="20"><br>
			<div class="check_font" id="pwChk"></div>
			<div>비밀번호 확인: </div><input type="password" id="pw2" name="pw2" maxlength="20" size="20"><br>
			<div class="check_font" id="pw2Chk"></div>
			<input type="submit" id="updatePwBtn" value="확인">
		</form>
	</div>	
</body>
</html>