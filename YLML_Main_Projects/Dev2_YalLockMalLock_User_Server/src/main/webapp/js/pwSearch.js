//아작스로 이름과 아이디가 일치하는지 검사후 인증버튼을 활성화 시켜줌 
//일치하는지 검사 후에는 readonly로 수정 못하게 해야함
//인증하기 부분은 숨겨야함 

//이메일 검사 정규식
var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
var timer = null;
var isRunning = false;

$(document).ready(function() {
//	$("#pwResetBtn").attr("disabled", true);
	$("#sendSMS").hide();
	$("#checkSMS").hide();
	$('#pwSearchBtn').click(function(){

		$.ajax({
			type: 'post',
			url : './pwSearch.do',
			data: 
				"name="+document.getElementById('name').value+"&email="+document.getElementById('email').value,
				success: function(data){
					console.log('Ajax for pwSearch : ' + data);
					if(data > 0){
						$("#pwSearchBtn").hide();
//						$("#pwSearchBtn").attr("disabled", true);
						$("#sendPhone_num").removeAttr("disabled");
						$("#email").attr("readonly",true);
						$("#name").attr("readonly",true);
						$("#sendSMS").show();
					}else if(mailJ.test(email)){
						alert('유효하지 않은 이메일 입니다. 다시 시도해주세요.')
					}else{
						alert('해당하는 정보의 회원이 없습니다.')
						$("#pwResetBtn").attr("disabled", true);
					}
				}

		});
	});

	$("#email").blur(function() {
		if ($('#email').val() != '') {
			$("#pwSearchBtn").removeAttr("disabled");
		} 				
	});

	$("#sendPhone_num").attr("disabled",true);

	$('#sendPhone_num').click(function(){
		let phoneNumber = $('#phone_num').val();
		alert('인증번호 발송 완료!');
		$("#phone_num").attr("readonly",true);
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
					if($('#inputCertified_num').val()=='') {
						alert('값을 입력하세요.');
					} else if(isRunning && $.trim(res) ==$('#inputCertified_num').val()){
						// 타이머가 활성화 되어있고 값이 정확히 입력되었을 때
						alert('휴대폰 인증이 정상적으로 완료되었습니다.');
						clearInterval(timer);
						display.html("");
						$("#sendSMS").hide();

					}else{
						if(isRunning) {
							// 타이머가 활성화 되어있고 인증번호가 틀렸을때
							alert('인증번호가 맞지 않습니다.');
						} else {
							// 타이머가 활성화 되어 있지 않을때
							alert('시간이 초과되었습니다.');
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
						isRunning = false;
					}
				}, 1000);
		isRunning = true;
	}

});	