function exchangeBtn(form) {
	// 키교환할 수령 사용자 입력하기
	form.action='./updateOutUser.do';
	form.submit();
}

function deliveryBtn(num) {
	var OX = document.getElementById("OX"+num).innerText
	var frm = document.getElementById("storeForm"+num)
	if(OX == "O"){
		alert("이미 배송 신청한 물품 입니다.")
	}else if(OX == "X"){
		frm.action='./deliveryForm.do';
		frm.submit();
	}
}
   
function extendBtn(num) {
	//연장하기
	var frm = document.getElementById("receiptForm"+num)
	var id = frm.elements['storageId'].value
	var boxSeq = frm.elements['boxSeq'].value
	if(confirm('연장 비용은 2시간에 1500원 입니다. 연장하시겠습니까?')){
		$.ajax({
		  url : './updateExtend.do',
		  type : 'get',
		  data : { "id" : id ,
			  		"boxSeq" : boxSeq},
		  success : function(isc) {
			  if(isc){
				  alert("연장 되었습니다");
				  location.href="./userStorageList.do";
			  }
			  
		  },
		  error : function() {
			  alert("연장에 실패했습니다.");
		  }
	  });//ajax
	

	}//if문 
}
   
function paymentBtn(form) {
//	var frm = document.getElementById("receiptForm"+num)
	//결제전 키 대조 화면으로 이동 
	form.action='./beforePay.do';
	form.submit();
}