<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>

<body>
<input type="text" id="inputPhoneNumber" value='수신번호'/>
<input type="button" id="sendPhoneNumber" value='전송'/><br>
<input type="text" id="inputCertifiedNumber" value='인증번호'>
<input type="button" id="checkBtn" value="확인">
<div class="time"></div>
<script>

var timer = null;
var isRunning = false;
        $('#sendPhoneNumber').click(function(){
            let phoneNumber = $('#inputPhoneNumber').val();
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

            var docum = document.getElementById("checkBtn").disabled;
            $.ajax({
                type: "POST",
                url: "./sendSMS.do",
                data: {"phoneNumber" : phoneNumber}, // 핸드폰 값이 넘어감
                success: function(res){ // 인증번호 값이 넘어옴
                    $('#checkBtn').click(function(){
                    	if($('#inputCertifiedNumber').val()=='') {
                    		alert('값을 입력하세요.');
                    	} else if(isRunning && $.trim(res) ==$('#inputCertifiedNumber').val()){
                            // 타이머가 활성화 되어있고 값이 정확히 입력되었을 때
                    		alert('인증성공!'+'휴대폰 인증이 정상적으로 완료되었습니다.'+'success');
							clearInterval(timer);
			        		display.html("");
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