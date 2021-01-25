<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<style type="text/css">
.input-group{
	width:100%;
	display: flex;
	z-index: 1;
}

.sidepanel  {
  width: 0;
  position: fixed;
  z-index: 2;
  height: 250px;
  top: 0;
  left: 0;
  background-color: #5a5a5a;
  overflow-x: hidden;
  transition: 0.5s;
  padding-top: 60px;
}

.sidepanel a {
  padding: 8px 8px 8px 32px;
  text-decoration: none;
  font-size: 15px;
  color: #c3c4c5;
  display: block;
  transition: 0.3s;
}

.sidepanel a:hover {
  color: #ffffff;
}

.sidepanel .closebtn {
  position: absolute;
  top: 0;
  right: 25px;
  font-size: 36px;
}

.openbtn {
  width:13.4%;
  font-size: 20px;
  cursor: pointer;
  background-color: #5a5a5a;
  color: white;
  padding: 10px 15px;
  border: none;
}

.openbtn:hover {
  background-color:#444;
}
</style>

<script type="text/javascript">
	function openNav() {
		document.getElementById("mySidepanel").style.width = "50%";
		document.getElementById("mySidepanel").style.height = "100%";
	}

	function closeNav() {
		document.getElementById("mySidepanel").style.width = "0";
	}
</script>

<div id="mySidepanel" class="sidepanel">
	<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
	<c:choose>
		<c:when test="${mem == null}">
			<a href="./member/loginForm.do">로그인</a>
			<a href="./member/infoAgree.do">회원가입</a>
		</c:when>
		<c:otherwise>
			<a>${mem.name }님 안녕하세요.</a>
			<a href="./member/logout.do">로그아웃</a>
			<a href="./member/myPage.do">마이페이지</a>
			<a href="./storage/userStorageList.do">보관 조회</a>
			<a href="./storage/deliveryListMain.do">배송</a>
		</c:otherwise>
	</c:choose>
	<a href="./reportList.do">신고</a>
</div>
<div class="input-group">
	<button class="openbtn" onclick="openNav()">☰</button>
</div>