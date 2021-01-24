
// 휴대폰 번호 정규식
var phoneJ = /(01[016789])(\d{4}|\d{3})\d{4}$/g; 

var timer = null;
var isRunning = false;

$(document).ready(function() {
	$("#sendPhone_num").attr("disabled",true);
	$("#sendSMS").hide();
	$("#checkSMS").hide();
//	$("#updateInfoBtn").hide();

	$("#phone_num").blur(function(){ 

		if($('#phone_num').val() == ''){
			$('#phoneChk').text('휴대폰 번호를 입력하세요.');
			$('#phoneChk').css('color', 'red');

		}else if($('#phone_num').val() != ''){

			var phone_num = $('#phone_num').val();

			$.ajax({
				async: true,
				type: 'POST',
				data: {
					"phone" : phone_num
				},
				url : './phoneCheck.do',
				dateType: 'json',
				success: function(data){
					console.log('Ajax for phoneCheck :' + data);	

					if(data > 0){
						$('#phoneChk').text('중복된 휴대폰 번호 입니다.');
						$('#phoneChk').css('color','red');
					} else {
						if(phoneJ.test(phone_num)){
							$('#phoneChk').text('사용가능한 휴대폰 번호 입니다.');
							$('#phoneChk').css('color', 'blue');
							$("#sendPhone_num").removeAttr("disabled");
						}
						else if(phone_num == ''){
							$('#phoneChk').text('휴대폰 번호를 입력해주세요.');
							$('#phoneChk').css('color', 'red');
							$("#sendPhone_num").attr("disabled",true);

						} else if(! phoneJ.test(phone_num)){
							$('#phoneChk').text("휴대폰 번호 형식이 맞지 않습니다 '-'을 제외하고 입력해주세요.");
							$('#phoneChk').css('color', 'red');
							$("#sendPhone_num").attr("disabled",true);
						}
					}  

				}

			}); // ajax
		} // else if

	}); // blur
	$('#sendPhone_num').click(function(){
		let phoneNumber = $('#phone_num').val();
		alert('인증번호 발송 완료!');
		$("#phone_num").attr("readonly",true);
		$("#sendPhone_num").attr("disabled",true);
		$("#checkSMS").show();

		var display = $('.time');
		var leftSec = 120;
		// 남은 시간
		// 이미 타이머가 작동중이면 중지
		if (isRunning){
			clearInterval(timer);
			display.html("");
			startTimer(leftSec, display);
		}else{
			startTimer(leftSec, display);

		}

		$.ajax({
			type: "POST",
			url: "./sendSMS.do",
			data: {"phoneNumber" : phoneNumber}, // 핸드폰 값이 넘어감
			success: function(res){ // 인증번호 값이 넘어옴
				$('#checkBtn').click(function(){
					if($('#certified_num').val()=='') {
						alert('값을 입력하세요.');
					} else if(isRunning && $.trim(res) ==$('#certified_num').val()){
						// 타이머가 활성화 되어있고 값이 정확히 입력되었을 때
						alert('휴대폰 인증이 정상적으로 완료되었습니다.');
						clearInterval(timer);
						display.html("");
						$("#sendSMS").hide();
						$("#updateInfoBtn").removeAttr("disabled");
						$("#updateInfoBtn").show();

					}else{
						if(isRunning) {
							// 타이머가 활성화 되어있고 인증번호가 틀렸을때
							alert('인증번호가 맞지 않습니다.');
							$("#updateInfoBtn").hide();
						} else {
							// 타이머가 활성화 되어 있지 않을때
							alert('시간이 초과되었습니다.');
							$("#updateInfoBtn").hide();
						}
					}
				})
			}
		})
	});
	//--------------------타이머

	function startTimer(count, display) {
		var minutes, seconds;
		timer = setInterval(
				function () {
					minutes = parseInt(count / 60, 10);
					seconds = parseInt(count % 60, 10);

					minutes = minutes < 10 ? "0" + minutes : minutes;
					seconds = seconds < 10 ? "0" + seconds : seconds;

					display.html(minutes + ":" + seconds);

					// 타이머 끝
					if (--count < 0) {
						clearInterval(timer);
						alert("시간초과");
						display.html("시간초과");
						$('#checkBtn').attr("disabled","disabled");
						$("#updateInfoBtn").hide();
						isRunning = false;
					}
				}, 1000);
		isRunning = true;
	}

});	