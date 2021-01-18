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
</script>

<!-- jqGrid 테스트 스크립트 -->
<script type="text/javascript">
var $Grid = {};
var rowNum = 1000;
var selectUrl = "./selectDetailReport.do";
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
                    { name : 'email', index: 'email', width:80,  align:'center'  },
                    { name : 'regdate', index: 'regdate', width:80,  align:'center'  },
                    { name : 'title', index: 'title', width:80,  align:'center' },
            ],
            onCellSelect :function(rowId) {
            	var seq = $("#jqGrid").getCell(rowId, 'seq');
            	
                $.get("selectOneReportAjax.do?seq="+seq, function(data) {
                    console.log(data);
                    $('#listAjax').html(data);
                 });
              }
              ,
	          rowNum : rowNum,
	          pager : '#pager2',
	          width : '700',
	          height : 'auto'

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

	function replyGo(seq){
		location.href='./replyReport.do?seq='+seq;
	}

</script>

<body>
        <div>
            <table id="jqGrid"></table>
            <div id="pager2"></div><br>
        </div>

<div id = "listAjax">

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

	<div>
		<button onclick="location.href = './insertReport.do'">신고 글 작성</button><br>
	</div>
	
	<div>
		<form action="./searchId.do" method="post">
			<input type="text" value="USER02@NAVER.COM" name="email">
			<input type="submit" value="검색">
		</form>
	</div>


	
	<table style="text-align: center;">
		<tr>
			<th colspan="5">상세 보기 표시 테이블</th>
		</tr>
		<tr>
			<th>seq</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일자</th>
			<th>내용</th>
		</tr>
		<tr>
			<td>${dto.seq }</td>
			<td>${dto.email }</td>
			<td>${dto.title }</td>
			<td>${dto.regdate }</td>
			<td>${dto.content }</td>
		</tr>
		<tr>
			<td colspan="5">
				<button onclick="replyGo(${dto.seq})">답변 글 작성</button>
			</td>
		</tr>
	</table>
        
</div>

</body>
</html>