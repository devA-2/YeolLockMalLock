<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보관함 상세조회</title>
</head>
<body>
<%-- ${list} --%>
<h1>보관함 상세조회</h1>
<hr>
<div>
	<p>보관함 ID : ${list.storage_id}</p>
	<p>보관함 이름 : ${list.storage_name}</p>
	<p>주 소 : ${list.address}</p>
	<p>지하철역 : ${list.subway}</p>
	<p>좌표 LAT : ${list.lat}</p>
	<p>좌표 LNG : ${list.lng}</p>
	<p>상 세 : ${list.detail}</p>
	<p>담당자 : ${list.manager}</p>
	
</div>




<!-- 	<div> -->
<!-- 	<table border="1"> -->
<!-- 		<tr> -->
<!-- 			<th>보관함 ID</th> -->
<!-- 			<th>보관함 이름</th> -->
<!-- 			<th>주 소</th> -->
<!-- 			<th>지하철역</th> -->
<!-- 			<th>좌표 LAT</th> -->
<!-- 			<th>좌표 LNG</th> -->
<!-- 			<th>상 세</th> -->
<!-- 			<th>담당자</th> -->
<!-- 		</tr> -->
<!-- 			<tr> -->
<%-- 				<td>${list.storage_id}</td> --%>
<%-- 				<td>${list.storage_name}</td> --%>
<%-- 				<td>${list.address}</td> --%>
<%-- 				<td>${list.subway}</td> --%>
<%-- 				<td>${list.lat}</td> --%>
<%-- 				<td>${list.lng}</td> --%>
<%-- 				<td>${list.detail}</td> --%>
<%-- 				<td>${list.manager}</td> --%>
<!-- 			</tr> -->
<!-- 	</table> -->
<!-- 	</div> -->
<hr>
<input style="font-size: large;" type="button" value="수정하기" onclick="location.href='./storageModify.do?storage_id=${list.storage_id}'">
<input style="font-size: large;" type="button" value="전체리스트로" onclick="location.href='./allStorageList.do'">
</body>
</html>