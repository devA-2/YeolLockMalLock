<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변 글 입력</title>
<script type="text/javascript">
	function goBack(){
		history.go(-1);
	}
</script>
</head>
<body>
	<form action="./replyDo.do">
		<input type="hidden" name="seq" value="${dto.seq}">
		
		<div>
			<input type="text" style="width:500px;" name="title" value="'${dto.email}'님이 문의하신 '${dto.title}'글의 답변입니다." readonly="readonly">
		</div>
		<div>
		<input type="text" name="email" value="asd" readonly="readonly">
			<select name='process' size='3' name="category">
			  <option selected>-- 처리 상태 변경 --</option>
			  <option value='complete'>처리 완료</option>
			  <option value='back'>반려 처리</option>
			</select>
		</div>
		<textarea rows="10" cols="40" id="content" name="content"></textarea>
		<div>
			<input type="submit" value="작성완료">
			<input type="button" onclick="goBack()" value="뒤로가기">
		</div>
	</form>
</body>
</html>