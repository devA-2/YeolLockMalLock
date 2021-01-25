<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/common.css">
<style type="text/css">
#content{
	text-align: center;
	padding : 10%;
}
#btn{
	width:50%;
	text-align: center;
	display: flex;
	padding-left: 20%;
}
</style>
</head>
<body>
	<script type="text/javascript">
		function compareKey() {
			var key = document.getElementById('key').value;
			if (key == '') {
				alert("key를 입력해주세요")
				return;
			}

			$.ajax({
				url : './compareKey.do',
				type : 'post',
				data : {"key" : key},
				success : function(isc) {
					if (isc) {
						checkOverCost();//추가결제금액 있을시 update후 결제 진행
					} else {
						alert("키가 일치하지 않습니다. 다시 시도해주세요")
					}//else문 끝

				},
				error : function() {
					alert("문제가 발생했습니다.");
					location.href = "./userStorageList.do";
				}
			});//ajax

		}
		
	function checkOverCost(){
		var overCost = document.getElementById('overCost').value;
		if(overCost>0){
			$.ajax({
				url : './updateOverCost.do',
				type : 'get',
				async: false,
				data : {"overCost" : overCost},
				success : function(isc) {
					if(isc){
						alert("보관시간이 초과되어 "+overCost+"원이 추가로 결제됩니다.")
					}else{
						alert("overCost update 실패");
						location.href = "./userStorageList.do";
					}
				},
				error : function() {
					alert("문제가 발생했습니다.");
					location.href = "./userStorageList.do";
				}
			});//ajax
		}
		location.href="./paymentPage.do";
		
	}
		
	</script>
	<div id="container">
		<div id="content">
		<h2>
			<br><br>결제전<br> 보관함 리더기에<br> NFC를 태그해주세요<br>
		</h2>
		<h5>태그 일치시 결제화면으로 넘어갑니다</h5>
		</div>
		<div id="btn">
		<input type="hidden" id="overCost" value="${overCost }"> 
		<input type="text" id="key" placeholder="가상 키 대조"> 
		<input type="button" value="태그" class="btn btn-info"  onclick="compareKey()">
		
		</div>


	</div>
</body>
</html>