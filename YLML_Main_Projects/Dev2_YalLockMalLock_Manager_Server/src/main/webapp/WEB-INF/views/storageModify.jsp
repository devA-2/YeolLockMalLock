<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	#storageRegistTb{
		width: 730px;
	}

	#inputWindow{
		width: 600px;
	}
	
	#formTable{
		margin-left: 290px;
	}
	
	#sub{
		margin-left: 270px;
	}
	
	#stgModify{
		text-align: center;
	}
	
	
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link type="text/css" rel="stylesheet" href="./css/header.css">
<script type="text/javascript" src="./js/managerAuth.js"></script>
<title>상세정보 수정하기</title>
</head>
<body>
<div id="container">
<%@include file="./header.jsp" %>
<div id="stgModify"><h1>보관함 상세정보 수정</h1></div>
<hr>

	<form id="formTable" action="./storageModifyRegist.do" method="post">
		
	<table border="1" id="storageRegistTb" class="table table-bordered">
		<tr>
			<td>보관함 ID</td>
			<td><input id="inputWindow" type="text" name="storageId" value="${list.storageId}" readonly></td>
		</tr>
		<tr>
			<td>보관함 이름</td>
			<td><input id="inputWindow" type="text" name="storageName" value="${list.storageName}" ></td>
		</tr>
		<tr>
			<td>주 소</td>
			<td><input id="inputWindow" type="text" name="address" value="${list.address}" ></td>
		</tr>
		<tr>
			<td>상세주소</td>
			<td><input id="inputWindow" type="text" name="detail" value="${list.detail}" ></td>
		</tr>	
		<tr>
			<td>지하철역</td>
			<td><input id="inputWindow" type="text" name="subway" value="${list.subway}" ></td>
		</tr>
		<tr>
			<td>좌표 LAT</td>
			<td><input id="inputWindow" type="text" name="lat" value="${list.lat}" ></td>
		</tr> 
		<tr>
			<td>좌표 LNG</td>
			<td><input id="inputWindow" type="text" name="lng" value="${list.lng}" ></td>
		</tr>
		<tr>
			<td>담당자</td>
			<td><input id="inputWindow" type="text" name="manager" value="${list.manager}" ></td>
		</tr>
		<tr>
			<td>보관함 갯수</td>
			<td><input id="inputWindow" type="text" name="boxAmount" value="${list.boxAmount}" readonly></td>
		</tr>
	</table>
		
    <input id="sub" class="btn btn-success" type="submit" value="보관함 정보수정" >
    <input class="btn" type="button" value="뒤로가기" onclick="history.back(-1)">
    </form>

<%@include file="./footer.jsp" %>
</div>
</body>
</html>