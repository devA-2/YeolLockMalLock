<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>담당자 및 배송원 상세 페이지</title>
</head>
<body>
deliveryDetail.jsp

<!-- 값 넘어오는지 확인 -->
<%-- ${list1}<br> --%>
<%-- ${list2} --%>

<div id="container">
<hr>
<h1>담당자 및 배송원 상세조회</h1>

	<div>
	<p>아이디 : ${list1.email}</p>
	<p>이  름  : ${list1.name}</p>
	<p>전화번호 : ${list1.phone_num}</p>
	<p>등록일 : ${list1.reg_date}</p>
	<p>탈퇴일 : ${list1.leave_date}</p>
	<p>권  한 : ${list1.auth}</p>
	</div>
<hr>
<h1>배송정보</h1>
	<div>
	<p>배송코드 : ${list2.delivery_code}</p>
	<p>현재위치  : ${list2.current_loc}</p>
	<p>배송시작 : ${list2.delivery_start}</p>
	<p>배송완료 : ${list2.delivery_arrive}</p>
	</div>

<button style="color:blue; font-size: large; font-weight: bold;" onclick="#">권한 수정</button>
<hr>
<input style="font-size: large;" type="button" value="돌아가기" onclick="history.back(-1)">
</div>
</body>
</html>