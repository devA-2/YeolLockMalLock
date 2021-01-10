<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" 	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta charset="utf-8"/>
</head>
<body>
	<!-- 고유키 설정 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=17fa16b302b947b735f86c5f96eb39b1"></script>

	<!-- 지도 표시하는 div -->
	<div class="container" id="map" style="width:500px;height:500px;"></div>
<!-- 	<input type="button" onclick="getCount('CITYHALL_A')"> -->
<!-- <input type="button" onclick="location.href='./selectStorageStatus.do?id=CITYHALL_A'"> -->
	<script>

	console.log(JSON.parse(JSON.stringify(${positions})));
	
	var container = document.getElementById('map');
	var options = {
			//지도의 중심좌표(학원위치)
		center: new kakao.maps.LatLng(37.47788928071299,126.87901000461567),
		level: 3 //지도의 확대 레벨 
	};

	var map = new kakao.maps.Map(container, options); // 지도생성

	if (navigator.geolocation) { // HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
	    navigator.geolocation.getCurrentPosition(function(position) {
       
    var lat = position.coords.latitude, // 위도
   		lon = position.coords.longitude; // 경도
       
    var locPosition = new kakao.maps.LatLng(lat, lon);
      		//현재위치 생성
			
	    map.panTo(locPosition);  // 지도 중심을 부드럽게 이동시킵니다
	    });
	    }else{// HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
	    	alert('현재위치를 찾을수 없습니다');
	    }
	
	//마커를 표시할 위치와 title 객체 배열입니다 
	var positions = ${positions};
	
	//마커 이미지의 이미지 주소입니다
	var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

	for (var i = 0; i < positions.length; i++) {

		// 마커 이미지의 이미지 크기 입니다
		var imageSize = new kakao.maps.Size(24, 35);

		// 마커 이미지를 생성합니다    
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			map : map, // 마커를 표시할 지도
			position : positions[i].LATLNG, // 마커를 표시할 위치
			image : markerImage,// 마커 이미지 
			clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
		});
		
		
		// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
		var iwContent = '<div id="info" style="padding:5px;"></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
		    iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

		    
		// 인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
		    content : iwContent,
		    removable : iwRemoveable
		});

		 var position = positions[i];   
		    
		// 마커에 이벤트를 등록하는 함수 만들고 즉시 호출하여 클로저를 만듭니다
	    // 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
	    (function(marker, infowindow) {
	        // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다 
	        kakao.maps.event.addListener(marker, 'click', function() {   
// 	        	getCount(position.ID);
				console.log(position.ID);
	        	infowindow.open(map, marker);
	        });
	    })(marker, infowindow);
	}//FOR문
	function getCount(id){
		
	        	$.ajax({
	        	      url:'./ajaxCountStorage.do',
	        	      type: 'get',
	        	      data: {"id" : id},
	        	      success:function(LDto){
	        	      console.log(LDto);
// 	        	    	console.log(cnt)
// 						document.getElementById('info').innerText = cnt;
	        	      },
	        	      error : function(){
	        	         alert("잘못된 요청입니다");
	        	      }
	        	   });//ajax
	}

	</script>
</body>
</html>