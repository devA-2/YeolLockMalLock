// 로그아웃
//  	function logOut() {
//  		sessionStorage.clear();
//  		location.href="index.do"
//	}

	// 담당자 및 배송원 전체조회
      function accessDeliveryList() {
		console.log(auth);
		if(auth != "100"){
			alert("해당 메뉴의 접근권한이 없습니다.");
		}
		else {
			location.href="allDeleveryList.do";
		}
	} 
      
      // 보관함 조회
      function accessStorageList() {
    	  console.log(auth);
  		if(auth != "100"){
  			alert("해당 메뉴의 접근권한이 없습니다.");
  		}
  		else {
  			location.href="allStorageList.do";
  		}
	}
      
      // 보관함 등록
      function accessStorageRegist() {
    	  console.log(auth);
  		if(auth != "100"){
  			alert("해당 메뉴의 접근권한이 없습니다.");
  		}
  		else {
  			location.href="registStorage.do";
  		}
	}