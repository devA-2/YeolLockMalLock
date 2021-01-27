<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="../js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/common.css">
<style type="text/css">

#short-input{
	width: 80%;
	margin-right: 30%

}

#update-form{
	width: 80%;
	margin-left: 20%; 
	
}

#btn-group{
	text-align: center;
	margin-right: 10%;
}

h1.info{
	font-weight: 600;
	text-align: center;
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
		<h1 class="info">비밀번호 변경</h1><br><br>
		<div id="content">
			<div id="update-form">
				<form action="./updatePw.do" id="updatePwFrm" method="post">
					<div id="short-input">
						<input type="hidden" value="${email }" name="email">
						<label>변경 할 비밀번호</label>
						<input type="password" id="pw" name="pw" maxlength="20" size="20"><br>
						<div class="check_font" id="pwChk"></div><br>
						<label>비밀번호 확인 </label>
						<input type="password" id="pw2" name="pw2" maxlength="20" size="20"><br>
						<div class="check_font" id="pw2Chk"></div><br>
						<div id="btn-group">
							<input type="submit" id="updatePwBtn" class="btn btn-info" value="확인">
						</div>
					</div>	
				</form>
			</div>
		</div>
	</div>	
</body>
</html>