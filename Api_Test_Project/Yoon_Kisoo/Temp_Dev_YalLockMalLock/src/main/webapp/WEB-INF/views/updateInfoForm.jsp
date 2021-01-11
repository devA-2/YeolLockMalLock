<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#container{
   width : 300px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
</head>
<body>
	<div id="container">
	<h1>마이페이지 수정</h1>
		<form action="./updateMember.do" method="post">
			<table border="1">
				<tr>
					<td>핸드폰번호 :</td>
					<td><input type="text" id="phone_num" name="phone_num"/>${mem.phone_num }</td>
				</tr>	
			</table>
			<input type="submit" value="개인정보 수정">
		</form>
		<!-- 보관함 페이지의 보관내역 여기에 인클루드 할 예정 -->
	</div>
</body>
</html>