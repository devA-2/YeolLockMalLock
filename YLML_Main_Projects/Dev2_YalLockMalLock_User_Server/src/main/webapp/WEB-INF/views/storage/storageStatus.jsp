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
.container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
div.btn{
	width: 90px;
	height: 90px;
	margin : 10px;
}
.storageBox{
	width: 360px;
	height: 360px;
}

</style>
</head>
<body>
	<script type="text/javascript">
		//클릭했을때  노란색으로, 노란색을 누른거면 원상태로 , 노란색 있는상태에서 다른걸 누르면 그게 노란색으로 다른건 파란색으로
	function clickBox(seq){
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
	//보관함 선택안했으면 alert, 아니면 모달띄우기
	function clickCheck(){
		if(document.getElementById('boxSeq').value == 0){
			alert('사용할 보관함을 선택해주세요');
		}else{
// 			updateModal();
			$('#myModal').modal();
		}
	}	
	

	function insertGoods(form){
		form.submit();
	}
	
</script>
<div class="container">
	<h1>${storageInfo.label }</h1>
	<h4>${storageInfo.desc }</h4>
	<hr>
	<form action="./storageInfoCheck.do" method="post">
		<div class="storageBox">
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
		</div>
<%-- 		<input type="hidden" name="email" value="${mem.email}"> --%>
		<input type="hidden" name="email" value="user01@naver.com">
		<input type="hidden" id='id' name="id" value="${storageInfo.value}">
		<input type="hidden" name="boxSeq" id="boxSeq" value="0">
		<input type="button" class="btn btn-info btn-lg" onclick="clickCheck()" value="다음">
<!-- 모달시작 -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h5 class="modal-title">보관 이용 약관</h5>
        </div>
        <div class="modal-body">
			이용약관 !@@!@!!!!!!!!!!!!!!!!!!~~~~~~~~~~~~~~~~~
        </div>
        <div class="modal-footer">
          <input type="button" class="btn btn-success" data-dismiss="modal" value="동의" onclick="insertGoods(this.form)">
        </div>
      </div>
      
    </div>
  </div>
<!--   모달끝 -->
	</form>
	

</div>

	
	
	

</body>
</html>
<!-- [StorageBoxDto(boxSeq=1, storageId=null, boxStatus=O),  -->
<!-- StorageBoxDto(boxSeq=2, storageId=null, boxStatus=O),  -->
<!-- StorageBoxDto(boxSeq=3, storageId=null, boxStatus=O)] -->
<!-- 하나만 체크되어야함 -->
<!-- O 일때만 클릭할수 있고 I,X,W일때는 클릭못함 -->
<!-- 클릭시 boxSeq, storageId, email 가지고 jsp 두개 거친뒤 보관 ! -->


