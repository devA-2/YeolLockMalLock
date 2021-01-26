<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="../js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="../js/updateMypage.js"></script>
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

#update-form{
	width: 80%;
	margin-left: 10%; 
 	margin-right: 10%; 
}

#short-input{
	width: 80%;
	margin-right: 30%
}

#btn-group{
	text-align: center;
}

</style>
</head>
<body>
	<div id="container">
	<jsp:include page="../menu.jsp"/>
	<h1 class="info">마이페이지 수정</h1><br>
		<div id="content">
			<div class="update-form">
				<form action="./updateInfo.do" method="post">
					<label>변경 할 핸드폰 번호 </label>
						<div id=short-input>
							<input type="text"  name="phoneNum" id="phone_num" class="form-control" maxlength="11" size="11">
								<div class="check_font" id="phoneChk"></div><br>
							<input type="button" id="sendPhone_num" class="btn btn-info" value="인증번호 전송"><br><br>
								<div id="checkSMS">
							<input type="text" id="inputCertified_num" class="form-control" size="5"><br>
							<input type="button" id="checkBtn" class="btn btn-primary" value="확인"><br><br>
							<div class="time"></div>
						</div>
					</div>
					<div id="btn-group">
					<input type="submit" value="개인정보 수정" id="updateInfoBtn" class="btn btn-info" >	
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>