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
.container {
	width: 360px;
	height: 600px;
	border: 1px solid black;
	margin: auto;
}

div.btn {
	width: 90px;
	height: 90px;
	margin: 10px;
}

.storageBox {
	width: 360px;
	height: 360px;
}

.docu {
	height: 500px;
	overflow: scroll;
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
		<input type="hidden" name="email" value="${mem.email}">
<!-- 		<input type="hidden" name="email" value="user01@naver.com"> -->
		<input type="hidden" id='id' name="id" value="${storageInfo.value}">
		<input type="hidden" name="boxSeq" id="boxSeq" value="0">
		<input type="button" class="btn btn-info btn-lg" onclick="clickCheck()" value="다음">
<!-- 모달시작 -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-dialog-scrollable" role="document">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h5 class="modal-title">보관 이용 약관</h5>
        </div>
        <div class="modal-body">
        <pre class="docu">제1조(목적) 이 약관은 서울도시철도엔지니어링 주식회사(이하 “당사”라 합니다)와
물품보관함을 이용하는 개인 또는 법인 간에 물품의 보관・전달 및 이에 따르는 책
임에 관한 사항을 정하는 것을 목적으로 합니다.

제2조(정의) 이 약관에서 사용하는 용어의 뜻은 다음 각 호와 같습니다.
1. “보관함“이란 당사가 직접 운영하는 보관, 물류(물품전달, 이동, 택배 등), 광
고, 홍보 등이 가능한 설비를 말합니다.
2. “이용자” 란 보관함 및 그 기능을 적용한 서비스를 이용하는 개인 또는 법인을
말합니다.
3. “정기이용서비스”란 이용자가 보관함을 월 또는 연간 단위 등의 기간을 정하여
이용할 것을 약정한 이용 방식을 말합니다.
4. “경과품”이란 보관일로부터 5일 이상 경과한 보관품을 말합니다.
5. “해피박스콜센터(이하 ”콜센터“라 합니다)”란 보관함 이용자의 편의증진을
위하여 사용방법 안내, 민원사항 처리 등의 업무를 수행하는 장소를 말합니다.

제3조(계약의 성립) ① 보관함 이용자와 당사와의 계약은 다음 각 호의 경우에 성
립되며, 이 경우 이용자는 이 약관의 내용 및 이 약관을 근거로 정한 내용에 동
의한 것으로 봅니다.
1. 이용요금 선불인 경우(보관함 제어부 이용자) : 이용자가 제어부에서 결제 후
해당 보관함에 물품을 보관 후 닫은 때
2. 이용요금 후불인 경우(핸드폰 사용자) : 이용자가 해당 보관함에 핸드폰번호를
입력하여 보관 후 시스템에서 비밀번호 문자를 전송한 때
3. 정기이용 서비스 경우 : 이용자의 신청을 접수하고 운영시스템에서 해당 보관함의
비밀번호를 전송 한 때
② 이용요금의 적용은 요금표에 의합니다.
③ 약관 이용의 별도 약정에 의한 이용계약의 성립시기는 해당 약정에서 정한 바에
따릅니다.

제4조(보관함 이용) ① 이용자는 물품을 보관․전달 또는 찾기를 할 경우 약관
내용을 숙지하고 이용절차를 준수하여야 합니다.
② 보관함의 크기보다 큰 물품을 넣고 문을 억지로 밀어서 닫으면 보관함이 파손될
수 있고 회수 시 문이 열리지 않을 수 있으니 주의하시기 바랍니다.
③ 이용자의 보관함 이용은 통상적으로 최장 5일 이내로 하며, 5일을 초과하여 보관
하고자 하는 경우 콜센터로 문의 바랍니다.
④ 당사는 이용자가 제공한 개인정보는 본인 인증 이외의 목적으로 사용하지 아니하며,
보관함 이용이 종료되는 경우 자동으로 삭제합니다. 단 범죄의 수사를 위하여 수사
기관의 요청이 있을 시 거래기록, CCTV 영상등을 제공할 수 있습니다.
⑤ 물품을 찾을 때 이용자 인증장애가 발생한 경우 이용자는 즉시 콜센터에 연락
하여 직원의 안내에 따라야 하며, 아래 각호에 따라 처리할 수 있습니다.
1. 비밀번호 분실 또는 사용미숙으로 인한 인증장애 발생 시 당사는 고객 정보를
요구할 수 있고, 제공받은 정보로 이용자의 신분확인을 진행할 수 있습니다.
2. 현장 확인이 필요할 경우 현장관리자 또는 역직원 등의 도움을 받아 처리할
수 있습니다.
⑥ 이용자가 물품을 넣고 보관할 경우 보관함의 쇄정상태를 반드시 확인하여야 하며,
사용자의 부주의로 물품이 분실될 경우 당사는 물품에 대한 책임을 지지 않습니다.

제5조(영수증 발행) ① 당사는 이용자가 보관함 이용 후 영수증을 요구하면 당사는 다
음 각 호에서 정한 방법으로 발행합니다.
1. 이용자 출력 : 온라인 발행(WEB, E-MAIL)
2. 당사 출력 : 콜센터 내 프린터 이용한 출력
3. 기타 모바일을 이용한 전송(문자 전송)
② 이용자는 영수증 발행을 위해 사용한 기본정보(역명, 사용일시, 함번호, 결재수단,
사용시 비밀번호)를 당사에 알려주셔야 합니다.

제6조(이용자의 의무) ① 이용자는 물품보관함의 이용에 관한 권리를 제3자에게
양도하거나 질권 설정 등 보관함의 통상적인 이용을 제한하는 권리를 설정할
수 없습니다.
② 사용중인 보관함은 이용자 임의로 이동할 수 없습니다.

제7조(이용의 거절) 당사는 다음 각 호의 경우에 보관함 이용을 거절할 수 있습니다.
1. 공공질서를 해칠 우려가 있는 경우
2. 범죄용으로 사용될 우려가 있는 경우
3. 시민안전에 저해될 우려가 있는 경우

제8조(장애신고) ① 보관함 이용 중 발생한 장애 등은 콜센터 또는 현장관리자
에게 신고하여야 하며, 보관함을 임의로 파손, 훼손 시 변상하여야 합니다.
② 장애 발생 시 현장관리자 등의 현장 출동 시간은 신고접수 후 2시간 이내에
하도록 하며, 09시～18시까지 입니다. 그 외 시간대에 발생한 장애는 콜센터 또는
현장관리자에게 안내를 받기 바랍니다.
③ 과대물품 보관에 따른 보관함 개방불가 및 파손, 비밀번호 분실 또는 기타 이
용자의 과실로 당사 관리자가 출동한 경우 출장비 및 수리비 등을 실비로 지급하여
야 합니다.

제9조(보관금지 물품) ① 당사는 다음 각 호에 해당하는 물품의 보관을 금지하며,
이를 위반하여 발생한 손해 또는 배상책임은 이용자가 부담하여야 하고 민․형사상의 책
임을 집니다.
1. 현금, 유가증권, 귀금속 또는 10만원 이상의 귀중품
2. 총포류, 흉기, 시위용품(화염병 등), 마약류 등
3. 폭발물, 인화성 물질, 위험물 및 독극물 등
4. 악취, 불결한 물품, 부패 또는 변질 우려가 있는 식품류 등
5. 동물, 사체, 부피가 팽창하는 물품
6. 중량 30kg이상의 물품 또는 보관함 문을 강제로 닫을 정도의 부피가 큰 물품
7. 역내에서 금지하는 노숙자, 잡상 행위를 위한 물품
8. 기타 시민안전에 저해되는 물품
② 당사는 보관금지 물품의 보관 여부 또는 관리상 필요한 경우 보관함을 임시 열
기를 하여 점검을 할 수 있습니다.
③ 당사는 보관금지 물품 발견 시 관계기관 신고, 임의반출, 폐기처분 등 필요한
조치를 취하며, 이용자는 이로 인한 손해를 당사에 청구 할 수 없습니다.

제10조(경과품의 처리) ① 보관일로부터 5일 이상 된 경과품은 당사에서 수거하여
별도 장소에 보관할 수 있습니다.
② 경과품은 평일 09:00～18:00에 당사에서 지정한 장소에서 찾을 수 있습니다.
③ 경과품을 수령하고자 할 때의 보관료는 이용요금표에 준합니다.
④ 경과품은 최장 1개월 보관하되 별도의 연기신청 등의 의사표시가 없을 경우
당사에서 임의로 처분할 수 있으며 이용자는 이에 이의를 제기할 수 없습니다.

제11조(배상책임) 이용자의 과실로 본인 또는 타인에게 피해가 발생하거나 보관함의
파손, 보관품 분실 등이 발생할 경우 배상 책임은 이용자에게 있습니다.

제12조(면책) 다음 각 호에 의하여 발생한 손해는 당사에서 책임을 지지 않습니다.
단, 당사의 과실에 의한 피해는 사실 확인을 거쳐 최대 10만원까지 보상합니다.
1. 천재지변, 전쟁, 기타 불가항력적인 사유로 인한 손해
2. 이용자의 부주의로 인한 손해(비밀번호관리, 결제, 보관함 조작 미숙 등)
3. 보관금지 물품을 보관하여 발생한 손해
4. 보관기간이 종료된 경과품의 임의 처리 시
5. 장애 신고 후 출동한 직원 현장 도착 전에 고객부주의로 발생한 손해
6. 수사기관이 필요에 의하여 보관한 물품 압수 시
7. 물품의 보관 사실이 입증되지 않는 경우
8. 보관품 모두를 찾지 않아 발생한 물품 분실사고

제13조(유지보수) ① 당사는 원활한 서비스 제공을 위하여 필요한 경우 보관함의 보
수 및 점검을 행할 수 있으며, 부득이한 경우 서비스를 일시 중지 할 수 있습니다.
② 당사는 보다 나은 서비스를 위하여 시스템 개량 또는 이용방법 등의 개선이 필
요할 경우 서비스 내용을 변경할 수 있습니다.
③ 당사는 고객의 불편사항이 접수된 경우 이를 신속하게 처리하되, 신속한 처리가
곤란할 경우 이용고객에게 사유 및 처리예정 시간 등을 통보합니다.

제14조(콜센터 이용안내) 기타 서비스 이용 등에 관한 문의사항은 콜센터(운영시간
06:00～24:00)에 문의하시기 바랍니다.

부 칙

(시행일) 이 약관은 당사의 설립등기일부터 
                           </pre>
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


