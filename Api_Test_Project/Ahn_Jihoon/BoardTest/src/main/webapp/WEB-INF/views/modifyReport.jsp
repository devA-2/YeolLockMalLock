<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function update(){
		var frm = document.frm;
		frm.action = "./modify.do";
		frm.submit();
	}
	
	function goHome(){
		location.href="./goReportList.do";
	}
</script>
<body>
	<form name="frm" method="get">
		<input type="hidden" name="seq" value="${dto.seq}">
			<table>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="email" value="${dto.email}" readonly></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" value="${dto.title}"></td>
	
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="content" cols="30" rows="10">${dto.content}</textarea></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td>구분</td> -->
<%-- 					<td>${dto.category}</td> <!-- 구분(처리 상태) 처리하는 if문 만들어야함 --> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>사진</td> -->
<%-- 					<td><input type="text" name="title" value="${dto.image}"></td> --%>
<!-- 				</tr> -->
				<tr>
					<td><input type="submit" value="확인" onclick="update()"></td>
					<td><input type="submit" value="뒤로가기" onclick="goHome()"></td>
				</tr>
			</table>
	</form>
</body>
</html>