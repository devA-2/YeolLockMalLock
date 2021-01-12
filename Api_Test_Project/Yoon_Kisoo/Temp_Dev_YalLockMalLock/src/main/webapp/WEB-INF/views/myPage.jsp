<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<style type="text/css">
#container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
<script type="text/javascript">

	function updateInfo(){
		location.href='./updateInfoForm.do'
		
	}
	
	function quitMem(){
		location.href='./quitMember.do'
	}
	
</script>
</head>
<body>
	<div id="container">
	<h1>마이페이지</h1>
		<form>
			<table border="1">
				<tr>
					<td>아이디 :</td>
					<td><div class="email">${mem.email }</div></td>
				</tr>	
				<tr>
					<td>이름 :</td>
					<td><div class="email">${mem.name }</div></td>
				</tr>	
				<tr>
					<td>핸드폰번호 :</td>
					<td><div class="email">${mem.phone_num }</div></td>
				</tr>	
				<tr>
					<td>가입일 :</td>
					<td><div class="email">${mem.reg_date }</div></td>
				</tr>	
			</table>
			<input type="button" value="개인정보 수정" onclick="updateInfo()">
			<input type="button" value="회원탈퇴" onclick="quitMember()">
		</form>
		<!-- 보관함 페이지의 보관내역 여기에 인클루드 할 예정 -->
	</div>
</body>
</html>