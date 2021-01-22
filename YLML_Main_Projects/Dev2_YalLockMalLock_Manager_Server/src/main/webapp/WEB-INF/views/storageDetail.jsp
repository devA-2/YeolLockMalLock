<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.js"></script>
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
<%-- ${list} --%>
<h1>보관함 상세조회</h1>
<hr>
<div>
	<p>보관함 ID : ${list.storageId}</p>
	<p>보관함 이름 : ${list.storageName}</p>
	<p>주 소 : ${list.address}</p>
	<p>상세주소 : ${list.detail}</p>
	<p>지하철역 : ${list.subway}</p>
	<p>좌표 LAT : ${list.lat}</p>
	<p>좌표 LNG : ${list.lng}</p>
	<p>담당자 : ${list.manager}</p>
	<p>보관함 갯수 : ${list.boxAmount}</p>
</div>
<hr>

<div>
<%-- ${lists} --%>
	
<c:choose>
<c:when test="${!empty lists}">
<div></div>
<table border="1">
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
			<button onclick="statusChange(${vs.index+1})">보관함 상태변경</button>
		</td>
	</tr>
</c:forEach>
</tbody>
</table>
	
</c:when>
<c:otherwise>
	<p>상태를 표시할 보관함이 없습니다.</p>
</c:otherwise>	
</c:choose>	

</div>


<hr>
<input style="font-size: large;" type="button" value="수정하기" onclick="location.href='./storageModify.do?storageId=${list.storageId}'">
<input style="font-size: large;" type="button" value="전체리스트" onclick="location.href='./allStorageList.do'">
</body>
</html>