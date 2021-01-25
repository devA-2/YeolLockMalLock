<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>마이페이지</title>
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

function myPageChk(){
		var pw = document.getElementById("pw").value;
		var result = "";
		
		if(pw == "" || pw.trim()== ""){
			document.getElementById("pw").focus();
			$("pw").val("");
			alert("비밀번호를 입력해 주세요");
		}else{
			$.ajax({
				type:"post",
				url:"./pwChk.do",
				data:"pw="+pw,
				success: function(isc){ 
					if(isc){
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
			<div>비밀번호: </div><input type="password" id="pw" name="pw" maxlength="20" size="20" value="Deli0101"><br><br>
			<input type="button" id="myPageChk" name="myPageChk" value="확인" onclick="myPageChk()">
	</div>	
</body>
</html>