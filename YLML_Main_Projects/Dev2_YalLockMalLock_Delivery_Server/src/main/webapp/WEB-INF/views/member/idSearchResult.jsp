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
#content{
	text-align: center;
}

h1.info {
	font-weight: 600;
	text-align: center;
}
a { 
	text-align: center;

}


</style>
<script type="text/javascript">
</script>
</head>
<body>
	<div id="container">
	<jsp:include page="../menu.jsp"/>
	<div id="content">
		<h1 class="info">아이디 찾기</h1><br><br>
		<h4>해당하는 정보의 아이디는  ${email}입니다.</h4><br>
		<a href="./loginForm.do">로그인으로 돌아가기</a>
	</div>
	</div>
</body>
</html>