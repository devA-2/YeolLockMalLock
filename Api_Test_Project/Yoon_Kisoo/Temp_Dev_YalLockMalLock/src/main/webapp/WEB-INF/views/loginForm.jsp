<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
<style type="text/css">
#container{
   width : 300px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
<script type="text/javascript">	

function loginChk(){
	var email = document.getElementById("email").value;
	var pw = document.getElementById("pw").value;
	var frm = document.loginFrm;

	frm.action = "./login.do"	// Ajax에서 이동할 주소
	
	var result = "";
	
	if(email == "" || email.trim()==""){
		document.getElementById("email").focus();
		$("email").val("");
		alert("로그인", "아이디를 입력해 주세요");
	}else if(pw == "" || pw.trim()==""){
		document.getElementById("pw").focus();
		$("pw").val("");
		alert("로그인", "비밀번호를 입력해 주세요");
	}else{
		$.ajax({
			type:"post",
			url:"./loginCheckMap.do",
			data:"email="+email+"&pw="+pw,
			success: function(msg){ 
				if(msg.isc == "성공"){
					frm.submit();
				}else{
					alert("아이디 및 비밀번호가 틀립니다. 다시 시도해주세요.");
				}
			},
			error: function(){
				alert("로그인에 문제가 발생했습니다");
			}
		});
	}
}
</script>
</head>
<body>
	<!-- 정보를 수정 할 수 있는 건 현재 휴대폰 번호 뿐이지만, 추 후에 추가 될 수 있음 -->
	<div id="container">
		<h1>열락말락 로그인</h1>
		<form method="post" name="loginFrm">
			<div>아이디</div>
				<input type="text" name="email" id="email" required="required" value="user01@naver.com"/>
			<div>비밀번호</div>
				<input type="password" name="pw" id="pw" required="required" value="User010101" maxlength="20" size="20"><br>
			<a href="./idSearchForm.do">아이디 찾기</a> &nbsp;&nbsp;
			<a href="./infoAgree.do">비밀번호 찾기</a><br><br>
			
			<input type="button" id="login" name="login" class="btn btn-success" value="로그인" onclick="loginChk()">&nbsp;&nbsp;
		</form>
	</div>
</body>
</html>