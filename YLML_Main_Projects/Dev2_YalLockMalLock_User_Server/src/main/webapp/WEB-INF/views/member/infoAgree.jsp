<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="../js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" href="../css/common.css"> -->
<style type="text/css">
#container {
	width: 100%; 
	border: 1px solid black;
}

#content {
	width: 97%;
 	margin-left: 1.5%;
	margin-right: 1.5%; 
	text-align: center;
}

h1.info{
	font-weight: 600;
}
</style>
</head>
<script type="text/javascript">


	function AgreeChk() {
		var arr_Agree = document.getElementsByName("agree");
		if (arr_Agree[0].checked && arr_Agree[1].checked) {
			location.href = "./signUpForm.do"
		} else {
			alert('이용약관 및 개인정보 수집 및 이용에 동의를 해주세요');
		}
	}
</script>
<body>
	<div id="container">
		<div id="content">
			<h1 class="info">회원가입</h1>
			<h3>열락말락 이용약관</h3>
			<textarea cols="46" rows="10" placeholder="Disabled" disabled style="resize: none;">
			
			</textarea><br>
			
			<input type="checkbox" name="agree">동의
			
			<h3>개인정보 수집 및 이용에 동의</h3>
			<textarea cols="46" rows="10" placeholder="Disabled" disabled style="resize: none;">
1. 수집하는 개인정보 항목 및 수집목적
① 회사는 회원가입, 회원 관리, 결제 등 각종
서비스의 제공을 위해 개인정보를 수집하고 있습니다.
-  간편로그인  및 일반회원 가입: 온라인 성명, 
아이디(이메일), 비밀번호, 연락처(휴대폰 or
전화번호) 회원관리, 회원특성에 따른 서비스 이용
회원탈퇴시까지(단, 관계법령에 따름)
2. 추가 수집 유형
- 자동 생성 정보: IP Address, 쿠키, 방문
일시, OS종류, 브라우져 종류, 단말기 모델,
이동통신사 정보, 하드웨어 ID, 단말기 OS 
종류, 회원번호, PC 모바일 여부, 시간
- 유료서비스 이용 시 : 신용카드 결제시 : 카드사,
카드번호(마스킹), 할부개월
- 휴대전화 결제시 : 휴대폰번호
- 계좌이체시 : 은행명, 입금자명
- 환불시 : 환불계좌정보(은행명, 계좌번호, 예금주명)
3. 모바일 앱 서비스 이용 시	목적에 따라 이동통신단말기 내
 정보 및 기능에 접근하여 이용
- 필수 접근 항목 : 앱 설치시 또는 최초 실행 시 안내 및 동의
- 선택 접근 항목 : 서비스 최초 이용 시 별도 동의
② 회사는 이용자가 제공한 모든 정보를 상기 목적에 필요한 용도
이외로는 사용하지 않으며, 이용 목적이 변경될 시에는 사전에
별도 동의를 구할 것입니다.
4. 개인정보의 보유 및 이용기간
이용자의 개인정보는 원칙적으로 회원탈퇴 후 1년까지 보관되며
그 이후에는 삭제됩니다. 단, 다음의 정보에 대해서는 아래의
이유로 명시한 기간 동안 보존합니다.
① 관계 법령에 의한 정보보유 사유
‘상법’, ‘전자상거래 등에서의 소비자보호에 관한 법률’ 등
 관계 법령의 규정에 의하여 보존할 필요가 있는 경우 관계 법령
 에서 정한 일정한 기간 동안 개인정보를 보관합니다.
이 경우 회사는 보관하는 정보를 그 보관의 목적으로만 이용하며
 보존 기간은 아래와 같습니다.
1. 계약 또는 청약철회 등에 관한 기록
- 보존 근거 : 전자상거래 등에서의 소비자보호에 관한 법률
- 보존 기간 : 5년
2. 대금결제 및 재화 등의 공급에 관한 기록
- 보존 근거 : 전자상거래 등에서의 소비자보호에 관한 법률
- 보존 기간 : 5년
3. 소비자의 불만 또는 분쟁처리에 관한 기록
- 보존 근거 : 전자상거래 등에서의 소비자보호에 관한 법률
- 보존 기간 : 3년
4. 웹사이트 방문기록
- 보존 근거 : 통신비밀보호법
- 보존 기간 : 3개월
				
이외에 개인정보 수집 및 이용에 문의사항이 있으실 경우
 아래 연락처로 문의 바랍니다.
- gdproject2077@gmail.com 
- 전화번호 : 1544-1544
			</textarea><br>
			<input type="checkbox" name="agree">동의
			<br>
			<br>
			<input type="button" value="다음단계" class="btn btn-info" onclick="AgreeChk()">
		</div>
	</div>
</body>
</html>