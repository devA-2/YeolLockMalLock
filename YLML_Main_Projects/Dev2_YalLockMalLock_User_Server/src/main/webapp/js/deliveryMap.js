//검색창 자동완성
$( function() {
	$.ajax({
		url : './selectStorageList.do',
		type : 'get',
		dataType : 'json',
		success : function(storages) {
//			console.log(storages);
			$( "#search" ).autocomplete({
				minLength: 1,//최소 글자수
				source: storages,//가져오는 array
				focus: function( event, ui ) {
					$( "#search" ).val( ui.item.label );
					return false;
				},
				select: function( event, ui ) {
					$( "#search" ).val( ui.item.label );
					location.href='https://map.kakao.com/link/search/'+ui.item.value;
					return false;
				}
			})
			.autocomplete( "instance" )._renderItem = function( ul, item ) {
				return $( "<li>" )
				.append( "<div><strong>" + item.label + "</strong><br>" + item.desc + "</div>" )
				.appendTo( ul );
			};
		},
		error : function() {
			console.log("ajax 오류");
		}
	});//ajax
}); 

//지도에 마커표시
$.get("./selectMap.do", function(markers) {
//	console.log(markers);
	addMarker(markers);
});

var container = document.getElementById('map');
var options = {
		//지도의 중심좌표(학원위치)
		center : new kakao.maps.LatLng(37.56570275817552, 126.97685084761777),
		level :5
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
			position : new kakao.maps.LatLng(markers[i].lat,markers[i].lng), // 마커를 표시할 위치
			image : markerImage,// 마커 이미지 
			clickable : true,
			// 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
			title : markers[i].id
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
	$('#result').empty()
	$.ajax({
		type:"post",
		url:"./checkDeliveryInfo.do",
		data: {"arriveStation":storageId},
		success : function(info){
			if(info.isc == "success"){
				console.log(typeof info.boxSeq)
				console.log(typeof info.deliveryCost)
				html = "<div>"
				html += "<input type='hidden' name='boxSeq' value='"+info.boxSeq+"'>"
				html += "<input type='hidden' name='storageId' value='"+info.storageId+"'>"
				html += "</div>"	
				html += "<div>"	
				html += "<label for='start'>출발</label>"
				html += "<span id='start'>"+info.userStorageSubway+"</span>"
				html += "</div>"
				html += "<div>"
				html += "<label for='arrive'>도착</label>"
				html += "<span id='arrive'>"+info.deliveryStorageSubway+"</span>"
				html += "<input type='hidden' name='outboxId' value='"+info.outboxId+"'>"
				html += "</div>"	
				html += "<div>"
				html += "<label for='deliveryTime'>소요 시간</label>"
				html += "<span id='deliveryTime'>"+info.deliveryTime+"분</span>"
				html += "</div>"
				html += "<div>"
				html += "<label for='deliveryCost'>배송 비용</label>"
				html += "<span id='deliveryCost'>"+info.deliveryCost+"원</span>"
				html += "<input type='hidden' name='deliveryCost' value='"+info.deliveryCost+"'>"
				html += "</div>"
				html += "<div>"
				html += "<label for='deliverymanName'>담당 배달원</label>"
				html += "<span id='deliverymanName'>"+info.delManInfo.deliverymanName+"배달원</span>"
				html += "<input type='hidden' name='deliverymanId' value='"+info.delManInfo.deliverymanId+"'>"
				html += "</div>"
				html += "<div>"
				html += "<label for='message'>배송 메시지</label>"
				html += "<input type='text' class='form-control' id='message' name='message' placeholder='배송 메시지(선택)'>"
				html += "</div>"
				html += "<div>"
				html += "<span id='msg'>소요 시간은 가장 가까운 배달원의 이동시간을 고려하여 산정되었으며,<br>배달원 사정에 따라 배송 시간이 늦어질 수 있습니다.<br>"
				html += "배송 비용에는 보관 비용은 산정 되어 있지 않습니다.</span>"
				html += "</div>"
				html += "<div>"
				html += "<input type='submit' id='btn' class='btn btn-info' value='배송하기'>"
				html += "</div>"	
				$('#result').html(html)	
			}else if(info.isc == "false"){
				html = "<div>"
				html += "<label for='msg'></label>"
				html += "<span>현재 배송 물량 초과로 배송 신청이 불가합니다.</span>"
				html += "</div>"
				$('#result').html(html)	
			}else if(info.isc == "same"){
				html = "<div>"
				html += "<label for='msg'></label>"
				html += "<span>동일 역을 선택했습니다.</span>"
				html += "</div>"
				$('#result').html(html)
			}
		},
		error: function(){
			console.log("Ajax 실패")
		}
	});
}