//지도에 마커표시
$.get("./selectMap.do", function(markers) {
//			console.log(markers);
	addMarker(markers);
});

var container = document.getElementById('map');
var options = {
	//지도의 중심좌표(학원위치)
	center : new kakao.maps.LatLng(37.56586056914695, 126.9768273395538),
	level : 3
	//지도의 확대 레벨 
};

var map = new kakao.maps.Map(container, options); // 지도생성

if (navigator.geolocation) { // HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
	navigator.geolocation.getCurrentPosition(function(position) {

	var lat = position.coords.latitude, // 위도
	lon = position.coords.longitude; // 경도

	var locPosition = new kakao.maps.LatLng(lat, lon);
	//현재위치 생성

	map.panTo(locPosition); // 지도 중심을 부드럽게 이동시킵니다
	});
} else {// HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
	alert('현재위치를 찾을수 없습니다');
}
      
//마커 추가하는 함수
var addMarker = function(markers) {
	//마커 이미지의 이미지 주소입니다
	var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

	for (i in markers) {
		// 마커 이미지의 이미지 크기 입니다
		var imageSize = new kakao.maps.Size(24, 35);
	
		// 마커 이미지를 생성합니다    
		var markerImage = new kakao.maps.MarkerImage(imageSrc,imageSize);
	
		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			map : map, // 마커를 표시할 지도
			position : new kakao.maps.LatLng(markers[i].lng,markers[i].lat), // 마커를 표시할 위치
			image : markerImage,// 마커 이미지 
			clickable : true,
			title : markers[i].id
			// 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
		});
	
		// 마커에 이벤트를 등록하는 함수 만들고 즉시 호출하여 클로저를 만듭니다
		// 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
		(function(marker) {
			// 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다 
			kakao.maps.event.addListener(marker, 'click', function() {
				istStation(marker.Fb);
			});
		})(marker);
	}//FOR문
}//마커추가하는 함수 끝!


function istStation(storageId){
	console.log("istStation 실행!!")
	var start = document.getElementById("start")
	var arrive = document.getElementById("arrive")
	$.ajax({
		url: "./searchSubway.do",
		data: {"storageId":storageId},
		type: "POST",
		success : function(dto){
			// 출발이 비어있으면
			if(start.innerText == ""){
				start.previousSibling.value = dto.storageId
				start.innerText = dto.subway
			// 출발이 있다면
			}else if(start.innerText != ""){
				start.previousSibling.value = dto.storageId
				arrive.innerText = dto.subway
				if(start.innerText == arrive.innerText){
					console.log("동일한 역을 선택하셨습니다. 다시 선택해주세요.")
					arrive.innerText = ""
				}
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
	console.log(startStatin)
	$.ajax({
		type:"post",
		url:"./inquiryDelivery.do",
		data:{"startStation":startStatin, "arriveStation":arriveStatin},
		success: function(info){
			html = "<div>"
			html += "<label for='deliveryTime'>소요 시간 : </label>"
			html += "<span id='deliveryTime'>"+info.deliveryTime+"분</span>"
			html += "</div>"
			html += "<div>"
			html += "<label for='deliveryCost'>배송 비용 : </label>"
			html += "<span id='deliveryCost'>"+info.deliveryCost+"원</span>"
			html += "</div>"
			html += "<div>"
			html += "<label for='msg'></label>"
			html += "<span>소요 시간은 보관함 간의 거리만은 산정한 시간입니다.<br>"
			html += "추후 배송 이용시에는 배송원의 사정에 따라 변경될 수 있습니다.</span>"
			html += "</div>"
			$('#result').html(html)	
		},
		error: function(){
			console.log("Ajax 실패")
		}
	});
}

function reset() {
	document.getElementById("start").innerText = ""
	document.getElementById("arrive").innerText = ""
	document.getElementById("result").innerHTML = ""
}