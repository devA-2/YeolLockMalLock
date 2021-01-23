<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
#container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
<script type="text/javascript">


</script>

<title>Test Index</title>
</head>
<body>
<div>
   <div id="container">
   	<c:if test="${mem != null}">
		<p>${mem.name }님 안녕하세요.</p>
		<p><a href="./logout.do">로그아웃</a></p>
		<hr>
		<a href="./allMember.do">회원전체조회</a><br>
	</c:if>				
   <!-- 여기서부터 필요한 화면은 각자 작성 및 테스트 하시면 됩니다 -->
   <hr>
   <c:if test="${mem == null}">
	   <a href="./loginForm.do">로그인</a><br>
	   <hr>
	   <a href="managerMain.do">바로 메인으로</a><br>
	   <hr>
   </c:if>
   </div>
</div>
</body>
</html>