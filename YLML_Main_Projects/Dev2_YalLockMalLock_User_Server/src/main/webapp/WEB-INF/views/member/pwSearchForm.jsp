<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
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

//아작스로 이름과 아이디가 일치하는지 검사후 인증버튼을 활성화 시켜줌 
// 일치하는지 검사 후에는 readonly로 수정 못하게 해야함
// 인증하기 부분은 숨겨야함 

	//이메일 검사 정규식
	var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	
	
	$(document).ready(function() {
// 	$("#pwReserBtn").attr("disabled", true);
	$('#pwSearchBtn').click(function(){
		
		$.ajax({
			type: 'post',
			url : './pwSearch.do',
			data: 
			"name="+document.getElementById('name').value+"&email="+document.getElementById('email').value,
			success: function(data){
				console.log('Ajax for pwSearch : ' + data);
				if(data > 0){
					$("#pwReserBtn").removeAttr("disabled");
		        	$("#email").attr("readonly",true);
		        	$("#name").attr("readonly",true);
				}else{
					alert('해당하는 정보의 회원이 없습니다.')
					$("#pwReserBtn").attr("disabled", true);
				}
			}
			
			});
		});
	
		$("#email").blur(function() {
			if ($('#email').val() != '') {
				$("#pwSearchBtn").removeAttr("disabled");
			} 				
		});
	});	
</script>
</head>
<body>
	<div id="container">
		<h1>비밀번호 찾기</h1>
		<form action="./updatePwForm.do" id="pwSearchForm" method="post">
			<div>이름</div>
				<input type="text" id="name" name="name" required="required" value="윤기수"/>
			<div>아이디</div>
				<input type="text" id="email" name="email" size="40" maxlength="40"><br>
				<div class="checkFont" id="mailChk"></div>
			<input type="button" id="pwSearchBtn" name="pwSearchBtn" class="btn btn-success" value="정보확인">&nbsp;&nbsp;<br>
			<!-- 숨겨야해 -->
			<label>핸드폰 번호 : </label>
				<input type="text" name="phoneNum" id="phone_num" maxlength="11" size="15">
			<div class="check_font" id="phoneChk"></div>
				<input type="button" id="sendPhone_num" value="인증번호 전송"><br>
				<input type="text" id="certified_num" size="5">
				<input type="button" id="checkBtn" value="확인">
			<div class="time"></div>
			<input type="submit" id="pwReserBtn" value="비밀번호 초기화">	
		</form>
	</div>
</body>
</html>