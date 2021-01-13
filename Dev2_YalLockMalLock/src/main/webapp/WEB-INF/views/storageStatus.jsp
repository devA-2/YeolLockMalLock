<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">

</style>
</head>
<body>
	<script type="text/javascript">
	function clickBox(seq){
		//클릭했을때  노란색으로, 노란색을 누른거면 원상태로 , 노란색 있는상태에서 다른걸 누르면 그게 노란색으로 다른건 파란색으로
		var seqFinal = document.getElementById('boxSeq').value;
		if(seqFinal == 0){
			document.getElementById(seq).className='btn btn-warning';
			document.getElementById('boxSeq').value = seq;
		}else if(seqFinal == seq){
			document.getElementById(seq).className = 'btn btn-primary';
			document.getElementById('boxSeq').value = 0;
		}else{
			document.getElementById(seqFinal).className = 'btn btn-primary';
			document.getElementById(seq).className='btn btn-warning';
			document.getElementById('boxSeq').value = seq;
		}
	}
</script>
	<form action="./insertGoods.do" method="post">
			<c:forEach var="box" items="${statusList}">
				<c:choose>
					<c:when test='${box.boxStatus eq "O"}'>
						<div id="${box.boxSeq}" class="btn btn-primary" onclick="clickBox(${box.boxSeq})"></div>
					</c:when>
					<c:otherwise>
						<div id="${box.boxSeq}" class="btn btn-danger"></div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
		<input type="hidden" name="email" value="user02@naver.com">
		<input type="hidden" name="id" value="${id}">
		<input type="hidden" name="boxSeq" id="boxSeq" value="0">
		<input type="submit" value="다음">
	</form>
	

</body>
</html>
<!-- [StorageBoxDto(boxSeq=1, storageId=null, boxStatus=O),  -->
<!-- StorageBoxDto(boxSeq=2, storageId=null, boxStatus=O),  -->
<!-- StorageBoxDto(boxSeq=3, storageId=null, boxStatus=O)] -->
<!-- 하나만 체크되어야함 -->
<!-- O 일때만 클릭할수 있고 I,X,W일때는 클릭못함 -->
<!-- 클릭시 boxSeq, storageId, email 가지고 jsp 두개 거친뒤 보관 ! -->


