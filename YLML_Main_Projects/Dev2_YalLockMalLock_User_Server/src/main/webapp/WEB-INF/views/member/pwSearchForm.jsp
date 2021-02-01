<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
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
<script type="text/javascript" src="../js/pwSearch.js"></script>
<style type="text/css">

#pwSearch-form{
	width: 80%;
	margin-left: 10%; 
 	margin-right: 10%; 
	
}

#short-input{
	width: 80%;
	margin-right: 30%;
	margin-left: 10%; 

}

h1.info{
	font-weight: 600;
	text-align: center;
}

#btn-group{
	text-align: center;
}
</style>
</head>
<body>
	<div id="container">
	<jsp:include page="../menu.jsp"/>
		<h1 class="info">비밀번호 찾기</h1><br><br>
		<div id="content">
			<div id="pwSearchForm">
				<form action="./updatePwForm.do" id="pwSearchForm" method="post">
					<div id="short-input">
						<label>아이디</label><br>
							<input type="text" id="email" class="form-control" name="email" maxlength="40"><br>
							<div class="checkFont" id="mailChk"></div>
						<label>이름</label><br>
							<input type="text" id="name" class="form-control" name="name" required="required" maxlength="6" size="10"><br>
						<input type="button" id="pwSearchBtn" name="pwSearchBtn" class="btn btn-success" value="정보확인"><br>
						<div id="sendSMS">
							<label>휴대폰 번호 : </label>
							<input type="text" name="phoneNum" id="phone_num" class="form-control" maxlength="11" size="11"><br>
							<input type="button" id="sendPhone_num" class="btn btn-info" value="인증번호 전송"><br><br>
						</div>
						<div id="checkSMS">
							<input type="text" id="inputCertified_num" class="form-control" size="5">
							<div class="time"></div>
							<input type="button" id="checkBtn" class="btn btn-success" value="확인"><br>
						</div>
					</div>
					<div id="btn-group">
						<input type="submit" id="pwResetBtn" class="btn btn-info" value="비밀번호 초기화">	
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>