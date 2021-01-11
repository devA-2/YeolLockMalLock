<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

</style>
<script type="text/javascript">

function myPageCheck(){
		var pw = document.getElementById("InputPw").value;
		var result = "";
		
		if(pw == "" || pw.trim()== ""){
			document.getElementById("InputPw").focus();
			$("InputPw").val("");
			alert("비밀번호를 입력해 주세요");
		}else{
			$.ajax({
				type:"post",
				url:"./pwChk.do",
				data:"pw="+pw,
				success: function(msg){ 
					if(msg.isc == "성공"){
						location.href="./myPage.do"
					}else{
						alert("비밀번호가 틀립니다. 다시 시도해주세요");
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
		<h1>마이페이지</h1>
			<div>비밀번호: </div><input type="password" id="InputPw" name="pw" maxlength="20" size="20"><br><br>
			<input type="button" id="myPageChk" name="myPageChk" value="확인" onclick="myPageCheck()">
	</div>	
</body>
</html>