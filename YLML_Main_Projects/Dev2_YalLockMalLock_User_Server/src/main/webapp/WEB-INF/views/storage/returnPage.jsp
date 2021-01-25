<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/common.css">
<style type="text/css">
#content{
	padding: 5%;
	text-align: center;
}
</style>
</head>
<body>
<script type="text/javascript">
	function insertReturn(){
		var msg = document.getElementById('message').value;
		if(msg==''){
			alert("반품 메세지를 입력해주세요")
		}else{
			$.ajax({
				  url : './insertReturn.do',
				  type : 'get',
				  data : {"msg":msg},
				  success : function(isc) {
					  if(isc){
						  alert("반품 신청 되었습니다");
						  location.href="./userStorageList.do";
					  }else{
						  alert("반품에 실패하였습니다. 다시시도해주세요");
						 return;
					  }
				  },
				  error : function() {
					  alert("반품에 실패 했습니다.");
				  }
			  });//ajax
		}
		
	}
	
	
</script>
<div id="container">
	<div id="content">
	<h4><br><br><br><br>보관한 상대방에게 반품 처리됩니다.<br> 반품 메세지를 입력해주세요</h4>
	<hr>
		<textarea class="form-control" rows="5" id="message" placeholder="반품합니다"></textarea>
		<hr>
		<input type="button" value="반품등록" class="btn btn-info"onclick="insertReturn()">
	
	</div>
</div>
</body>
</html>