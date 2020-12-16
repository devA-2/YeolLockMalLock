 <%@page import="java.util.List"%>
<%@page import="com.min.edu.vo.KakaoVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%
    	KakaoVo vo = (KakaoVo)request.getAttribute("list");
    	
    %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 아임포트 라이브러리 추가 : jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- 아임포트 라이브러리 추가 : iamport.payment.js -->
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>

        <title>테스트 페이지</title>
        <style type="text/css">
        	div{
        		text-align: center;
        	}
        </style>
    </head>

    <body>
    
	<div>
		<h1>테스트 결제</h1>
    	<input id="check_module" type="button" value="결제하기">
	</div>

	 <script type="text/javascript">
    $("#check_module").click(function () {
        var IMP = window.IMP; // 생략해도 괜찮습니다.
        IMP.init('imp40532383'); // 'imp60827137' 대신 부여받은 "가맹점 식별코드"를 사용 (테스트용 식별코드 imp40532383)
        IMP.request_pay({ // param
            pg: 'kakao', // 카카오
            /*
            'kakao':카카오페이,
            html5_inicis':이니시스(웹표준결제)
            'nice':나이스페이
            'jtnet':제이티넷
            'uplus':LG유플러스
            'danal':다날
            'payco':페이코
            'syrup':시럽페이
            'paypal':페이팔
            */
            pay_method: 'trans',
            /*
            'samsung':삼성페이,
            'card':신용카드,
            'trans':실시간계좌이체,
            'vbank':가상계좌,
            'phone':휴대폰소액결제
            */
            merchant_uid: 'merchant_' + new Date().getTime(), // 주문번호(merchant_uid) 생성하기
            //IMP.request_pay를 호출하기 전에 여러분의 서버에 주문 정보를 전달(데이터베이스에 주문정보 INSERT)하고 서버가 생성한 주문 번호를 param의 merchant_uid속성에 지정
            name: 'CU',
            amount: <%=vo.getPrice()%>,
            buyer_email: '<%=vo.getEmail()%>',
            buyer_name: '<%=vo.getName()%>',
            buyer_tel: '<%=vo.getPhone()%>',
            buyer_addr: '<%=vo.getAddress()%>',
            buyer_postcode: '123-456',
            m_redirect_url: 'https://admin.iamport.kr/payments/complete'
        }, function (rsp) { // callback
            console.log(rsp);
            if (rsp.success) {
                var msg = '결제가 완료되었습니다.';
                msg += '고유ID : ' + rsp.imp_uid;
                msg += '상점 거래ID : ' + rsp.merchant_uid;          // 결제 성공 시 로직,
                msg += '결제 금액 : ' + rsp.paid_amount;
                msg += '카드 승인번호 : ' + rsp.apply_num;
            } else {
                var msg = '결제에 실패하였습니다.';                      // 결제 실패 시 로직,
                msg += '에러내용 : ' + rsp.error_msg;
            }
            alert(msg);
            /*
            	function (rsp) { // callback
				    if (rsp.success) { // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
				      // jQuery로 HTTP 요청
				      jQuery.ajax({
				          url: "https://www.myservice.com/payments/complete", // 가맹점 서버
				          method: "POST",
				          headers: { "Content-Type": "application/json" },
				          data: {
				              imp_uid: rsp.imp_uid,
				              merchant_uid: rsp.merchant_uid
				          }
				      }).done(function (data) {
				        // 가맹점 서버 결제 API 성공시 로직
				      })
				    } else {
				      alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
				    }
            */
        });
    });
   
</script>
    </body>

    </html>