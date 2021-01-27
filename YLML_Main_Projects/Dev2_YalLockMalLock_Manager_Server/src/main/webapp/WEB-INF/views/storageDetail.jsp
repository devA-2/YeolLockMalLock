<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	#storageDetail{
		float: left;
		width: 49%
	}

	#storageStatus{
		float: right;
		width: 49%
	}
	#statusBtn{
		height: 25px;
	}
	
	#tableStatus{
		width: 100%;
		height: 250px;
	}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="./css/common.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/managerAuth.js"></script>
<title>보관함 상세조회</title>
<script type="text/javascript">
	// box_seq = ${vs.index+1} 클릭한 버튼의 인덱스
	function statusChange(boxSeq) {
// 		console.log(box_seq);
		
		var storageId = "${list.storageId}";
// 		console.log(storage_id);
		var answer = confirm("보관함 상태를 사용가능으로 변경하시겠습니까?");
		
		if(answer){
			var url = 'activateStorage.do?boxSeq=' + boxSeq +
					  '&storageId=' + storageId;
// 			console.log(url);
			
			location.href = url;
		}
	}

</script>
</head>
<body>
<div id="container">
<%@include file="./header.jsp" %>
<div>
<h1>보관함 상세조회</h1>
<input class="btn btn-success" type="button" value="수정하기" onclick="location.href='./storageModify.do?storageId=${list.storageId}'">
<input class="btn btn-info" type="button" value="전체리스트" onclick="location.href='./allStorageList.do'">
</div>
<hr>
<div id="storageDetail">
<table border="1" class="table table-bordered">
<tr>
	<td>보관함 ID</td>
	<td>${list.storageId}</td>
</tr>
<tr>
	<td>보관함 이름</td>
	<td>${list.storageName}</td>
</tr>
<tr>
	<td>주 소</td>
	<td>${list.address}</td>
</tr>
<tr>
	<td>상세주소</td>
	<td>${list.detail}</td>
</tr>	
<tr>
	<td>지하철역</td>
	<td>${list.subway}</td>
</tr>
<tr>
	<td>좌표 LAT</td>
	<td>${list.lat}</td>
</tr> 
<tr>
	<td>좌표 LNG</td>
	<td>${list.lng}</td>
</tr>
<tr>
	<td>담당자</td>
	<td>${list.manager}</td>
</tr>
<tr>
	<td>보관함 갯수</td>
	<td>${list.boxAmount}</td>
</tr>
</table>

<%-- 	<p>보관함 ID : ${list.storageId}</p> --%>
<%-- 	<p>보관함 이름 : ${list.storageName}</p> --%>
<%-- 	<p>주 소 : ${list.address}</p> --%>
<%-- 	<p>상세주소 : ${list.detail}</p> --%>
<%-- 	<p>지하철역 : ${list.subway}</p> --%>
<%-- 	<p>좌표 LAT : ${list.lat}</p> --%>
<%-- 	<p>좌표 LNG : ${list.lng}</p> --%>
<%-- 	<p>담당자 : ${list.manager}</p> --%>
<%-- 	<p>보관함 갯수 : ${list.boxAmount}</p> --%>
<c:choose>
<c:when test="${empty lists}">
	<p style="font-size: large;">상태를 표시할 보관함이 없습니다.</p>
</c:when>
</c:choose>
</div>	
<c:choose>
<c:when test="${!empty lists}">
	<div id="storageStatus">
<table border="1" id="tableStatus" class="table table-bordered">
	<tbody>
	<tr>
		<th>보관함 번호</th>
		<th>보관함 상태</th>
		<th>보관함 상태변경</th>
	</tr>
	
<c:forEach varStatus="vs" items="${lists}" var="dto">
	<tr>
		<td align="center">${dto.boxSeq}</td>
		<td align="center">${dto.boxStatus}</td>
		<td align="center">
			<button id="statusBtn" class="btn btn-warning" onclick="statusChange(${vs.index+1})">보관함 상태변경</button>
		</td>
	</tr>
</c:forEach>
</tbody>
</table>
	</div>
</c:when>

<c:otherwise>
	<p>상태를 표시할 보관함이 없습니다.</p>
</c:otherwise>	
</c:choose>	


 <%@include file="./footer.jsp" %>
</div>

</body>
</html>