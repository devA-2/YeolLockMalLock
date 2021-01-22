$("#check_module").click(function () {
	var cost = document.getElementById("cost").value
	var phoneNum = document.getElementById("phoneNum").value
	var IMP = window.IMP; // 생략해도 괜찮습니다.
	IMP.init('imp40532383'); // 'imp60827137' 대신 부여받은 "가맹점 식별코드"를 사용 (테스트용 식별코드 imp40532383)
	IMP.request_pay({ // param
		pg: 'kakao', // 카카오
		pay_method: 'trans', // 실시간 계좌이체
		merchant_uid: 'merchant_' + new Date().getTime(), // 주문번호(merchant_uid) 생성하기
		//IMP.request_pay를 호출하기 전에 여러분의 서버에 주문 정보를 전달(데이터베이스에 주문정보 INSERT)하고 서버가 생성한 주문 번호를 param의 merchant_uid속성에 지정
		name: '열락말락',
		amount: cost,
		buyer_email: '',
		buyer_name: '',
		buyer_tel: phoneNum,
		buyer_postcode: '',
		m_redirect_url: 'http://localhost:8095/nerdhead-archetype-webapp-custom/resultPayment.do'
	});
});
