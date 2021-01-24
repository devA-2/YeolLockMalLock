<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
  request.setCharacterEncoding("UTF-8");
  response.setContentType("text/html; charset=UTF-8");
  String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>신고 글 작성</title>
<script type="text/javascript" src="<%=ctx %>/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	function goBack(){
		if (confirm("글을 입력하지 않고 돌아가시겠습니까?")==true) {
			location.href="./reportList.do?";
		}else{
			return;
		}
	}
	
	var isShow = true;
	function validateForm() {
		var form = document.forms[0];
		var email = document.getElementById("email");
		var title = document.getElementById("title");
		var content = document.getElementById("ir1"); // 위치 탐색
		content.value = oEditors.getById["ir1"].getIR(); // 값을 가져와서 content에 넣어줌

		//trim 어떻게 하는지 모르겠다 (공백 (&nbsp;)만 들어갈 때 유효성 검사)
		//글등록때 뜨는 alert(사이트 나가시겠습니까?) 없애기: oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);를 적용하면 해결된다.
		
// 		if (email.value == "" || title.value == "" || content.value == "" || content.value == "<p><br></p>") { // 위치탐색 후 값을 넣어준뒤 비어있는 값인지 확인 (유효성 검사)
// 			alert("필수 값을 입력해 주세요");
// 		} 
		
		if (title.value=="") {
			alert("제목을 입력 해 주세요.");
		}else if(content.value == "<p><br></p>"){
			alert("내용을 입력 해 주세요.");
		}else {
// 				var str = content.value;
// 				str = str.replace(/(?:\r\n|\r|\n)/g,"<br>");
			//값 확인
// 				ir1.textContent = str;
// 				document.getElementById("resultView").textContent = str;
			isShow = false;
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
			form.submit();
		}
	}
	
	window.onbeforeunload = function() {
		if (isShow) {				
		return alert("화면을 벗어 납니다.");
		}
	}
	
	
</script>
</script>
</head>
<body>
<div id="resultView">
</div>
<div id="container" style="width: 360px; height: 600px; border: 1px solid black; margin:auto;">
	<div>
		<form action="./insert.do" method="post" id="chk">
					<br><br>
					<span>아이디 : <input type="text" value="${mem.email}" name="email" id="email"></span><br>
					<span>제목 : <input type="text" placeholder="제목" name="title" id="title"><div id="chkTitle"></div></span>
					
						<textarea rows="10" cols="40" id="ir1" name="ir1"></textarea>
<!-- 						<textarea rows="10" cols="40" id="content" name="content"></textarea> -->
<!-- 						<span><input type="text" placeholder="내용" name="content" id="content"></span> -->
						
					<span>카테고리 : <input type="text" value="1" name="category" id="category"></span>
<!-- 					<tr> -->
<!-- 					<th>이미지<th> -->
<!-- 					<td><input type="text" value="1" name="image" id="image"></td> -->
<!-- 					</tr> -->
					<br><br>
					

					
					
					
					<input type="button" value="확인" onclick="validateForm()">
					<input type="button" onclick="goBack()" value="뒤로가기">
		</form>
	</div>
</div>
</body>
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
 oAppRef: oEditors,
 elPlaceHolder: "ir1",
//SmartEditor2Skin.html 파일이 존재하는 경로
 sSkinURI: "SE2/SmartEditor2Skin.html",
 fCreator: "createSEditor2"
});

function writeReset(){
		oEditors.getById["ir1"].exec("SET_IR", [""]);
}
</script>
</html>