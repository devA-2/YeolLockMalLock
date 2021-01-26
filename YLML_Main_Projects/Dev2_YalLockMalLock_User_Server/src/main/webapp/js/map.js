//검색창 자동완성
$( function() {
	$.ajax({
		url : './storage/selectStorageList.do',
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
					location.href='./storage/selectStorageStatus.do?id='+ui.item.value;
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
$.get("./storage/selectMap.do", function(markers) {
//			console.log(markers);
    	  addMarker(markers);
      });

      var container = document.getElementById('map');
      var options = {
         //지도의 중심좌표(학원위치)
         center : new kakao.maps.LatLng(37.47788928071299, 126.87901000461567),
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

             // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
             var iwContent = '<div id="info" style="padding:5px;"><br><br><br></div>', 
             // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
             iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

             // 인포윈도우를 생성합니다
             var infowindow = new kakao.maps.InfoWindow({
                content : iwContent,
                removable : iwRemoveable
             });

             // 마커에 이벤트를 등록하는 함수 만들고 즉시 호출하여 클로저를 만듭니다
             // 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
             (function(marker, infowindow) {
                // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다 
                kakao.maps.event.addListener(marker, 'click', function() {
                	console.log(marker)
//                	infowindow.close();
                   getInfo(marker.Fb);
                   infowindow.open(map, marker);
                });
             })(marker, infowindow);
          }//FOR문
      }//마커추가하는 함수 끝!
      

      //마커 클릭시 ajax로 정보 가져오기 
      function getInfo(id) {
//    	  console.log(id);
    	  $.ajax({
    		  url : './storage/ajaxCountStorage.do',
    		  type : 'get',
    		  data : { "id" : id },
    		  success : function(LDto) {
//    			  console.log(LDto);
    			  msg = LDto.subway+"  <br><strong>"+LDto.storageName+"</strong><br>사용가능 갯수 : "+ LDto.cnt ;
    			  document.getElementById('info').innerHTML = msg;
    			  document.getElementById('info').onclick=function(){
    				  location.href='./storage/selectStorageStatus.do?id='+LDto.storageId;
    			  }
    		  },
    		  error : function() {
    			  alert("잘못된 요청입니다");
    		  }
    	  });//ajax
      }
