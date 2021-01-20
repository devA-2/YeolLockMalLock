<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
<title>관리자로그인</title>
<style type="text/css">
	#container{
		padding-left: 40%;
		padding-right: 40%;
		border: 1px solid gray;
	}
</style>
<script type="text/javascript">

	function loginCheck(){
		var email = document.getElementById("InputId").value; 
		var pw = document.getElementById("InputPw").value;
		var frm = document.frm;
		
		frm.action ="login.do"; 
		
		var result = "";
		
		if(email == "" || email.trim()==""){
			document.getElementById("InputId").focus();
			$("InputId").val("");
			alert("로그인","아이디를 입력해 주세요");
		}else if(pw == "" || pw.trim()==""){
			document.getElementById("InputPw").focus();
			$("InputPw").val("");
			alert("로그인","비밀번호를 입력해 주세요");
		}else{
			$.ajax({
				type:"post",
				url:"loginCheckMap.do", 
				data:"email="+email+"&pw="+pw,
				success: function(msg){ 
// 					alert(msg.isc);
					if(msg.isc == "성공"){
						frm.submit();
					}else{
						alert("로그인 실패","해당 사용자는 존재하지 않습니다.");
					}
				},
				error:function(){
					alert("로그인 실패","로그인에 문제가 발생 했습니다");
				}
			});
			
			
			
			
			
			
			
			
			
// 			frm.submit();
		}
		
		
	}
</script>
</head>
<body>
managerLogin.jsp<br>


<div id="container">
	<div id="title">Together Login</div>
	<div id="id">아이디</div>
	<form method="post" name="frm">
		<input type="hidden" id="loginChk" name="auth" value="0">
		<input type="text" name="email" id="InputId" value="admin@naver.com" placeholder="아이디입력"><br>
		<div id="pw">비밀번호</div>
		
		<input type="password" name="pw" id="InputPw" value="123qwe" onkeyup="enterKey()"><br>
		
		<input type="button" id="login" name="login" value="로그인"
		 onclick="loginCheck()"><br>
		
		<div id="bottom">
<!-- 			<input type="button" id="SearchId" name="SearchId" value="아이디찾기" onclick="idSearch()">  -->
<!-- 			<input type="button" id="SearchPw" name="SearchPw" value="비밀번호 찾기" onclick="pwSearch()"> -->
		</div>
	</form>
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