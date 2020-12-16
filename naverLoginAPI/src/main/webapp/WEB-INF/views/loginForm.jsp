<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="./css/sweetalert.css">

<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="./js/sweetalert.min.js"></script>
<script type="text/javascript">
	window.onload = function(){
		document.getElementById('SignUp').onclick = function(){
			location.href="./signUpForm.do";
		}
	};

	function loginCheck(){
		var email = document.getElementById("InputEmail").value;
		var pw = document.getElementById("InputPw").value;
		var frm = document.frm;

		frm.action = "./login.do"
		
		var result = "";
		
		if(email == "" || email.trim()==""){
			document.getElementById("InputEmail").focus();
			$("InputEmail").val("");
			swal("로그인", "이메일를 입력해 주세요");
		}else if(pw == "" || pw.trim()==""){
			document.getElementById("InputPw").focus();
			$("InputPw").val("");
			swal("로그인", "비밀번호를 입력해 주세요");
		}else{
			$.ajax({
				type:"post",
				url:"./loginCheckMap.do",
				data:"email="+email+"&pw="+pw,
				success: function(msg){	
					if(msg.isc == "성공"){
						frm.submit();
					}else{
						swal("로그인", "해당 사용자는 존재하지 않습니다.");
					}
				},
				error: function(){
					swal("로그인","로그인에 문제가 발생했습니다");
				}
			});
		}
	}
</script>
</head>
<body>
	<div>
		<form method="post" name="frm">
			<div id="email">이메일</div>
			<input type="hidden" id="loginChk" name="auth" value="0">
			<input type="text" id="InputEmail" name="id" placeholder="아이디 입력"><br>
			
			<div id="pw">비밀번호</div>
			<input type="password" id="InputPw" name="pw" onkeyup="enterKey()"><br>
			
			<input type="button" id="login" name="login" value="로그인" onclick="loginCheck()"><br>
			
			<div id="button">
				<input type="button" id="SignUp" name="signup" value="회원가입">
			</div>
		</form>
	</div>
	<hr>
	
	<div>
	<a href="${url}">네이버 아이디로 로그인</a>
	</div>

<script type="text/javascript">
	function enterKey(){
		if(window.event.keyCode == 13){
			loginCheck();
		}
	}
</script>	
</body>
</html>