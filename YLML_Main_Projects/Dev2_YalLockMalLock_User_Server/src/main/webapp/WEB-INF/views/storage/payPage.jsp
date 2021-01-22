<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
결제페이지
<form action="./afterPayment.do" method="post">
<input type="hidden" name="costCode" value="${costCode }">
<input type="submit" value="결제완료">

</form>
</body>
</html>