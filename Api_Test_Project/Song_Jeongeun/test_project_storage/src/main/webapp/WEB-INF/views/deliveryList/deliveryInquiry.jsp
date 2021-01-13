<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>미리 조회</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function start(start) {
		$.ajax({
			url: "./searchSubway.do",
			data: {"storageId":start},
			type: "POST",
			success : function(dto){
				console.log(dto.subway)
				document.getElementById("start").value = dto.storageId
				document.getElementById("start").childNodes[1].innerHTML = dto.subway
			},
			error : function(){
				alert("서비스에 문제가 생겼습니다.")		
			}
		});
	}
	
	function arrive(arrive) {
		var start = document.getElementById("start").innerHTML
		$.ajax({
			url: "./searchSubway.do",
			data: {"storageId":arrive},
			type: "POST",
			success : function(dto){
				if(start == dto.subway){
					console.log("같은 위치를 선택하셨습니다. 다시 선택해주세요.")
				}else{
					console.log(dto.subway)
					document.getElementById("arrive").value = dto.storageId
					document.getElementById("arrive").childNodes[1].innerHTML = dto.subway
				}
			},
			error : function(){
				alert("서비스에 문제가 생겼습니다.")		
			}
		});
	}
	
	function calTime() {
		var startStatin = document.getElementById("start").childNodes[1].innerHTML
		var arriveStatin = document.getElementById("arrive").childNodes[1].innerHTML
		$.ajax({
			type:"post",
			url:"./inquiryDelivery.do",
			data:{"startStation":startStatin, "arriveStation":arriveStatin},
			success: function(info){
				$('#time > span').html(info.deliveryTime+'분')
				$('#cost > span').html(info.deliveryCost+'원')
				$('#msg').html('소요 시간은 보관함 간의 거리만은 산정한 시간입니다.<br>추후 배송 이용시 배송원의 사정에 따라 변경될 수 있습니다.<br>')
			},
			error: function(){
				console.log("Ajax 실패")
			}
		});
	}
	
	function reset() {
		document.getElementById("start").childNodes[1].innerHTML = ''
		document.getElementById("arrive").childNodes[1].innerHTML = ''
		document.getElementById("time").childNodes[1].innerHTML = ''
		document.getElementById("cost").childNodes[1].innerHTML = ''
		document.getElementById("msg").innerHTML = ''
	}
</script>
</head>
<body>
	<div>
		<button name="start" value="CITYHALL_A" onclick="start(value)">시청역</button>
		<button name="start" value="EULJIROENTRANCE_A" onclick="start(value)">을지로입구역</button>
		<button name="start" value="EULJIRO3_A" onclick="start(value)">을지로3가역</button>
		<button name="start" value="EULJIRO4_A" onclick="start(value)">을지로4가역</button>
		<button name="start" value="DDP_A" onclick="start(value)">동대문역사문화공원역</button>
		<button name="start" value="SINDANG_A" onclick="start(value)">신당역</button>
		<button name="start" value="SANGWANGSIMNI_A" onclick="start(value)">상왕십리역</button>
		<button name="start" value="WANGSIMNI_A" onclick="start(value)">왕십리역</button>
		<button name="start" value="HANYANGUNIVERSITY_A" onclick="start(value)">한양대역</button>
		<button name="start" value="TTUKSEOM_A" onclick="start(value)">뚝섬역</button>
	</div>
	
	<div>
		<button name="arrive" value="CITYHALL_A" onclick="arrive(value)">시청역</button>
		<button name="arrive" value="EULJIROENTRANCE_A" onclick="arrive(value)">을지로입구역</button>
		<button name="arrive" value="EULJIRO3_A" onclick="arrive(value)">을지로3가역</button>
		<button name="arrive" value="EULJIRO4_A" onclick="arrive(value)">을지로4가역</button>
		<button name="arrive" value="DDP_A" onclick="arrive(value)">동대문역사문화공원역</button>
		<button name="arrive" value="SINDANG_A" onclick="arrive(value)">신당역</button>
		<button name="arrive" value="SANGWANGSIMNI_A" onclick="arrive(value)">상왕십리역</button>
		<button name="arrive" value="WANGSIMNI_A" onclick="arrive(value)">왕십리역</button>
		<button name="arrive" value="HANYANGUNIVERSITY_A" onclick="arrive(value)">한양대역</button>
		<button name="arrive" value="TTUKSEOM_A" onclick="arrive(value)">뚝섬역</button>
	</div>
	
	<div id="start">출발  <span></span></div>
	<div id="arrive">도착  <span></span></div>
	<div id="time">예상 소요 시간  <span></span></div>	
	<div id="cost">배송 비용  <span></span></div>		
	<div id="msg"></div>
	<div>
		<button onclick="calTime()">계산</button>
		<button onclick="reset()">다시 선택</button>
	</div>
	
</body>
</html>