$(function(){
//		var id = $("input[name='selectGraph']").val();
//		console.log(id);
	var chartData;
	var chartCategories = [];
	
	$("input[name='selectGraph']").change(function(e){ /*라디오 박스 선택 항목에 따라 바뀌게 설정*/
	
		var graphType = e.currentTarget.id;	//특정 이벤트(e = click)의 대상 태그(name이 selectPraph)의 id를 가져옴.
		//graphTyep = selectPrice or selectQuantity
		
		console.log("판매금액/판매수량: " + $('#' + graphType).val());
		console.log("선택된 기간: " + $('#select3').val());
	    console.log("선택된 의류: " + $('#select4').val());
	    
	    $('#select3_stat').val($('#select3').val());
	    $('#select4_stat').val($('#select4').val());
	    
	    chartAJAX(graphType);
		
	}); //price/quantity function 끝
})//$(function) 끝
