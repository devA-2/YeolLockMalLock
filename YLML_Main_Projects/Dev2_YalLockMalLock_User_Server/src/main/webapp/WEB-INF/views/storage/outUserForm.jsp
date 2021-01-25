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
			var result = document.getElementById('result').innerHTML;
			if(checkedEmail==''){
				document.getElementById('result').innerHTML = '해당 사용자가 없습니다. <br> 다시 입력해주세요'
			}else{
				document.getElementById('result').innerHTML = '사용 가능한 이메일입니다. <br> 해당 이메일로 키 전송 하시겠습니까? <br> <input type="submit" value="다음">';	 				
			}
			  
		 },
		  error : function() {
			  console.log("잘못된 요청입니다");
		  }
	  });//ajax
	
}

</script>
	<div id='container'>
		<form method="post" action="./updateOutUser.do">
			<h2>수령할 사용자를 이메일을 입력해주세요</h2>
			<input type="hidden" name="storageId" value="${map.id }">
			<input type="hidden" name="boxSeq" value="${map.boxSeq }">
			<input type="text" name='email' id="searchOutUser"> 
			<input type="button" value="입력" onclick="checkEmail()">
			<div>
				<div id="result"></div>
<!-- 				<input type="submit" value="다음"> -->
			</div>

		</form>
	</div>
</body>
</html>