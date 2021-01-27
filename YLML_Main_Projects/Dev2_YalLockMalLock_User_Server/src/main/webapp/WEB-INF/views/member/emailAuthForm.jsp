<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
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
</head>
<style type="text/css">
h1.info{
	font-weight: 600;
	text-align: center;
}
#content{
	text-align: center;
}
.form-control{
	width : 50%;
	
}
#authBar{
/* 	text-align:center; */
/* 	margin:auto; */
	padding-left:20%;
	display: flex;
	width: 100%;
	display : none;
}
</style>
<script type="text/javascript">
function sendCodeToMail(){
	  $.ajax({
		  url : './sendCodeToMail.do',
		  type : 'get',
		  success : function() {
				alert('메일 전송!');
				document.getElementById('authBar').style.display='flex';
		  },
		  error : function() {
			  alert("인증 메일 보내기 실패 했습니다.");
		  }
	  });//ajax
}
</script>
</head>
<body>
	<div id="container">
	<jsp:include page="../menu.jsp"/>
	<h1 class="info"><br><br>이메일 인증폼<br><br></h1>
		<div id="content">
			<input type="button" class="btn btn-default" name="sendMailBtn" onclick='sendCodeToMail()' value="${mem.email }로 인증 메일 보내기">
			<hr>

			<form action="./checkCode.do" method="post">
			<div id="authBar">
			<input type="text" name="code" class="form-control" placeholder="인증번호">
			<input type="submit" class="btn btn-info" name="checkCodeBtn" value="인증">
			</div>
		</form>
		</div>
	</div>
</body>
</html>