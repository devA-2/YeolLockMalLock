<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">

<title>상세 글</title>
<style type="text/css">
	#container{
		width: 600px;
		height: 300px;
 		border: 1px solid black; 
		text-align: center;
		margin: auto;
	}
</style>
<script type="text/javascript">
	function historys(){
		history.back();
	}
</script>

</head>
<body>
	<div id="container">
		<table>
			<tr>
				<td>${dto.title}</td>
				<td>${dto.email}</td>
				<td>${dto.category }</td>
				<td>${dto.process_status}</td>
				<td>${dto.content }</td>
				<td>${dto.image}</td>
			</tr>
		</table>
		<input type="button" value="수정하기" onclick="location.href='./modifyReport.do?seq=${dto.seq}'">
		<button onclick="historys()">뒤로 가기</button>
	</div>
</body>
</html>