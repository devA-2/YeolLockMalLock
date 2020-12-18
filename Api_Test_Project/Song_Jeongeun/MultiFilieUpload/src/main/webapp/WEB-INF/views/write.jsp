<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<style type="text/css">
#preImg {
	width: 500px;
	height: 300px;
}
</style>
</head>
<body>
	<form>
		<table>
			<!-- 제목 -->
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="title" />
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
					<button type="button" class="btn btn-primary" id="btn-save" onclick="upload()">올리기</button>
				</td>
			</tr>
		</table>
	</form>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="./js/upload.js"></script>
</html>