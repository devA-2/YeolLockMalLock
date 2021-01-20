<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보관함 등록</title>
</head>
<body>
registStorage.jsp<br>
보관함 등록<br>
<hr>

<form action="registNewStorage.do" method="post">
        <div >
             <label>보관함 ID : </label>
              <input type="text" name="storage_id" placeholder="보관함 ID를 입력하세요" required="required">
        </div>
        <div >
            <label>보관함 이름</label>
            <input type="text" name="storage_name" placeholder="보관함 이름을 입력하세요"  required="required">
        </div>
        <div >
            <label>주 소</label>
            <input type="text" name="address" placeholder="보관함 주소를 입력하세요"  required="required">
        <div >
            <label>상세주소</label>
            <input type="text" name="detail" placeholder="상세내용을 입력하세요" required="required">
        </div>
        </div>
        <div >
            <label>지하철역</label>
            <input type="text" name="subway" placeholder="지하철역을 입력하세요" required="required">
        </div>
        <div >
            <label>좌표 LAT</label>
            <input type="text" name="lat" placeholder="좌표 LAT를 입력하세요" required="required">
        </div>
        <div >
            <label>좌표 LNG</label>
            <input type="text" name="lng" placeholder="좌표 LNG를 입력하세요" required="required">
        </div>
        <div >
            <label>담당자</label>
            <input type="text" name="manager" placeholder="담당자 이름을 입력하세요" required="required">
        </div>
        
        
  <hr> 
    <input style="font-size: large;" id="sub" type="submit" value="등록하기" >
    <input style="font-size: large;" type="button" value="전체리스트로" onclick="location.href='./allStorageList.do'">
    </form>

</body>
</html>