<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
   input,textarea {
      width: 70%;
}
</style>
</head>

<body>
<!-- 메일 상황 1. 그냥 text 2.html 3.첨부파일이 있어야 한다 -->
<h2>메일 보내기</h2>
<div id="container">
   <form action="./mailSender.do" method="post">
      <div align="center">
         <input type="text" name="tomail" placeholder="상대방 이메일">
      </div>
      <div align="center">
         <input type="text" name="title" placeholder="메일 제목">
      </div>
      <div align="center">
         <textarea rows="20" cols="30" name="content" placeholder="메일 내용"></textarea>
      </div>
      <div align="center">
         <input type="submit" value="메일 보내기">
      </div>
   </form>

</div>
</body>
</html>