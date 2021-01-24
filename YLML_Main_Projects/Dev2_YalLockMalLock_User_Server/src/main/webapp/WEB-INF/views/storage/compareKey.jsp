<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>Insert title here</title>
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
	<div class="container">
		<h1>
			결제전<br> 보관함 리더기에<br> NFC를 태그해주세요
		</h1>
		<h5>태그 일치시 결제화면으로 넘어갑니다</h5>
		<input type="hidden" id="overCost" value="${overCost }"> 
		<input type="text" id="key" placeholder="가상 키 대조"> 
		<input type="button" value="태그" onclick="compareKey()">


	</div>
</body>
</html>