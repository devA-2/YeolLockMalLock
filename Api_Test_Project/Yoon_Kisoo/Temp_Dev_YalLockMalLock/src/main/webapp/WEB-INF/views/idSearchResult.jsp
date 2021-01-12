<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
</head>
<body>
	<div id="container">
		<h1>아이디 찾기</h1>
			해당하는 정보의 아이디는  ${dto.email }입니다.<br>
			<a href="./loginForm.do">로그인으로 돌아가기</a>
	</div>
</body>
</html>