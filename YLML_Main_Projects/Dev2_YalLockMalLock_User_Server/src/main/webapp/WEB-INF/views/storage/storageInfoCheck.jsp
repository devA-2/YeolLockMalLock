<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보관정보 확인</title>
<style type="text/css">
.container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
</head>
<body>
<script type="text/javascript">
// var date = new Date();
// var sysdate = date.getFullYear()+'.'+(date.getMonth()+1)+'.'+date.getDate();
// var systime = date.getHours()+':'+date.getMinutes();
// var systime4;
// if(date.getHours()>19){
//	   systime4 = '24:00';
// }else{
//	   systime4 = date.getHours()+4+':'+date.getMinutes();
// }

//// 		   var storageName = document.getElementById('id').value;
// var boxSeq = document.getElementById('boxSeq').value;
// document.getElementById('storageInfo').innerHTML= '<h4>${storageInfo.label }보관함 '+boxSeq+' </h4>';
// document.getElementById('storageTime').innerHTML=sysdate+' '+systime+'->'+sysdate+' '+systime4;
// document.getElementById('storageCost').innerText='기본 4시간 : 2000원';
</script>
<div class="container">
보관함 ${map.id }<br>
보관함 번호 ${map.boxSeq }<br>
시간출력 <br>
기본 4시간 : 2000원

<input type="button" value="보관" onclick="location.href='./NFCtag.do'">	
<input type="button" value="메인" onclick="location.href='./map.do'">	
</div>
</body>
</html>