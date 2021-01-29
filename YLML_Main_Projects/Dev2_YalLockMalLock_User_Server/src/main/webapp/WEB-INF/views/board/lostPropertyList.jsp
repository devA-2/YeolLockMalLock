<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/common.css">
<link rel="stylesheet" href="./css/boardCss.css">
</head>
<script type="text/javascript">
	function chkAuth(memEmail, voEmail, seq){
			if (voEmail == memEmail) {
// 				alert("동작");
				location.href='./selectOneLostProperty.do?seq='+seq;
			}else{
				alert("자신의 게시물만 확인할 수 있습니다.");
			}
		
		}
</script>
<body>
<div class="container">
	<form class="lostForm" action="./searchIdLostProperty.do" method="post">
			<input type="text" placeholder="검색할 ID를 입력하세요" name="receipt_user_id">
			<input class="btn btn-info" type="submit" value="검색">
	</form>
	<table class="table table-hover">
		<tr>
			<th>연번</th>
			<th>소유자</th>
			<th>등록일</th>
		</tr>
		<c:forEach items="${lists}" var="vo" varStatus="vs">
			<tr onclick="chkAuth('${mem.email}','${vo.receiptUserId}','${vo.seq}')">
				<td>${vo.seq}</td>
				<td>${vo.receiptUserId}</td>
				<td>${vo.lostRegdate}</td>
			</tr>
		</c:forEach>
	</table>
	
	<div class="reportBtn">
		<button class="btn btn-info" onclick="location.href='./goMainPage.do'">메인으로</button>
		
	</div>
</div>
</body>
</html>