<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/common.css">
<style type="text/css">
#info{
	padding: 15px;
	text-align: center;
}
#result{
	text-align: center;
}
#searchBar{
	width: 70%;
	text-align: center;
	display: flex;
	margin-left: 15%;
}
/* .form-control{ */
/* 	width: 50% */
/* } */
</style>
</head>
<body>
<script type="text/javascript">
function checkEmail(){
	var email = document.getElementById('searchOutUser').value;
// 	console.log(email)
	 $.ajax({
		  url : './checkOutUser.do',
		  type : 'get',
		  data : { "email" : email },
		  success : function(checkedEmail) {
// 			  console.log(checkedEmail)
// 			var result = document.getElementById('result').innerHTML;
			if(checkedEmail==''){
				document.getElementById('result').innerHTML = '해당 사용자가 없습니다. <br> 다시 입력해주세요'
			}else{
				document.getElementById('email').value = checkedEmail;
				document.getElementById('result').innerHTML = '사용 가능한 이메일입니다. <br> 해당 이메일로 키 전송 하시겠습니까? <br> <input type="submit" class="btn btn-default" value="전송">';	 				
			}
			  
		 },
		  error : function() {
			  console.log("잘못된 요청입니다");
		  }
	  });//ajax
}

function chkSubmit(){
// 	document.getElementById('email').value = 'no';
	if(document.getElementById('email').value==document.getElementById('searchOutUser').value){
		return true;
	}else{
		alert('이메일 확인해주세요');
		return false;
		
	}

}

</script>
	<div id='container'>
		<jsp:include page="../menu.jsp"/>
		<form method="post" action="./updateOutUser.do" onsubmit="return chkSubmit()">
			<div id="info">
			
			<h3><br><br><br>수령할 사용자의<br> 이메일을 입력해주세요</h3>
			</div>
			<hr>
			<div id="searchBar">
			<input type="hidden" name="storageId" value="${map.id }">
			<input type="hidden" name="boxSeq" value="${map.boxSeq }">
			<input type="hidden" name="email"  id="email" >
			<input type="text" class="form-control"  id="searchOutUser" > 
			<input type="button"  class="btn btn-info" value="검색" onclick="checkEmail()">
			</div>
			<hr>
				<div id="result"></div>

		</form>
	</div>
</body>
</html>