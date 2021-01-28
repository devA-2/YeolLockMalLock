<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="./css/ui.jqgrid.css" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />

<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="./js/grid.locale-kr.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="./js/jquery.jqGrid.min.js"></script>

<style type="text/css">
	div > div{
		margin:auto;
	}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="./css/common.css">

</head>
<script type="text/javascript">
var $Grid = {};
var rowNum = 1000;
$(document).ready(function(){
     $Grid = $('#jqGrid');
     $Grid.jqGrid({
          url : 'jqGridLostProperty.do',
          datatype : "json",
          mtype : "get",
          jsonReader : {
               id : "seq" // 대표 아이디를 설정
               ,root : "employee" // 데이터의 시작을 설정
          },
          colNames : [
                          'seq',
                          '소유자',
                          '작성일',
                          '만기일'
                     ],
          colModel : [
                    { name : 'seq', index: 'seq', width:40,  align:'center'},
                    { name : 'receiptUserId', index: 'receiptUserId', width:80,  align:'center'  },
                    { name : 'lostRegdate', index: 'lostRegdate', width:80,  align:'center'  },
                    { name : 'andDate', index: 'andDate', width:80,  align:'center' }
            ],
            onCellSelect :function(rowId) {
            	var seq = $("#jqGrid").getCell(rowId, 'seq');
            	
                $.get("selectOneLostProperty.do?seq="+seq, function(data) {
                    console.log(data);
                    $('#listAjax').html(data);
                 });
              }
              ,
	          rowNum : rowNum,
	          pager : '#pager2',
	          width : '700',
	          height : 'auto'

    });
     
});

function historys(){
	location.href="./managerMain.do";
}
	
</script>
<body>
<div id="container">
 <%@include file="../header.jsp" %>
	<div id="listAjax" style="text-align: center;">
		<div>
			<div>
				<button onclick="historys()" class="btn btn-primary">메인으로</button><br><br>
				<form action="./searchIdLostProperty.do" method="post">
					<input type="text" placeholder="검색할 ID 입력" name="receiptUserId">
					<input type="submit" class="btn btn-primary" value="검색">
				</form>
			</div>
			<br>
			<div style="margin: auto;">
				<table id="jqGrid" style="margin: auto;"></table>
				<div id="pager2" style="margin: auto;"></div>
			</div>
		</div>
	</div>
	<%@include file="../footer.jsp" %>
</div>
</body>
</html>