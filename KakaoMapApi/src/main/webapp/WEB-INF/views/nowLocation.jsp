<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>Kakao 지도 현재위치 받아오기 </title>
</head>
<body>
	<!-- 고유키 설정 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=17fa16b302b947b735f86c5f96eb39b1"></script>

	<!-- 지도 표시하는 div -->
	<div id="map" style="width:500px;height:400px;"></div>
	<button onclick="goNow()">현재 위치 표시</button>
	
	<script>
		var container = document.getElementById('map');
		var options = {
				//지도의 중심좌표(학원위치)
			center: new kakao.maps.LatLng(37.47788928071299,126.87901000461567),
			level: 3 //지도의 확대 레벨 
		};

		var map = new kakao.maps.Map(container, options); // 지도생성

		// 지도를 클릭한 위치에 표출할 마커입니다
		var marker = new kakao.maps.Marker({ 
		    // 지도 중심좌표에 마커를 생성합니다 
		    position: map.getCenter() 
		}); 
		// 지도에 마커를 표시합니다
		marker.setMap(map);
		
		function goNow(){
 		if (navigator.geolocation) { // HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
 		    navigator.geolocation.getCurrentPosition(function(position) {
	        
		    var lat = position.coords.latitude, // 위도
	       		lon = position.coords.longitude; // 경도
	        
	       var locPosition = new kakao.maps.LatLng(lat, lon);
	       		//현재위치 생성
	       		
 			marker.setPosition(locPosition);
 			//마커 위치 현재 위치로 변경
 			
 		    map.panTo(locPosition);  // 지도 중심을 부드럽게 이동시킵니다
 		    });
 		    }else{// HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
 		    	alert('현재위치를 찾을수 없습니다');
 		    }
		}
		
		
// 			var locPosition; 왜 미리 선언해서 사용하는데 안먹지 ??
		
// 		// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
// 		if (navigator.geolocation) {
		    
// 		    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
// 		    navigator.geolocation.getCurrentPosition(function(position) {
		        
// 		        var lat = position.coords.latitude, // 위도
// 		            lon = position.coords.longitude; // 경도
		        
// 		        var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
// 		            message = '<div style="padding:4px;">현재위치</div>'; // 인포윈도우에 표시될 내용입니다
// 		        // 마커와 인포윈도우를 표시합니다
// 		        displayMarker(locPosition, message);
// 		      });
		    
// 		} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
// 		    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
// 		        message = 'geolocation을 사용할수 없어요..'
// 		    displayMarker(locPosition, message);
// 		}

// 		// 지도에 마커와 인포윈도우를 표시하는 함수입니다
// 		function displayMarker(locPosition, message) {
// 		    // 마커를 생성합니다
// 		    var marker = new kakao.maps.Marker({  
// 		        map: map, 
// 		        position: locPosition
// 		    }); 
		    
// 		    var iwContent = message, // 인포윈도우에 표시할 내용
// 		        iwRemoveable = true;

// 		    // 인포윈도우를 생성합니다
// 		    var infowindow = new kakao.maps.InfoWindow({
// 		        content : iwContent,
// 		        removable : iwRemoveable`
// 		    });
		    
// 		    // 인포윈도우를 마커위에 표시합니다 
// 		    infowindow.open(map, marker);
		    
// 		    // 지도 중심좌표를 접속위치로 변경합니다
// 		    map.setCenter(locPosition);      
// 		}    
	</script>
</body>
</html>