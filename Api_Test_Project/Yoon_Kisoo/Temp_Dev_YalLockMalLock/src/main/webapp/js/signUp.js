
//TODO : 유효성검사 지금 블러로 진행중인데, keyup으로 특정 자릿수를 입력했을때 동작하는게 괜찮아보임 <br> 회원가입을 용이하게 하기위해 버튼을 비활성화함 <br> 유효성 검사 수정이 필요함. 현재 입력값이 없어도 진행이 됨

//유효성검사
//모든 공백 체크 정규식 
var empJ = /\s/g;

// 이메일 검사 정규식
var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

// 비밀번호 정규식 
var pwJ = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;

// 휴대폰 번호 정규식
var phoneJ = /(01[016789])(\d{4}|\d{3})\d{4}$/g; 
var timer = null;
var isRunning = false;

$(document).ready(function(){
	
	$("#sendPhone_num").attr("disabled",true);
//	$("#signUpBtn").attr("disabled",true); // 회원가입 테스트 용이하게 하기 위함
	
	        $('#sendPhone_num').click(function(){
	            let phoneNumber = $('#phone_num').val();
	            alert('인증번호 발송 완료!');
	        	$("#phone_num").attr("readonly",true);
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
//				        		signUpBtn.disabled=false;
				        		
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

	// 아이디(이메일) 중복확인
	$("#email").blur(function(){ 
	
		if($('#email').val() == ''){
			$('#mailChk').text('아이디(이메일)를 입력하세요.');
			$('#mailChk').css('color', 'red');

		}else if($('#email').val() != ''){

			var email = $('#email').val();

				$.ajax({
						async: true,
						type: 'POST',
						data: {
						"mail" : email
						},
						url : './idCheck.do',
						dateType: 'json',
						success: function(data){
						console.log('Ajax for idCheck:' + data);	
						
						if(data > 0){
							$('#mailChk').text('중복된 아이디(이메일) 입니다.');
							$('#mailChk').css('color','red');
							$("#joinChkFrm").attr("disabled", false);
						} else {
							if(mailJ.test(email)){
								$('#mailChk').text('사용가능한 아이디(이메일) 입니다.');
								$('#mailChk').css('color', 'blue');
								$("#joinChkFrm").attr("disabled", false);
							}
							else if(email == ''){
								$('#mailChk').text('아이디(이메일)를 입력해주세요.');
								$('#mailChk').css('color', 'red');
								$("#joinChkFrm").attr("disabled", true);
	
	
							} else {
								$('#mailChk').text("이메일 형식이 아닙니다. 다시 입력해주세요.");
								$('#mailChk').css('color', 'red');
								$("#joinChkFrm").attr("disabled", true); 
							}
						}  
						
						}

				}); // ajax
		} // else if

		}); // blur
	
	// 휴대폰 번호 중복확인
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
							$("#joinChkFrm").attr("disabled", false);
						} else {
							if(phoneJ.test(phone_num)){
								$('#phoneChk').text('사용가능한 휴대폰 번호 입니다.');
								$('#phoneChk').css('color', 'blue');
								$("#joinChkFrm").attr("disabled", false);
								$("#sendPhone_num").removeAttr("disabled");
							}
							else if(phone_num == ''){
								$('#phoneChk').text('휴대폰 번호를 입력해주세요.');
								$('#phoneChk').css('color', 'red');
								$("#joinChkFrm").attr("disabled", true);
								$("#sendPhone_num").attr("disabled",true);
	
							} else if(! phoneJ.test(phone_num)){
								$('#phoneChk').text("휴대폰 번호 형식이 맞지 않습니다 '-'을 제외하고 입력해주세요.");
								$('#phoneChk').css('color', 'red');
								$("#joinChkFrm").attr("disabled", true); 
								$("#sendPhone_num").attr("disabled",true);
							}
						}  
						
						}

				}); // ajax
		} // else if

		}); // blur
	
	// 비밀번호 유효성 검사
	$('#pw').blur(function() {
		if (pwJ.test($('#pw').val())) {
			console.log('true');
			$('#pwChk').text('');

		} else {
			console.log('false');
			$('#pwChk').text('영문 대소문자와 숫자를 혼용하여 8자 이상 입력해주세요.');
			$('#pwChk').css('color', 'red');

		} // else if

	}); // blur (비밀번호 유효성 검사)

	// 비밀번호 일치 확인	
	$("#pw2").blur(function(){
		if($('#pw').val() != $(this).val()){
			$('#pw2Chk').text('비밀번호가 일치하지 않습니다.');
			$('#pw2Chk').css('color', 'red');

		}else {
			$('#pw2Chk').text('');

		}// else if

	}); // blur (비밀번호 확인)
	
	// 폼 서밋 후 체크
	$('form').on('submit', function(){
		var inval_Arr = new Array(2).fill(false);
		// 위에 중복이 되는데 이걸 어떻게 빼면 좋을까?
		
		// 이메일 정규식
		if (mailJ.test($('#email').val())) {
			inval_Arr[0] = true;
		} else {
			inval_Arr[0] = false;
			alert('이메일(아이디)을 확인하세요.');
			return false;
		}
		// 휴대폰 번호 정규식
		if (phoneJ.test($('#phone_num').val())) {
			console.log(phoneJ.test($('#phone_num').val()));
			inval_Arr[1] = true;
			alert('휴대폰 번호를 확인하세요.');
			return false;
		}
		
		// 전체 유효성 검사
		var validAll = true;
			for (var i = 0; i < inval_Arr.length; i++){
				if(inval_Arr[i] == false){
					validAll = false;
				}
			}
			if (validAll = true){ // 유효성 모두 통과
				alert('열락말락에 회원가입이 되셨습니다.');
			} else {
				alert('정보를 다시 확인하세요.');
			}
	});
	
	
});

