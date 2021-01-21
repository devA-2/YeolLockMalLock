<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송 이용 약관</title>
<script type="text/javascript">
	function agree() {
		var result = confirm("배송 하시겠습니까?")
		if(result == true){
			location.href="./searchDeliveryStation.do"
		}
	}
</script>
</head>
<body>
	<button onclick="agree()">이용약관 동의</button>
</body>
</html>