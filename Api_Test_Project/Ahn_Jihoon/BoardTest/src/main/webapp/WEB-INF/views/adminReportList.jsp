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
                          '제목',
                          'refer'
                     ],
          colModel : [
                    { name : 'seq', index: 'seq', width:40,  align:'center'},
                    { name : 'email', index: 'email', width:80,  align:'center'  },
                    { name : 'regdate', index: 'regdate', width:80,  align:'center'  },
                    { name : 'title', index: 'title', width:80,  align:'center' },
                    { name : 'refer', index: 'refer', width:80,  align:'center' }
            ],
            onCellSelect :function(rowId) {
            	var refer = $("#jqGrid").getCell(rowId, 'refer');
            	
                $.get("adminSelectDetailReport.do?refer="+refer, function(data) {
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


<div id="listAjax" style="width: 1000px; border: 1px solid black; text-align: center; margin: auto;">

	<div>사용중인 계정 : ${mem.email }</div>

	
	<div>
		<form action="./searchIdReport.do" method="post">
			<input type="text" value="user02@naver.com" name="email">
			<input type="submit" value="검색">
		</form>
	</div>


	
	<table style="text-align: center; margin: auto;">
		<tr>
			<th colspan="5">상세 보기 테이블</th>
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
	
		<div style="margin: auto;">
			<table id="jqGrid" style="margin: auto;"></table>
			<div id="pager2" style="margin: auto;"></div>
		</div>
        
</div>

</body>
</html>