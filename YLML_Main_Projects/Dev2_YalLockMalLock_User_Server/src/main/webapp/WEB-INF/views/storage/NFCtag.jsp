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
function chkTag(){
	var email = document.getElementById('email').value;
	var tag = document.getElementById('tag').value;
	var idNum = document.getElementById('idNum').value;

		$.ajax({
			url : './NFCtag.do',
			type : 'post',
			data : {
				'email' : email,
				'tag' : tag,
				'idNum' : idNum
			},
			success : function(result) {
				if(result==0){
					alert('등록된 정보와 일치하지 않습니다.');	
				}else{
					location.href='./insertGoods.do';
				}
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
			<input type="text" name="email"  id="email" value="${mem.email }">
			<input type="text" name="idNum"  id="idNum" placeholder="가상의 idNum값">
			<input type="text" name="tag"  id="tag" placeholder="가상의 NFC값"> 
			<input type="button" class="btn btn-info"  value="태그" onclick="chkTag()">
		</div>
	</div>
</body>
</html>