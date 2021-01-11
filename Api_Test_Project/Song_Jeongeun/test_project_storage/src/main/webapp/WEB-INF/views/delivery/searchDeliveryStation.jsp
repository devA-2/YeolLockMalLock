<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도착 지점 선택</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function calTime(arrive) {
		console.log(arrive)
		var storageId = document.getElementById("storageId").value
		var start = document.getElementById("start").value
		console.log(start)
		if(start != arrive){
			$.ajax({
				type:"post",
				url:"./checkDeliveryInfo.do",
				data:"start="+start+"&arrive="+arrive,
				success: function(info){
					if(info.isc == "success"){
						$('#resultTime').append('<span>예상 소요 시간 : '+info.cost.time+'분</span>')
						$('#resultCost').append('<span>배송 비용 : '+info.cost.cost+'원</span><br>')
						$('#resultCost').append('<span>소요 시간은 가장 가까운 배달원의 이동시간을 고려하여 산정되었으며, 배달원 사정에 따라 배송 시간이 늦어질 수 있습니다.</span><br>')
						$('#resultCost').append('<span>보관 비용은 산정 되지 않은 비용입니다.</span>')
						$('#resultCost').append('<button onclick="delivery()">배송하기</button>')
					}else if(info.isc == "false"){
						$('#resultTime').append('<span>현재 배송 물량 초과로배송 진행이 어렵습니다.</span>')
					}
				},
				error: function(){
				}
			});
		}else{
			console.log("현재 역을 선택하셨습니다.")
		}
	}
	
	function delivery() {
		location.href = './delivery.do'
	}
</script>
</head>
<body>
	<form action="">
		<input type="hidden" id="storageId" value="${param.storageId}">
		<input type="hidden" id="start" value="${param.subway}">
		<input type="button" name="arrive" value="시청역" onclick="calTime(value)">
		<input type="button" name="arrive" value="을지로입구역" onclick="calTime(value)">
		<input type="button" name="arrive" value="을지로3가역" onclick="calTime(value)">
		<input type="button" name="arrive" value="을지로4가역" onclick="calTime(value)">
		<input type="button" name="arrive" value="동대문역사문화공원역" onclick="calTime(value)">
		<input type="button" name="arrive" value="신당역" onclick="calTime(value)">
		<input type="button" name="arrive" value="상왕십리역" onclick="calTime(value)">
		<input type="button" name="arrive" value="왕십리역" onclick="calTime(value)">
		<input type="button" name="arrive" value="한양대역" onclick="calTime(value)">
		<input type="button" name="arrive" value="뚝섬역" onclick="calTime(value)">
	</form>

	<div>
		<div id="resultTime"></div>
		<div id="resultCost"></div>
	</div>
</body>
</html>