<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/loginPage.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
<script type="text/javascript">	

function loginChk(frm){
	var email = document.getElementById("email").value;
	console.log(email);
	var pw = document.getElementById("pw").value;
	console.log(pw);
	
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
	<div id="container">
	<div id="loginBox">
    	<p id="headLine">열락말락 관리자용 접속</p>
	<form method="post" name="loginFrm">
    <div class="input-group">
      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
      <input type="text" id="email" name="email" class="form-control"  placeholder="Email" value="admin@naver.com" required="required">
    </div>
    <div class="input-group">
      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
      <input type="password"  id="pw" name="pw" class="form-control"  placeholder="Password" value="Admin0101" required="required"><br>
    </div>
    <br>
      <input type="button" id="login" name="login" class="btn btn-success" value="Login" onclick="loginChk(this.form)">
  </form>
  </div>
  </div>



</body>
</html>