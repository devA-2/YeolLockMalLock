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
		var start = document.getElementById ("start").value
		if(start != arrive){
			$.ajax({
				type:"post",
				url:"./checkDeliveryInfo.do",
				data:{"arriveStation":arrive},
				success: function(info){
					if(info.isc == "success"){
						$('#result').empty()
						$('#msg').empty()
						$('#result').append('<tr><td colspan="2"><input type="hidden" name="boxSeq" value="'+info.boxSeq+'"><input type="hidden" name="storageId" value="'+info.storageId+'"></td></tr>')
						$('#result').append('<tr><td>출발역</td><td><input type="text" value="'+info.userStorageSubway+'" readonly></td></tr>')
						$('#result').append('<tr><td>도착역</td><td><input type="text" value="'+info.deliveryStorageSubway+'" readonly><input type="hidden" name="outboxId" value="'+info.outboxId+'"></td></tr>')
						$('#result').append('<tr><td>소요 시간</td><td><input type="text" value="'+info.deliveryTime+'" readonly>분</td></tr>')
						$('#result').append('<tr><td>배송 비용</td><td><input type="text" name="deliveryCost" value="'+info.deliveryCost+'" readonly>원</td></tr>')
						$('#result').append('<tr><td>담당 배달원</td><td><input type="text" value="'+info.delManInfo.deliverymanName+'" readonly>배달원<input type="hidden" name="deliverymanId" value="'+info.delManInfo.deliverymanId+'"></td></tr>')
						$('#result').append('<tr><td>배송 메시지</td><td><input type="text" name="message" placeholder="배달 메시지를 적어주세요"></td></tr>')
						$('#result').append('<tr><td colspan="2"><input type="submit" value="배송하기"></td></tr>')
						
						$('#msg').html('<span>소요 시간은 가장 가까운 배달원의 이동시간을 고려하여 산정되었으며, 배달원 사정에 따라 배송 시간이 늦어질 수 있습니다.</span><br>')
						$('#msg').html('<span>배송 비용에는 보관 비용은 산정 되어 있지 않습니다.</span>')
					}else if(info.isc == "false"){
						$('#msg').html('<span>현재 배송 물량 초과로배송 진행이 어렵습니다.</span>')
					}
				},
				error: function(){
					console.log("Ajax 실패")
				}
			});
		}else{
			console.log("동일 역을 선택했습니다.")
		}
	}
	
	function delivery() {
		location.href = './delivery.do'
	}
</script>
</head>
<body>
	<div>
		<input type="hidden" id="start" value="${sessionScope.storageId}">
		<button name="outboxId" value="CITYHALL_A" onclick="calTime(value)">시청역</button>
		<button name="outboxId" value="EULJIROENTRANCE_A" onclick="calTime(value)">을지로입구역</button>
		<button name="outboxId" value="EULJIRO3_A" onclick="calTime(value)">을지로3가역</button>
		<button name="outboxId" value="EULJIRO4_A" onclick="calTime(value)">을지로4가역</button>
		<button name="outboxId" value="DDP_A" onclick="calTime(value)">동대문역사문화공원역</button>
		<button name="outboxId" value="SINDANG_A" onclick="calTime(value)">신당역</button>
		<button name="outboxId" value="SANGWANGSIMNI_A" onclick="calTime(value)">상왕십리역</button>
		<button name="outboxId" value="WANGSIMNI_A" onclick="calTime(value)">왕십리역</button>
		<button name="outboxId" value="HANYANGUNIVERSITY_A" onclick="calTime(value)">한양대역</button>
		<button name="outboxId" value="TTUKSEOM_A" onclick="calTime(value)">뚝섬역</button>
		
<!-- 		<input type="button" name="arrive" value="CITYHALL_A" onclick="calTime(value)"> -->
<!-- 		<input type="button" name="arrive" value="EULJIROENTRANCE_A" onclick="calTime(value)"> -->
<!-- 		<input type="button" name="arrive" value="EULJIRO3_A" onclick="calTime(value)"> -->
<!-- 		<input type="button" name="arrive" value="EULJIRO4_A" onclick="calTime(value)"> -->
<!-- 		<input type="button" name="arrive" value="DDP_A" onclick="calTime(value)"> -->
<!-- 		<input type="button" name="arrive" value="SINDANG_A" onclick="calTime(value)"> -->
<!-- 		<input type="button" name="arrive" value="SANGWANGSIMNI_A" onclick="calTime(value)"> -->
<!-- 		<input type="button" name="arrive" value="WANGSIMNI_A" onclick="calTime(value)"> -->
<!-- 		<input type="button" name="arrive" value="HANYANGUNIVERSITY_A" onclick="calTime(value)"> -->
<!-- 		<input type="button" name="arrive" value="TTUKSEOM_A" onclick="calTime(value)"> -->
	</div>

	<div>
		<form action="./delivery.do" method="post">
			<table id="result">
			</table>
		</form>
		<div id="msg"></div>
	</div>
</body>
</html>