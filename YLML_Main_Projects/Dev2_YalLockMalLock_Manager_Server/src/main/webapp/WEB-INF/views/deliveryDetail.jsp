<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	#deliveryDetail{
		float:left;
		width: 49%;
	}
	
	#deliveryInfo{
		width:49%;
		float: right;
	}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="./css/header.css">
<script type="text/javascript" src="./js/managerAuth.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<title>담당자 및 배송원 상세 페이지</title>
<script type="text/javascript">

	function authCheck() {
		var chk = "<c:out value='${list1.auth}'/>";
		
		console.log(chk);
		
		if(!(chk==89||chk==99)){
			alert("권한이 이미 변경되었거나 변경할 수 없는 권한입니다.");
		}else{
			var email = "<c:out value='${list1.email}'/>";
			
			location.href="./modifyAuth.do?email="+email;
		}
	}

	function authChange() {
		var cnfm = confirm("임시권한을 변경하시겠습니까?");
		
		if(cnfm){
			authCheck();
		}
	}
</script>
</head>
<body>

<div id="container">
<%@include file="./header.jsp" %>
<div>
<button class="btn btn-success" onclick="authChange()">임시권한 변경</button>
<input class="btn btn-primary" type="button" value="전체리스트" onclick="location.href='./allDeleveryList.do'">
</div>
<hr>
	<div id="deliveryDetail">
<h1>담당자 및 배송원 상세조회</h1>
	<table  border="1" class="table table-bordered">
		<tr>
			<td>아이디</td>
			<td>${list1.email}</td>
		</tr>
		<tr>
			<td>이  름</td>
			<td>${list1.name}</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>${list1.phoneNum}</td>
		</tr>
		<tr>
			<td>등록일</td>
			<td>${list1.regDate}</td>
		</tr>
		<tr>
			<td>탈퇴일</td>
			<td>${list1.levDate}</td>
		</tr>
		<tr>
			<td>권  한</td>
			<td>${list1.auth}</td>
		</tr>
	</table>	

<%-- 	<p>아이디 : ${list1.email}</p> --%>
<%-- 	<p>이  름  : ${list1.name}</p> --%>
<%-- 	<p>전화번호 : ${list1.phoneNum}</p> --%>
<%-- 	<p>등록일 : ${list1.regDate}</p> --%>
<%-- 	<p>탈퇴일 : ${list1.levDate}</p> --%>
<%-- 	<p>권  한 : ${list1.auth}</p> --%>
	</div>
	
	<div id="deliveryInfo" >
<h1>배송정보</h1>
	<table  border="1" class="table table-bordered">
		<tr>
			<td>배송코드</td>
			<td>${list2.deliveryCode}</td>
		</tr>
		<tr>
			<td>현재위치</td>
			<td>${list2.currentLoc}</td>
		</tr>
		<tr>
			<td>배송시작</td>
			<td>${list2.deliveryStart}</td>
		</tr>
		<tr>
			<td>배송완료</td>
			<td>${list2.deliveryArrive}</td>
		</tr>
	</table>

<%-- 	<p>배송코드 : ${list2.deliveryCode}</p> --%>
<%-- 	<p>현재위치  : ${list2.currentLoc}</p> --%>
<%-- 	<p>배송시작 : ${list2.deliveryStart}</p> --%>
<%-- 	<p>배송완료 : ${list2.deliveryArrive}</p> --%>
	</div>

<%@include file="./footer.jsp" %>
</div>
</body>
</html>