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
		<form action="./idSearch.do" method="post" name="idSearchForm">
			<div id=>이름</div>
				<input type="text" name="name" required="required" value="기수"/>
			<div id="pw">휴대폰 번호</div>
				<input type="text" name="phone_num" id="phone_num" required="required" value="01026169251" maxlength="20" size="20"><br>
			
			<input type="submit" id="login" name="login" class="btn btn-success" value="해당정보로 아이디 찾기">&nbsp;&nbsp;
		</form>
	</div>
</body>
</html>