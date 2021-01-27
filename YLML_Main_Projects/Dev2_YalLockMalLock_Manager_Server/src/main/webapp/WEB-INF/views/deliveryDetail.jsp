<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

</script>
</head>
<body>

<div id="container">
<hr>
<h1>담당자 및 배송원 상세조회</h1>

	<div>
	<p>아이디 : ${list1.email}</p>
	<p>이  름  : ${list1.name}</p>
	<p>전화번호 : ${list1.phoneNum}</p>
	<p>등록일 : ${list1.regDate}</p>
	<p>탈퇴일 : ${list1.levDate}</p>
	<p>권  한 : ${list1.auth}</p>
	</div>
<hr>
<h1>배송정보</h1>
	<div>
	<p>배송코드 : ${list2.deliveryCode}</p>
	<p>현재위치  : ${list2.currentLoc}</p>
	<p>배송시작 : ${list2.deliveryStart}</p>
	<p>배송완료 : ${list2.deliveryArrive}</p>
	</div>
	
<button style="color:blue; font-size: large; font-weight: bold;" onclick="authCheck()">임시권한 변경</button>
<hr>
<input style="font-size: large;" type="button" value="전체리스트로" onclick="location.href='./allDeleveryList.do'">
</div>
</body>
</html>