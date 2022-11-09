$(function(){
	
	$('#select4').change(function(e){ /*라디오 박스 선택 항목에 따라 바뀌게 설정*/
		//특정 이벤트(e = change)의 대상 태그(select3)를 가져옴
		
		var graphType;
		//graphType 정의
		if($('#selectPrice_stat').val() == "선택"){
			graphType = "selectPrice";
		} else {
			graphType = "selectQuantity";
		}
		var sellPeriod = $('#select3').val();
		var clothType = e.currentTarget.value;
		
		console.log("판매금액/판매수량: " + graphType);
		console.log("선택된 기간: " + sellPeriod);
	    console.log("선택된 의류: " + clothType);
	    
	    $('#select3_stat').val(sellPeriod);
	    $('#select4_stat').val(clothType);
	    
	    chartAJAX(graphType, sellPeriod, clothType);
		
	}); //price/quantity function 끝
})//$(function) 끝

