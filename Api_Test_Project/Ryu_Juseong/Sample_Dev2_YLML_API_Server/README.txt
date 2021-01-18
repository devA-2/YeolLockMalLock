DB설정 확인 할 것 : "scr/resources/main/resources/db.properties"
DAO 경로는 : com.dev2.ylml.model.dao
Service경로는 : com.dev2.ylml.model.service

gitignore -> .gitignore로 수정해서 사용하면 됨



1. 모든 요청은 post로 인증 key를 포함 해야함
2. 페이지 요청시 return되는 jsonArray의 0번째 값은 {"Certification" : true/false}
	certification이 false면 인증되지 않은 key 
