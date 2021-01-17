<%@page import="com.min.edu.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>신고글 게시판 목록</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" type="text/css" href="./css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" media="screen" href="./css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="./css/ui.multiselect.css" />


    <script type="text/javascript" src="./js/jquery.min.js"></script>  
    <script type="text/javascript" src="./js/i18n/grid.locale-kr.js"></script>
    <script type="text/javascript" src="./js/jquery.jqGrid.min.js"></script>

</head>
<script type="text/javascript">
	function chkAuth(memEmail, voEmail, auth, refer){
	if (memEmail == voEmail || auth == 30 || auth == 20) {
		location.href="./selectDetailReport.do?refer="+refer;
	}else{
		alert("자신의 게시물만 확인할 수 있습니다.");
	}
	
		
	}

//       // 익명함수, 페이지 로드될 때 실행됨 -> 전체리스트 호출
//       $(function() {
//          $.get("reportList.do", function(data) {
//             console.log(data);
//             $('#tbody').html(data);
//          }); 
//       });
	
</script>

<!-- jqGrid 테스트 스크립트 -->
<script type="text/javascript">
var $Grid = {};

$(document).ready(function() {
	 	$Grid = $("#jqGrid");
	   var cnames = ['번호','작성일','제목','작성자'];
	   
	       $Grid.jqGrid({
	           url: "/adminLogin.do",
	           datatype: "json",
	           mtype: "get",
	           jsonReader :{
	        	 id :"seq",
	        	 root: "employee"
	           },
	           colNames: cnames,
	           colModel:[
	         {name:'seq', index:'seq', width:55, key:true, align:"center"},
	         {name:'regdate', index:'regdate', width:100, align:"center"},
	         {name:'title', index:'title', width:100},
	         {name:'email', index:'email', width:100}
	   ],
	           height: 480,
	           width: 1000,
	           rowNum: 10,
	           rowList: [10,20,30],
	           pager: '#pager2',
	           rownumbers  : true,
	           sortname: 'id',
	           viewrecords: true,
	           sortorder: "desc",
	           caption:"JSON TEST TABLE"
	       });
	   });
jQuery("#jqGrid").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});

// $.ajax({
// 				url: "adminLogin.do",
// 				dataType: "jsonp",
// 				type: "get",
// 				success: function(data){
// 				console.log(data);
// 					console.log(data.rows);
// 					var a=data.rows;
// 					$("#jqGrid").jqGrid('setGridParam',{'data' : a}).trigger('reloadGrid');
// 				},error: function(){
// 					console.log("에러 : " + error);
// 				}
// 			});

</script>

<body>

<div>
<div>${mem.email }</div>
	<%
	
	  	Object obj = session.getAttribute("mem"); 
	 	MemberDto mem = (MemberDto)obj;
	
		
		
	 	if(Integer.parseInt(mem.getAuth()) == 30){
	 		%> 
	 		<div>관리자 권한</div> 
			<% 
		}else if(Integer.parseInt(mem.getAuth()) == 10){
	  		%> 
	 		<div>일반 사용자</div>
	 		<% 
	  	}  
	%> 

	<table>
		<tr>
			<th>SEQ</th>
			<th>작성 일자</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
		<c:forEach items="${lists}" var="vo" varStatus="vs">
			<tr onclick="chkAuth('${mem.email}', '${vo.email}', '${mem.auth}', '${vo.refer}')">
<!-- 			<tr> -->
				<td>${vo.seq}</td>
				<td>${vo.regdate}</td>
				<td>${vo.title}</td>
				<td>${vo.email}</td>
			</tr> 
		</c:forEach>
	</table>
	
	<div>
		<button onclick="location.href = './insertReport.do'">신고 글 작성</button><br>
	</div>
	
	<div>
		<form action="./searchId.do" method="post">
			<input type="text" value="USER02@NAVER.COM" name="email">
			<input type="submit" value="검색">
		</form>
	</div>
	
</div>

<div>
	<h2>jqGird 테스트 영역</h2>
	<div class="row">
        <div>
            <table id="jqGrid">
            	<tr role="row"></tr>
            </table>
            <div id="pager2"></div>
        </div>    
    </div>
</div>


</body>
</html>