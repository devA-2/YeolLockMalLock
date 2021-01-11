<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인 화면</title>
<link rel="stylesheet" type="text/css" href="./css/Login.css">
<link rel="stylesheet" type="text/css" href="./css/sweetalert.min.css">
<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="./js/sweetalert.min.js"></script>
<script type="text/javascript">
	function loginCheck(){
// 		alert("작동");

		var id = document.getElementById("InputId").value;
		var pw = document.getElementById("InputPw").value;
// 		alert(id+":"+pw);

		var frm = document.frm;
		alert(frm);
		
		frm.action = "./login.do"; // ajax에서 이동할 주소
		
		var result = "";
		
		if(id == "" || id.trim()==""){
			document.getElementById("InputId").focus();
			$("InputId").val("");
		}elst if(pw == "" || pw.trim() == ""){
			document.getElementById("InputPw").focus();
			$("InputPw").val("");
			swal("로그인", "비밀번호를 입력 해 주세요");
		}else{
			$.ajax({
				type:"post",
				url:"./loginCheckText.do",
				data:"id="+id+"&pw="+pw,
				success: function(msg){
					alert(msg);
				},
				error:function(){
					swal("로그인", "로그인에 문제가 발생 했습니다.");
				}
			})
		}
	}
</script>

</head>
<body>
	<!-- Ajax를 통해 로그인 처리 -->
<div id="container">
	<div id="title">Together Login</div>
	<div id="id">아이디</div>
	<form method="post" name="frm">
		<input type="hidden" id="loginChk" name="auth" value="0">
		<input type="text" name="id" id="InputId" required="required"><br>
		<div id="pw">비밀번호</div>
		
		<input type="password" name="pw" id="InputPw" required="required">
		<input type="button" id="login" name="login" value="로그인" onclick="loginCheck()">
		
		<div id="bottom">
			<a href="#" onclick="signUp()">
				<input type="button" id="SignUp" name="signup" value="회원가입">
			</a>
			<input type="button" id="SearchId" name="SearchId" value="아이디 찾기" onclick="idSearch()">
			<input type="button" id="SearchPw" name="SearchPw" value="비밀번호 찾기" onclick="pwSearch()">
		</div>
	</form>
</div>

</body>
</html>