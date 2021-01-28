<%@page import="com.dev2.ylml.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">

<title>신고글 게시판 목록</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/common.css">

<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>

</head>
<script type="text/javascript">
	function chkAuth(memEmail, voEmail, auth, refer){
	if (memEmail == voEmail) {
		location.href="./selectDetailReport.do?refer="+refer;
	}else{
		alert("자신의 게시물만 확인할 수 있습니다.");
	}
	
	}
	
      // 익명함수, 페이지 로드될 때 실행됨 -> 전체리스트 호출
      $(function() {
         $.get("reportListAjax.do", function(data) {
            console.log(data);
            $('#tbody').html(data);
         });
      });
      
      window.onload = function(){
    	  document.getElementById("goSearch").onclick = function(){
    		  var doc = document.getElementById("searchId").value;
    		  if (doc=="") {
				alert("검색하실 아이디를 입력 해 주세요.");
			}else{
				document.getElementById("frm").submit();
			}
    		  
    	  };
    	  
      };
      
</script>

<body>

<div id="container">
	<div id="content">
	
<!-- 		<div class="header"> -->
<!-- 			<form action="./searchIdReport.do" id="frm" method="post"> -->
<!-- 				<input class="inputForm" type="text" placeholder="검색할 ID를 입력하세요" name="email"> -->
<!-- 				<input class="inputForm" type="submit" value="검색"> -->
<!-- 			</form> -->
<!-- 		</div> -->
		
		<div class="header">
			<form action="chkVal()" method="post">
				<input class="inputForm" type="text" id="searchId" placeholder="검색할 ID를 입력하세요" name="email">
				<input class="inputForm" type="submit" id="goSearch" value="검색">
			</form>
		</div>
		
		<table class="table table-hover" style="width: 97%; margin-left: 1.5%; margin-right: 1.5%;">
			<tr>
				<th>SEQ</th>
				<th>작성 일자</th>
				<th>제목</th>
				<th>작성자</th>
			</tr>
			<tbody id="tbody">
			</tbody>
		</table>
		
		<div class="reportBtn">
			<button onclick="location.href = './insertReport.do'">신고 글 작성</button>
		</div>
		
	</div>
</div>
</body>
</html>