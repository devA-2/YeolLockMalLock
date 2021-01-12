<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="./js/signUp.js"></script>
</head>
<style type="text/css">
#container{
   width : 360px;
   height: 600px;
   border: 1px solid black;
   margin: auto;
}
</style>
<script type="text/javascript">

<body>
<!-- 휴대폰 인증 유효성 검사를 세션에 담아서 체크하는 거 구현해야함... 메일 보내는 것도.. -->
	<div id="container">
		<h1>회원가입</h1>
		<form action="./insertMember.do" id="joinChkFrm" method="post">
			<label>아이디 : </label>
				<input type="text" id="email" name="email" size="40" maxlength="40"><br>
				<div class="checkFont" id="mailChk"></div>
			<label>비밀번호 : </label>
				<input type="password" id="pw" name="pw" maxlength="20" size="20"><br>
				<div class="check_font" id="pwChk"></div>
		
			<label>비밀번호 확인: </label>
				<input type="password" id="pw2" name="pw2" maxlength="20" size="20"><br>
				<div class="check_font" id="pw2Chk"></div>
			<label>이름 :</label>
				<input type="text" name="name" maxlength="6" size="10"><br>
		
			<label>핸드폰 번호 : </label>
				<input type="text" name="phone_num" id="phone_num" maxlength="11" size="15">
			<div class="check_font" id="phoneChk"></div>
				<input type="button" id="sendPhone_num" value="인증번호 전송"><br>
				<input type="text" id="certified_num" value='인증번호' size="5">
				<input type="button" id="checkBtn" value="확인">
			<div class="time"></div>
			<input type="submit" value="회원가입완료" id="signUpBtn">	
		</form>
	</div>
		<script>
var timer = null;
var isRunning = false;
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
                    	if($('#certified_num').val()=='') {
                    		alert('값을 입력하세요.');
                    	} else if(isRunning && $.trim(res) ==$('#certified_num').val()){
                            // 타이머가 활성화 되어있고 값이 정확히 입력되었을 때
                    		alert('휴대폰 인증이 정상적으로 완료되었습니다.');
							clearInterval(timer);
			        		display.html("");
			        		signUpBtn.disabled=false;
			        		
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