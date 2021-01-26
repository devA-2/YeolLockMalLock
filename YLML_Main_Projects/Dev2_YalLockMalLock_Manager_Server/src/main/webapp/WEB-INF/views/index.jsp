<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
	#container{
	width: 1280px;
	height: 720px;
	border: 1px solid gray; 
	}
	
	#loginBox{
	margin-top: 10%;	
	margin-left: 489px;
	border: 1px solid black;
	width: 300px;
	height:300px; 
	}

</style>
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
    	<p id="head">열락말락 관리자 로그인</p>
	<form method="post" name="loginFrm">
    <div class="input-group">
      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
      <input type="text" id="email" name="email" class="form-control"  placeholder="Email" value="admin@naver.com">
    </div>
    <div class="input-group">
      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
      <input type="password"  id="pw" name="password" class="form-control"  placeholder="Password" value="Admin0101"><br>
      <input type="button" id="login" name="login"  value="로그인" onclick="loginChk(this.form)">
    </div>
  </form>
  </div>
  </div>



	<!-- 정보를 수정 할 수 있는 건 현재 휴대폰 번호 뿐이지만, 추 후에 추가 될 수 있음 -->
<!-- 	<div id="container"> -->
<!-- 		<div id="loginBox"> -->
<!-- 		<p>열락말락 관리자 로그인</p> -->
<!-- 		<form method="post" name="loginFrm"> -->
<!-- 		<div class="input-group"> -->
<!-- 			<div>아이디</div> -->
<!-- 				<input type="text" name="email" id="email" required="required" value="admin@naver.com"/> -->
<!-- 			<div>비밀번호</div> -->
<!-- 				<input type="password" name="pw" id="pw" required="required" value="Admin0101" maxlength="20" size="20"><br> -->
<!-- 			<input type="button" id="login" name="login"  value="로그인" onclick="loginChk(this.form)">&nbsp;&nbsp; -->
<!-- 			</div> -->
<!-- 		</form> -->
<!-- 		</div> -->
<!-- 	</div> -->

</body>
</html>