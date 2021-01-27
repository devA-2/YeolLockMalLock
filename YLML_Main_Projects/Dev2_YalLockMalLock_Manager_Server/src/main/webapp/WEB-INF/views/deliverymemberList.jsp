<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>     
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	#table{
		width: 1200px;
		margin-left: 35px;
		text-align: center;
	}
	
	#th{
		font-weight:bold;
		font-size:medium;
		text-align: center;
		background-color: #F2F2F2;
	}
	
	#searchID{
		width: 150px;
		float: left;
	}
	
	#btnTmpAuth{
		float: left;
	}
	
	#cfnBtn{
		float: left;
	}
	
	#allListBtn{
		float:right; 
	}
	
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="./css/common.css">
<script type="text/javascript" src="./js/managerAuth.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">

<title>담당자 및 배송원 전체 정보조회</title>
</head>
<script type="text/javascript">
      
      // 익명함수, 페이지 로드될 때 실행됨 -> 전체리스트 호출
      $(function() {
         $.get("list.do", function(data) {
            $('#list').html(data);
         }); 
      });
      
      
      // id 검색버튼 클릭시, 검색어 결과 호출
      function search() {
    	  var searchID = $('#searchID').val();
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

<body>
<div id="container">
<%@include file="./header.jsp" %>
    <div id="table">
   <button id="btnTmpAuth" class="btn btn-info" onclick="viewTempAuth()">임시권한회원보기</button>&nbsp;
   <input id="searchID" class="form-control" type="text"  placeholder="검색어를 입력하세요"/>
   <button id="cfnBtn" class="btn btn-primary" onclick="search(),setTimeout(nullChk,1000);">확인</button>&nbsp;
   <button id="allListBtn" class="btn btn-info" onclick="viewAllList()">전체리스트</button>
   <hr>
   <div id=list></div>
   </div>
   
   
   <%@include file="./footer.jsp" %>
</div>
</body>
</html>