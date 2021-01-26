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
	text-align: center;
	padding : 10%;
}
</style>
</head>
<body>
<script type="text/javascript">
function tag(){
	var email = document.getElementById('email').value;
	var tag = document.getElementById('tag').value;
	var idNum = document.getElementById('idNum').value;

		$.ajax({
			url : './storage/NFCtag.do',
			type : 'post',
			data : {
				'email' : email,
				'tag' : tag,
				'idNum' : idNum
			},
			success : function(result) {
				console.log(result);
			},
			error : function() {
				console.log("ajax 오류");
			}
		});//ajax

	}
</script>
	<!-- 	태그 절차 수정해야함  -->
	<div id="container">
		<div id="content">
			<h1>
				<br><br>보관함 리더기에<br> NFC를 <br>태그해주세요
			</h1>
		<form method="post" action="./insertGoods.do">
			<input type="text" name="email" value="${mem.email }">
			<input type="text" name="idNum"  placeholder="가상의 idNum값">
			<input type="text" name="tag"  placeholder="가상의 NFC값"> 
			<input type="button" class="btn btn-info"  value="태그" onclick="tag()">
		</form>
		</div>
	</div>
</body>
</html>