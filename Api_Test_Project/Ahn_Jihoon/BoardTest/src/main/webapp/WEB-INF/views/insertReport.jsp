<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>신고 글 작성</title>
<script type="text/javascript">
	function goBack(){
		history.go(-1);
	}
</script>
</head>
<body>
	<form action="./insert.do" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" value="${mem.email}" name="email"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" placeholder="제목" name="title"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="10" cols="40" id="content" name="content"></textarea>
<!-- 					<input type="text" placeholder="내용" name="content"> -->
				</td>
			</tr>
			<tr>
				<th>카테고리<th>
				<td><input type="text" value="1" name="category"></td>
			</tr>
			<tr>
				<th>이미지<th>
				<td><input type="text" value="1" name="image"></td>
			</tr>
			
			<tr>
			<td>
				<input type="submit" value="확인">
				<input type="button" onclick="goBack()" value="뒤로가기">
			</td>
			</tr>
		</table>
	</form>
</body>
</html>