function exchangeBtn(form) {
	// 키교환할 수령 사용자 입력하기
	form.action='./outUserForm.do';
	form.submit();
}

function deliveryBtn(boxSeq, storageId, categoryCode) {
	$.ajax({
		type:"post",
		url: "./deliveryBtn.do",
		data: {"boxSeq":boxSeq, "storageId":storageId, "categoryCode":categoryCode},
		success : function(result){
			if(result == "success"){
				location.href = "./deliveryForm.do"
			}else{
				console.log("이미 배송 중인 물품입니다.")
			}
		},
		error : function(){
			alert("서비스에 문제가 생겼습니다.")      
		}
	});
}
   
function extendBtn(form) {
	//연장하기
	if(confirm('연장 비용은 2시간에 1500원 입니다. 연장하시겠습니까?')){
		form.action='./updateExtend.do';
		form.submit();
	}
}
   
function paymentBtn(form) {
	//결제전 키 대조 화면으로 이동 
	form.action='./compareKey.do';
	form.submit();
}