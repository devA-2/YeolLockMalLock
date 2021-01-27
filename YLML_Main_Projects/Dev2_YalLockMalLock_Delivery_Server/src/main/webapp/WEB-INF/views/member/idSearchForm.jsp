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
<style type="text/css">

#idSearch-form{
	width: 80%;
	margin-left: 10%; 
 	margin-right: 10%; 
	
}

#short-input{
	width: 80%;
	margin-right: 30%;
	margin-left: 20%;

}

h1.info{
	font-weight: 600;
	text-align: center;
}

#btn-group{
	text-align: center;
}

</style>
<script type="text/javascript">
var result=${fromIdSearch};
if(result!='true'){
	console.log(result)
 alert('해당하는 정보의 아이디가 없습니다.');
}
</script>
</head>
<body>
	<div id="container">
	<jsp:include page="../menu.jsp"/>
		<h1 class="info">아이디 찾기</h1><br><br>
		<div id="content">
			<div id="idSearchForm">
				<form action="./idSearch.do" method="post" name="idSearchForm">
					<div id="short-input">
						<label>이름</label><br>
							<input type="text" name="name" required="required"/><br>
						<label>휴대폰 번호</label><br>
							<input type="text" name="phoneNum" id="phone_num" required="required" maxlength="20" size="20"><br>
						</div>
					<div id="btn-group"><br><br>
					<input type="submit" id="login" name="login" class="btn btn-success" value="해당정보로 아이디 찾기">&nbsp;&nbsp;
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>