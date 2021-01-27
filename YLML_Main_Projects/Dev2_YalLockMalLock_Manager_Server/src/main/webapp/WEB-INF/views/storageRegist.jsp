<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
	
	#stgRegist{
		text-align: center;
	}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>보관함 등록</title>
<link type="text/css" rel="stylesheet" href="./css/header.css">
</head>
<script type="text/javascript" src="./js/managerAuth.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<body>
<div id="container">
<%@include file="./header.jsp" %>
<hr>
<div id="stgRegist"><h1>보관함 등록</h1></div>
<hr>
<form id="formTable" action="registNewStorage.do" method="post">
		
	<table border="1" id="storageRegistTb" class="table table-bordered">
		<tr>
			<td>보관함 ID</td>
			<td><input id="inputWindow" type="text" name="storageId" placeholder="보관함 ID를 입력하세요" required="required"></td>
		</tr>
		<tr>
			<td>보관함 이름</td>
			<td><input id="inputWindow" type="text" name="storageName" placeholder="보관함 이름을 입력하세요"  required="required"></td>
		</tr>
		<tr>
			<td>주 소</td>
			<td><input id="inputWindow" type="text" name="address" placeholder="보관함 주소를 입력하세요"  required="required"></td>
		</tr>
		<tr>
			<td>상세주소</td>
			<td><input id="inputWindow" type="text" name="detail" placeholder="상세내용을 입력하세요" required="required"></td>
		</tr>
		<tr>
			<td>지하철역</td>
			<td><input id="inputWindow" type="text" name="subway" placeholder="지하철역을 입력하세요" required="required"></td>
		</tr>
		<tr>
			<td>좌표 LAT</td>
			<td><input id="inputWindow" type="text" name="lat" placeholder="좌표 LAT를 입력하세요" required="required"></td>
		</tr>
		<tr>
			<td>좌표 LNG</td>
			<td><input id="inputWindow" type="text" name="lng" placeholder="좌표 LNG를 입력하세요" required="required"></td>
		</tr>
		<tr>
			<td>담당자</td>
			<td><input id="inputWindow" type="text" name="manager" placeholder="담당자 이름을 입력하세요" required="required"></td>
		</tr>
	</table>
        
    <input id="sub" class="btn btn-success" type="submit" value="등록하기" >
    <input class="btn btn-primary" type="button" value="전체리스트로" onclick="location.href='./allStorageList.do'">
    </form>
    
<%@include file="./footer.jsp" %>    
</div>
</body>
</html>