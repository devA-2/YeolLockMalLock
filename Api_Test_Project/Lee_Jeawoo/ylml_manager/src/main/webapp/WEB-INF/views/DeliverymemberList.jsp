<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>     
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<meta charset="UTF-8">
<title>담당자 및 배송원 전체 정보조회</title>
</head>
<body>
allDeleveryList.do<br>
담당자 및 배송원 전체 정보조회<br>
<div id="container">
<h1><a href="./managerMain.do">관리자메인페이지로</a></h1>


	<hr>
	
	<script type="text/javascript">
      
      // 익명함수, 페이지 로드될 때 실행됨 -> 전체리스트 호출
      $(function() {
         $.get("list.do", function(data) {
//             console.log(data);
            $('#list').html(data);
         }); 
      });
      
      
      // id 검색버튼 클릭시, 검색어 결과 호출
      function search() {
    	  var searchID = $('#searchID').val();
//     	  console.log(searchID);
    	  
    	  if(searchID == ""){
    		  alert('검색어를 입력해 주세요');
    	  }else {
          $.get("list.do?param="+searchID, function(data) {
//              console.log(data);
             $('#list').html(data);
          });
          
    	  }
       }
	   
      // 일치검색어 없을시 에러메세지 출력
      function nullChk() {
		var chk = $('#noResult').val();
// 		console.log(chk);
		
		if(chk == ""){
			alert("검색결과가 없습니다.");
		}
	}
      
      // 전체리스트보기
      function viewAllList() {
    	  $.get("list.do", function(data) {
//               console.log(data);
              $('#list').html(data);
           });
	}
      
      // 임시권한 회원 보기
      function viewTempAuth() {
    	  $.get("viewTempAuth.do", function(data) {
//               console.log(data);
              $('#list').html(data);
           });
	}
    </script>
    
    
    
    
    <div id='container'>
   <button onclick="viewTempAuth()">임시권한회원</button>&nbsp;
   <input id="searchID" type="text"  placeholder="검색어를 입력하세요"/>
   <button onclick="search(),setTimeout(nullChk,1500);">확인</button>&nbsp;
   <button onclick="viewAllList()">전체리스트</button>
   <div id=list></div>
   </div>
   
   
   
   <hr>
<input style="font-size: large;" type="button" value="돌아가기" onclick="history.back(-1)">    
</div>
</body>
</html>