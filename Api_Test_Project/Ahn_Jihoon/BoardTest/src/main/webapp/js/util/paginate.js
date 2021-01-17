function datainit(){
	
	$('#grid').jqGrid({
		url:'<c:url value="/datalist.do/>',
		mtype: 'POST',
		datatype:"json",
		height: 'auto',
		width: 'auto',
		autowidth: true,
		rowNum: 2,
		pager:'#pager',
		loadonce:true,
		colModel:[
			{label:'No', name:'id', index:'id', width:50, sortable:true},
			{label:'Password', name:'password', index:'password', width:50, hidden:true},
			{label:'Name', name:'name', index:'name', width:50, formatter:detailinfo},
			{label:'EmpNum', name:'empnum', index:'empnum', width:50, align:"rigth"},
			{label:'email', name:'email', index:'email', width:50, align:"right"},
			{label:'정보보기', name:'info', index:'info', width:50, formatter:infoclick},
			{label:'이메일 전송', name:'infomail', index:'infomail', width:50, formatter:sendmail}
		],
		multiselect:true,
		caption:"<label>* 총 <strong id='totalCnt'>0</strong>건의 검색 결과가 있습니다.</label>",
		loadComplete:function(data){
			$('#totalCnt').text(data.records);
			initPage("paginate", data.records, $('#grid').jqGrid("getGridParam", "rowNum"), data.page);
		}
	});

	console.log('data load success');
	
}