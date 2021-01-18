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

<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="./css/ui.jqgrid.css" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />

<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="./js/grid.locale-kr.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
var rowNum = 5;
$(document).ready(function(){
     $Grid = $('#jqGrid');
     $Grid.jqGrid({
          url : 'jqGrid.do',
          datatype : "json",
          mtype : "get",
          jsonReader : {
               id : "seq" // 대표 아이디를 설정
               ,root : "employee" // 데이터의 시작을 설정
          },
          colNames : [
                          'seq',
                          '아이디',
                          '작성일',
                          '제목'
                     ],
          colModel : [
                    { name : 'seq', index: 'seq', width:40,  align:'center'},
                    { name : 'email', index: 'email', width:80,  align:'left'  },
                    { name : 'regdate', index: 'regdate', width:80,  align:'left'  },
                    { name : 'title', index: 'title', width:80,  align:'right' },
            ],
          rowNum : rowNum,
          pager : '#pager2'
//           multiselect : true
    });
});

// sample code // collback
// function getData(callbackFunc) {
//     $.get('jqGrid.do', function(response) {
//         callbackFunc(response); // 서버에서 받은 데이터 response를 callbackFunc() 함수에 넘겨줌
//     });
// }

// getData(function(tableData) {

	
//     console.log(tableData); // $.get()의 response 값이 tableData에 전달됨
// });

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
        <div>
            <table id="jqGrid"></table>
            <div id="pager2"></div><br>
        </div>
</div>


</body>
</html>