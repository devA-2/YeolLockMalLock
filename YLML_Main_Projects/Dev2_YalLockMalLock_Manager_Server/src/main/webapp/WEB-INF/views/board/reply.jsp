<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변 글 입력</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="./css/common.css">
<script type="text/javascript">
	function goBack(){
		history.go(-1);
	}
</script>
</head>
<body>
<div id="container" style="text-align: center;">
 <%@include file="../header.jsp" %>
 
		<form action="./replyDo.do">
			<input type="hidden" name="seq" value="${dto.seq}">
			<input type="hidden" name="getEmail" value="${dto.email}">
			
			<div>
				<input type="text" style="width:500px;" name="title" value="'${dto.email}'님이 문의하신 '${dto.title}'글의 답변입니다." readonly="readonly">
			</div><br>
			
			<div>
				<div>답변자 : <input type="text" name="email" value="${mem.email}" readonly="readonly"></div><br><br>
					<select name='process' size='3' name="category">
					  <option selected>-- 처리 상태 변경 --</option>
					  <option value='complete'>처리 완료</option>
					  <option value='back'>반려 처리</option>
					</select>
			</div><br>
			
			<textarea rows="10" cols="40" id="content" name="content"></textarea>
			<br>
			<div>
				<input class="btn btn-primary" type="submit" value="작성완료">
				<input class="btn btn-primary" type="button" onclick="goBack()" value="뒤로가기">
			</div>
		</form>
	
	<%@include file="../footer.jsp" %>
</div>
</body>
</html>