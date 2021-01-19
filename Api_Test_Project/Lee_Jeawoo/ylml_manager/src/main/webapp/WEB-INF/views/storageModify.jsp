<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세정보 수정하기</title>
</head>
<body>
상세정보 수정하기<br>
<%-- ${list} --%>
<hr>

	<form action="./storageModifyRegist.do" method="post">
        <div >
             <label>보관함 ID : </label>
              <input type="text" name="storage_id" value="${list.storage_id}" readonly>
        </div>
        <div >
            <label>보관함 이름</label>
            <input type="text" name="storage_name" value="${list.storage_name}" >
        </div>
        <div >
            <label>주 소</label>
            <input type="text" name="address" value="${list.address}" >
        </div>
        <div >
            <label>지하철역</label>
            <input type="text" name="subway" value="${list.subway}" >
        </div>
        <div >
            <label>좌표 LAT</label>
            <input type="text" name="lat" value="${list.lat}" >
        </div>
        <div >
            <label>좌표 LNG</label>
            <input type="text" name="lng" value="${list.lng}" >
        </div>
        <div >
            <label>상 세</label>
            <input type="text" name="detail" value="${list.detail}" >
        </div>
        <div >
            <label>담당자</label>
            <input type="text" name="manager" value="${list.manager}" >
        </div>
        <div >
            <label>보관함 갯수</label>
            <input type="text" name="box_amount" value="${list.box_amount}" >
        </div>
        
        
        
  <hr> 
    <input style="font-size: large;" id="sub" type="submit" value="보관함 정보수정" >
    <input style="font-size: large;" type="button" value="뒤로가기" onclick="history.back(-1)">
    </form>


</body>
</html>