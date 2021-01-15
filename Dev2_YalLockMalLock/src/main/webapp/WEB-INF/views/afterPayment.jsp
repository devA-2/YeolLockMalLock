<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<script type="text/javascript">
	function pickUp(form){
		if(confirm('회수가 완료 되었습니다. 반품하시겠습니까 ? 페이지 이동시 반품은 불가합니다.')){
			document.getElementById('returnFlag').value = 'Y';
		}
		form.submit();
	}

</script>
<div class="container">
	<form action="./afterPayment.do" method="post">
		<input type="hidden" name="costCode" value="${costCode}">
		<input type="hidden" id="returnFlag" name="returnFlag" value="N">
		<h1>결제가 완료되었습니다<br>
		물건을 회수 후 <br>
		<input type="button" class="btn btn-success" value="회수 완료" onclick="pickUp(this.form)"> <br>
		꼭 눌러주세요</h1>
	</form>
</div>

</body>
</html>