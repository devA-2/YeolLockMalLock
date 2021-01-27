<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
  request.setCharacterEncoding("UTF-8");
  response.setContentType("text/html; charset=UTF-8");
  String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>

<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>신고 글 작성</title>
<script type="text/javascript">
	function goBack(){
		history.go(-1);
	}
	
	var isShow = true;
	function validateForm() {
		var form = document.forms[0];
		var email = document.getElementById("id");
		var title = document.getElementById("title");
		var content = document.getElementById("content"); // 위치 탐색
		content.value = oEditors.getById["content"].getIR(); // 값을 가져와서 content에 넣어줌

		//trim 어떻게 하는지 모르겠다 (공백 (&nbsp;)만 들어갈 때 유효성 검사)
		//글등록때 뜨는 alert(사이트 나가시겠습니까?) 없애기: oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);를 적용하면 해결된다.
		
		if (email.value == "" || title.value == "" || content.value == "" || content.value == "<p><br></p>") { // 위치탐색 후 값을 넣어준뒤 비어있는 값인지 확인 (유효성 검사)
			alert("필수 값을 입력해 주세요");
		} else {
//				var str = content.value;
//				str = str.replace(/(?:\r\n|\r|\n)/g,"<br>");
			//값 확인
//				ir1.textContent = str;
//				document.getElementById("resultView").textContent = str;
			isShow = false;
			oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
			form.submit();
		}
	}
	
	window.onbeforeunload = function() {
		if (isShow) {				
		return alert("화면을 벗어 납니다.");
		}
	}
</script>
</head>
<body>
	<form action="./insert.do" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" value="${mem.email}" name="email"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" placeholder="제목" name="title"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="10" cols="40" id="content" name="content"></textarea>
<!-- 					<input type="text" placeholder="내용" name="content"> -->
				</td>
			</tr>
			<tr>
				<th>카테고리<th>
				<td><input type="text" value="1" name="category"></td>
			</tr>
			<tr>
				<th>이미지<th>
				<td><input type="text" value="1" name="image"></td>
			</tr>
			
			<tr>
			<td>
				<input class="btn btn-primary active" type="button" value="새글저장" onclick="validateForm()">
				<input type="button" onclick="goBack()" value="뒤로가기">
			</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
 oAppRef: oEditors,
 elPlaceHolder: "content",
//SmartEditor2Skin.html 파일이 존재하는 경로
 sSkinURI: "SE2/SmartEditor2Skin.html",
 fCreator: "createSEditor2"
});

function writeReset(){
		oEditors.getById["content"].exec("SET_IR", [""]);
}
</script>
</html>