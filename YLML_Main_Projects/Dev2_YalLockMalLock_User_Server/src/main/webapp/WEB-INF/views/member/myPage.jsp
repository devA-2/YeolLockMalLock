<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
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
$(document).ready(function(){
	$('#quitMember').click(function(){
		
		$.ajax({
			type: 'post',
			data: {
			"mail" : document.getElementById('email').value
			},
			url : './usingCheck.do',
			
			success: function(data){
				console.log('Ajax for usingCheck : ' + data);
				if(data > 0){
					alert('현재 이용중인 서비스가 있으므로 탈퇴가 불가합니다. 서비스를 모두 이용 후 탈퇴해주세요.');
				}else{
					alert('회원탈퇴를 진행합니다.')
					location.href='./quitMember.do'
				}
			}
			
			});
		});
	});	

	function updateInfo(){
		location.href='./updateInfoForm.do'
		
	};
</script>
</head>
<body>
	<div id="container">
	<h1>마이페이지</h1>
		<form>
			<table border="1">
				<tr>
					<td>아이디 :</td>
					<td><div class="email">${mem.email } <input type="hidden" name="email" id="email" value="${mem.email}"/></div></td>
				</tr>	
				<tr>
					<td>이름 :</td>
					<td><div class="email">${mem.name }</div></td>
				</tr>	
				<tr>
					<td>핸드폰번호 :</td>
					<td><div class="email">${mem.phoneNum }</div></td>
				</tr>	
				<tr>
					<td>가입일 :</td>
					<td><div class="email">${mem.regDate }</div></td>
				</tr>	
			</table>
			<input type="button" value="개인정보 수정" onclick="updateInfo()">
			<input type="button" value="회원탈퇴" id="quitMember">
		</form>
		<!-- 보관함 페이지의 보관내역 여기에 인클루드 할 예정 -->
	</div>
</body>
</html>