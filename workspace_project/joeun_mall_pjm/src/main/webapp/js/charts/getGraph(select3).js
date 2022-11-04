$(function(){
//		var id = $("input[name='selectGraph']").val();
//		console.log(id);
	
	var chart_categories = [];
	
	$("input[name='selectGraph']").change(function(e){ /*라디오 박스 선택 항목에 따라 바뀌게 설정*/
	
		var graphType = e.currentTarget.id;	//특정 이벤트(e = click)의 대상 태그(name이 selectPraph)의 id를 가져옴.
		
//		graphTypeId = "selectQuantity"
	    console.log("선택된 기간: " + $('#select3').val());
	    console.log("선택된 의류: " + $('#select4').val());
	
	    $('#select3_stat').val($('#select3').val());
	    $('#select4_stat').val($('#select4').val());
	    
	    chartAJAX(graphType, chart_categories);
		
	}); //price/quantity function 끝
	
	
	
	
	$('#select4').change(function(e){
		
		alert("select4 실행됨");
		var graphType = e.currentTarget.id;
		
		if($('#select4').val() == "ct-all"){
			chart_categories = ["티셔츠", "팬츠/스커트", "원피스", "니트/가디건", "자켓"];
		} else if ($('#select4').val() == "ct-1"){
			chart_categories = [];
		} else if ($('#select4').val() == "ct-2"){
			chart_categories = [];
		} else if ($('#select4').val() == "ct-3"){
			chart_categories = [];
		} else if ($('#select4').val() == "ct-4"){
			chart_categories = [];
		} else if ($('#select4').val() == "ct-5"){
			chart_categories = [];
		};
		
		chartAJAX(graphType, chart_categories);
		
	});
	
	
})//$(function) 끝
