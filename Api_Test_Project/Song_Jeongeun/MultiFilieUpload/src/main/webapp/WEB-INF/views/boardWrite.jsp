<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 글 쓰기</title>
</head>
<body>
	<h2>새 글 쓰기</h2>
	<form>
		<table>
			<!-- 제목 -->
			<tr>
				<td>제목</td>
				<td>
					<input type="text" id="writeTitle">
				</td>
			</tr>
			
			<!-- 이미지 업로드 -->
			<tr>
				<td>업로드 파일</td>
				<td>
					<input type="file" name="files" id="uploadFile" accept="image/*" multiple />
				</td>
			</tr>
		
			<!-- 이미지 썸네일 -->
			<tr>
				<td colspan="2">
					<div id="thumbnail"></div>
				</td>
			</tr>
	
			<!-- 본문 -->
			<tr>
				<td>내용</td>
				<td>
					<textarea id="content" placeholder="사진에 대해서 설명해주세요."></textarea>
				</td>
			</tr>
	
			<!-- 업로드 버튼 -->
			<tr>
				<td colspan="2">
					<input type="button" value="업로드" onclick="upload()">
					<input type="button" value="글 목록 이동" onclick="listMove()">
				</td>
			</tr>
		</table>
	</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="./js/upload.js"></script>
<script type="text/javascript">
	function listMove(){
		location.href="./boardList.do"
	}
</script>
</body>
</html>