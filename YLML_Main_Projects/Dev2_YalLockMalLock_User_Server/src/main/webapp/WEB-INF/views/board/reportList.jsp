<%@page import="com.dev2.ylml.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">

<title>신고글 게시판 목록</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>

</head>
<script type="text/javascript">
	function chkAuth(memEmail, voEmail, auth, refer){
	if (memEmail == voEmail) {
		location.href="./selectDetailReport.do?refer="+refer;
	}else{
		alert("자신의 게시물만 확인할 수 있습니다.");
	}
	
	}
	
      // 익명함수, 페이지 로드될 때 실행됨 -> 전체리스트 호출
      $(function() {
         $.get("reportListAjax.do", function(data) {
            console.log(data);
            $('#tbody').html(data);
         });
      });
      
</script>

<body>

<div style="width: 360px; height: 600px; border: 1px solid black; margin:auto;">
<div>${mem.email}, ${mem.auth }</div>
<%-- 	<% --%>
	
<!-- 	  	Object obj = session.getAttribute("mem");  -->
<!-- 	 	MemberDto mem = (MemberDto)obj; -->
	
		
<!-- 	 	if(Integer.parseInt(mem.getAuth()) == 30){ -->
<!-- 	 		%>  -->
<!-- 	 		<div>관리자 권한</div>  -->
<%-- 			<%  --%>
<!-- 		}else if(Integer.parseInt(mem.getAuth()) == 10){ -->
<%-- 	  		%>  --%>
<!-- 	 		<div>일반 사용자</div> -->
<%-- 	 		<%  --%>
<!-- 	  	}   -->
<%-- 	%>  --%>

	<table class="table table-hover">
		<tr>
			<th>SEQ</th>
			<th>작성 일자</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
<%-- 		<c:forEach items="${lists}" var="vo" varStatus="vs"> --%>
<%-- 			<tr onclick="chkAuth('${mem.email}', '${vo.email}', '${mem.auth}', '${vo.refer}')"> --%>
<!-- <!-- 			<tr> -->
<%-- 				<td>${vo.seq}</td> --%>
<%-- 				<td>${vo.regdate}</td> --%>
<%-- 				<td>${vo.title}</td> --%>
<%-- 				<td>${vo.email}</td> --%>
<!-- 			</tr>  -->
<%-- 		</c:forEach> --%>
		<tbody id="tbody">
		</tbody>
	</table>
	
	<div>
		<button onclick="location.href = './insertReport.do'">신고 글 작성</button><br>
	</div>
	
	<div>
		<form action="./searchIdReport.do" method="post">
			<input type="text" value="USER02@NAVER.COM" name="email">
			<input type="submit" value="검색">
		</form>
	</div>
</div>
</body>
</html>