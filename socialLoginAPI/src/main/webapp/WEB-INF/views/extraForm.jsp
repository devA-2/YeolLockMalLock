<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추가 정보 입력</title>
</head>
<body>
	<form action="./extraInfo.do" method="post">
		<table>
			<tr>
				<td>이메일</td>
				<td>
					${email}
					<input type="hidden" name="email" value="${email}">
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					${name}
					<input type="hidden" name="name" value="${name}">
				</td>
			</tr>
			<tr>
				<td>핸드폰 번호</td>
				<td><input type="text" name="phone" required></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="회원가입">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>