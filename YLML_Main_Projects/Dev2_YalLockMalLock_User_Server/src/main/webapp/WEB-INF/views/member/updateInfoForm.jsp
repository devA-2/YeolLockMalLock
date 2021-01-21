<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="../js/jquery-3.5.1.js"></script>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<style type="text/css">
#container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
<script type="text/javascript">
//휴대폰 번호 정규식
var phoneJ = /(01[016789])(\d{4}|\d{3})\d{4}$/g; 

function updatePw(){
	location.href='./updatePwForm.do'
	
}

$(document).ready(function(){
	
	var sendPhone_Num = document.getElementById("sendPhone_num");
// 	sendPhone_num.disabled='disabled';  // 샘플이라 버튼 막아둠
// 	updateBtn.disabled='disabled';
	
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
						} else {
							if(phoneJ.test(phone_num)){
								$('#phoneChk').text('사용가능한 휴대폰 번호 입니다.');
								$('#phoneChk').css('color', 'blue');
								sendPhone_num.disabled=false;
							}
							else if(phone_num == ''){
								$('#phoneChk').text('휴대폰 번호를 입력해주세요.');
								$('#phoneChk').css('color', 'red');
	
							} else if(! phoneJ.test(phone_num)){
								$('#phoneChk').text("휴대폰 번호 형식이 맞지 않습니다 '-'을 제외하고 입력해주세요.");
								$('#phoneChk').css('color', 'red');
							}
						}  
						
						}

				}); // ajax
		} // else if

		}); // blur
});
</script>
</head>
<body>
	<div id="container">
	<h1>마이페이지 수정</h1>
		<form action="./updateInfo.do" method="post">
			<label>변경 할 핸드폰 번호 : </label>
				<input type="text" name="phoneNum" id="phone_num" maxlength="11" size="15">
			<div class="check_font" id="phoneChk"></div>
				<input type="button" id="sendPhone_num" value="인증번호 전송"><br>
				<input type="text" id="inputCertified_num" size="5">
				<input type="button" id="checkBtn" value="확인">
			<div class="time"></div>
			<input type="submit" value="개인정보 수정" id="updateInfoBtn">	
			<input type="button" value="비밀번호 변경" onclick="updatePw()">
		</form>
	</div>
<script>
var timer = null;
var isRunning = false;
var updateInfoBtn = document.getElementById("updateInfoBtn");
// updateInfoBtn.disabled='disabled'; // 회원가입 용이하게 하기 위해 막아둠

        $('#sendPhone_num').click(function(){
            let phoneNumber = $('#phone_num').val();
            alert('인증번호 발송 완료!');
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
			        		updateInfoBtn.disabled=false;
			        		
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
</script>
</body>
</html>