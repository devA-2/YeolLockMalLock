<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style type="text/css">
#container{
   width : 300px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
<script type="text/javascript">


	function AgreeChk() {
		var arr_Agree = document.getElementsByName("agree");
		if (arr_Agree[0].checked && arr_Agree[1].checked) {
			location.href = "./signUpForm.do"
		} else {
			alert('이용약관 및 개인정보 수집 및 이용에 동의를 해주세요');
		}
	}
</script>
<body>
	<div id="container">
			<h1>회원가입</h1>
			<p>열락말락 이용약관</p>
			<textarea cols="30" rows="10"></textarea>
			
			<input type="checkbox" name="agree">동의
			
			<p>개인정보 수집 및 이용에 동의</p>
			<textarea cols="30" rows="10"></textarea>
			<input type="checkbox" name="agree">동의
			<br>
			<br>
			<input type="button" value="다음단계" onclick="AgreeChk()">
	</div>
</body>
</html>