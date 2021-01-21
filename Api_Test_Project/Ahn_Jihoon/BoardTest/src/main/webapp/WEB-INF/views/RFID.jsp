<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- <hr> -->
<!-- <h2>1-1과 1-2는 트랜젝션으로 묶어서 한동작으로 만들어줘야 합니다.</h2> -->
<!-- <div>1-1. 키 제외하고 물품부터 등록</div> -->
<!-- <form action="./insertStorage.do" method="post"> -->
<!-- 		<input type="text" value="1" name="box_seq" readonly="readonly"><br> -->
<!-- 		<input type="text" value="CITYHALL_A" name="storage_id" readonly="readonly"><br> -->
<!-- 		<input type="text" value="jamza2@naver.com" name="in_user" readonly="readonly"><br> -->
<!-- 		<input type="text" value="jamza2@naver.com" name="out_user" readonly="readonly"><br> -->
	
<!-- 		<input type="submit" value="확인"> -->
<!-- </form> -->
<hr>
<div>1-2. 물품 보관 입력 후 키 입력 동작입니다.<br>이건 물품 보관 정보 입력하는거 찾아서 트렌젝션으로 묶어줘야합니다.</div>
<form action="./insertKey.do" method="post">
	<table>
		<tr>
			<th>보관함 번호</th>
			<th>보관하는 사람 아이디</th>
		</tr>
		<tr>
			<td><input type="text" value="2" name="box_seq" readonly="readonly"></td>
			<td><input type="text" value="jamza2@naver.com" name="in_user" readonly="readonly"></td>
		</tr>
	</table>
	<input type="submit" value="키 입력">
</form>

<hr>
<h2>2-1과 2-2는 트랜젝션으로 묶어서 한동작으로 만들어줘야합니다.</h2>
<div>2-1. STORAGE_GOODS의 OUT_USER를 키를 받는사람(out_user)의 email로 변경</div>
<form action="./updateOutUser.do" method="post">
	<table>
		<tr>
			<th>보관함 번호</th>
			<th>키를 받는 사람 아이디</th>
			<th>보관함 이름(상세 주소)</th>
		</tr>
		<tr>
			<td><input type="text" value="2" name="box_seq" readonly="readonly"></td>
			<td><input type="text" value="user02@naver.com" name="out_user" readonly="readonly"></td>
			<td><input type="text" value="CITYHALL_A" name="storage_id" readonly="readonly"></td>
		</tr>
	</table>
	<input type="submit" value="키 전송">
</form>

<hr>
<div>2-2. STORAGE_GOODS의 KEY를 out_user의 키로 재설정해주기</div>
<form action="./updateKey.do" method="post">
	<table>
		<tr>
			<th>보관함 번호</th>
			<th>키를 받는 사람 아이디</th>
		</tr>
		<tr>
			<td><input type="text" value="2" name="box_seq" readonly="readonly"></td>
			<td><input type="text" value="user02@naver.com" name="out_user" readonly="readonly"></td>
		</tr>
	</table>
	<input type="submit" value="키 전송">
</form>

</body>
</html>