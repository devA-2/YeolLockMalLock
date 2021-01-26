<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="../js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" href="../css/common.css"> -->
<style type="text/css">
html{
	width: 100%;
	height:100%;
	border: 1px solid black;
}
#container {
	width: 100%;
}

#content {
	width: 100%;
	margin-left: 25%; 
 	margin-right: 25%; 
}

#header {
 	width: 100%; 
 	margin-left: 15%; 
 	margin-right: 15%; 
}

</style>
<script type="text/javascript">	

function loginChk(frm){
	var email = document.getElementById("email").value;
	var pw = document.getElementById("pw").value;

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
			success: function(isc){ 
				console.log(isc)
				if(isc){
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
		<div id="header">
			<img src="../img/logoSmall.png" width="250px" height="200px">
		</div>
		<div id="content">
			<form method="post" name="loginFrm">
				<div id="loginId">아이디</div>
					<input type="text" name="email" id="email" required="required" value="user03@naver.com"/>
				<div id="loginPw">비밀번호</div>
					<input type="password" name="pw" id="pw" required="required" value="User0303" maxlength="20" size="20"><br>
				<a href="./idSearchForm.do">아이디 찾기</a> &nbsp;&nbsp;
				<a href="./pwSearchForm.do">비밀번호 찾기</a><br>
				<a href="./infoAgree.do"> 아직 회원이 아니신가요?</a><br><br>
				<input type="button" id="loginBtn" name="login" class="btn btn-info" style="width:25%; height:10%;" value="로그인" onclick="loginChk(this.form)">&nbsp;&nbsp;
			</form>
		</div>
	</div>
</body>
</html>