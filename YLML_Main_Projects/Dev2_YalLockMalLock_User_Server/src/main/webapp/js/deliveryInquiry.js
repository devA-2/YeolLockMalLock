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
	var startStatin = document.getElementById("start").innerText
	var arriveStatin = document.getElementById("arrive").innerText
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