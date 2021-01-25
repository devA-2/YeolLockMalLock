<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

#myPage-form{
  	width: 100%;  
  	margin-left: 19%;
  	margin-right: 16%; 
}
}

#btn-group {
	width: 100%;
 	text-align: center; 
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
	<h1 class="info">마이페이지</h1><br><br>
		<div id="content">
			<form>
				<div id="myPage-form">
					<label>아이디</label><br>
						<div>${mem.email }<input type="hidden" name="email" id="email" value="${mem.email}"/></div><br>
					<label>이름</label><br>
						<div>${mem.name }</div><br>
					<label>휴대폰번호</label><br>
						<div>${mem.phoneNum }</div><br>
					<label>가입일</label><br>
						<div>${mem.regDate }</div><br>
				</div><br><br>
				<div id="btn-group">
					<input type="button" class="btn btn-info" value="개인정보 수정" onclick="updateInfo()">
					<input type="button" class="btn btn-danger" value="회원탈퇴" id="quitMember">
				</div>
			</form>
		</div>
		<!-- 보관함 페이지의 보관내역 여기에 인클루드 할 예정 -->
	</div>
</body>
</html>