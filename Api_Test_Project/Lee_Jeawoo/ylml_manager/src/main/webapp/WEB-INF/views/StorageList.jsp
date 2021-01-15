<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<meta charset="UTF-8">
<title>보관함 전체 조회</title>
<script type="text/javascript">

	// 익명함수, 페이지 로드될 때 실행됨 -> 전체리스트 호출
	$(function() {
	   $.get("storagelist.do", function(data) {
	//       console.log(data);
	      $('#list').html(data);
	   }); 
	});
</script>
</head>
<body>
StorageList.do<br>
보관함 전체 조회
${lists}


 <div id='container'>
   <button onclick="viewTempAuth()">임시권한회원</button>&nbsp;
   <input id="searchID" type="text"  placeholder="검색어를 입력하세요"/>
   <button onclick="">확인</button>&nbsp;
   <button onclick="viewAllList()">전체리스트</button>
   <div id=list></div>
   </div>

<input style="font-size: large;" type="button" value="돌아가기" onclick="history.back(-1)">  
</body>
</html>