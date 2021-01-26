<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="./css/header.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="./js/managerAuth.js"></script>
<meta charset="UTF-8">
<title>보관함 전체 조회</title>
<script type="text/javascript">

	// 익명함수, 페이지 로드될 때 실행됨 -> 전체리스트 호출
	$(function() {
	   $.get("storageList.do", function(data) {
	      $('#list').html(data);
	   }); 
	});
	
	// 전체리스트보기
    function viewAllList() {
  	  $.get("storageList.do", function(data) {
            $('#list').html(data);
         });
	}
	
	// 선택옵션에 맞춰 검색하기
	// 버튼을 클릭한다 -> 선택 옵션에 맞춰서 해당 컨트롤러를 작동
		function storageSearch() {
		var schOpt = $("#searchOption option:selected").val();
		// 검색 옵션값 확인
		console.log(schOpt);
		
		var schIp = $("#searchInput").val();
		// 검색어 입력값 확인
		console.log(schIp);
		
		// 검색 옵션값에 따라 컨트롤러 분기
		if(schIp == ""){
	  		  alert('검색어를 입력해 주세요');
	  	  }else if(schOpt == "storageId"){
	  		$.get("storageList.do?param="+schIp+"&schOpt=storageId", function(data) {
				$("#list").html(data);
			});
	  	  }else if(schOpt == "subway"){
	  		$.get("selectSubwayStorage.do?param="+schIp+"&schOpt=subway", function(data) {
				$("#list").html(data);
			}); 
	  	  }
		
	}
	
	
	
 // id 검색버튼 클릭시, 검색어 결과 호출
    function search() {
  	  var searchID = $('#searchID').text();
  	  if(searchID == ""){
  		  alert('검색어를 입력해 주세요');
  	  }else {
        $.get("storageList.do?param="+searchID, function(data) {
           $('#list').html(data);
        });
        
  	  }
     }
 
 // 일치검색어 없을시 에러메세지 출력
    function nullChk() {
		var chk = $('#noResult').val();
		if(chk == "검색결과가 없습니다"){
			alert("검색결과가 없습니다.");
		}
	}
 
	
</script>
</head>
<body>

 <div id='container'>
 <%@include file="./header.jsp" %>
 	<form>
 		<div>
			<select id="searchOption">
				<option value="storageId">보관함 ID</option>
				<option value="subway">지하철역</option>
			</select> 
			<input id="searchInput" type="text"  placeholder="검색어를 입력하세요" />
			<input  type="button" value="검색"  onclick="storageSearch(),setTimeout(nullChk,1000);"/>
		</div>
	</form>	
<!-- 		<input id="searchID" type="text"  placeholder="검색어를 입력하세요" /> -->
<!-- 	    <button onclick="search(),setTimeout(nullChk,1000);">확인</button>&nbsp; -->
   		<button onclick="viewAllList()">전체리스트</button>
   <hr>
   <div id=list></div>
   </div>
<hr>
<input style="font-size: large;" type="button" value="메인으로" onclick="location.href='managerMain.do'">  
</body>
</html>