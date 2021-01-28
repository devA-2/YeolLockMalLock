<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/common.css">
<style type="text/css">
#content{
	padding: 5%;
	text-align: center;
}

</style>
</head>
<body>
	<script type="text/javascript">
		function pickUp(form) {
			if (confirm('회수가 완료 되었습니다. 반품하시겠습니까 ? 페이지 이동시 반품은 불가합니다.')) {
				document.getElementById('returnFlag').value = 'Y';
				form.submit();
			}else{
				form.submit();
			}
		}
	</script>
	<div id="container">
		<div id="content">
			<form action="./successPayment.do" method="post">
				<input type="hidden" id="returnFlag" name="returnFlag" value="N">
				<h2>
					<br><br><br><br>결제가 완료되었습니다<br></h2>
					 물건을 회수 후 '회수완료'를 클릭해야<br> 사용완료 처리가 됩니다<hr>
					<input	type="button" class="btn btn-info" value="회수 완료"	onclick="pickUp(this.form)"> <br> 
			</form>
		</div>
	</div>
</body>
</html>