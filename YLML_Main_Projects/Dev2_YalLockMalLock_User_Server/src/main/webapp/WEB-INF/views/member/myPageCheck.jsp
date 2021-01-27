<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="../js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/common.css">
<style type="text/css">
h1.info{
	font-weight: 600;
	text-align: center;
}

#myPageChk-Form{
	width: 60%;
	margin-left: 20%; 
 	margin-right: 15%; 	
}

#btn-group {
	width: 100%;
	text-align: center;
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
	<jsp:include page="../menu.jsp"/>
		<h1 class="info">마이페이지</h1><br><br>
		<div id="myPageChk-Form">
			<label>비밀번호</label>
			<input type="password" id="pw" class="form-control" name="pw" maxlength="20" size="20" value="User010101"><br><br>
		</div>
		<div id="btn-group">
			<input type="button" id="myPageChk" class="btn btn-info" name="myPageChk" value="확인" onclick="myPageChk()">
		</div>
	</div>	
</body>
</html>