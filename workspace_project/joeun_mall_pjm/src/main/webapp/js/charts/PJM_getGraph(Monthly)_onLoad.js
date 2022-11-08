//페이지 로드 되자마자 그래프 표시되게 하기 위한 javascript

$(function(){
	
		var graphType = $("input[name='selectGraph']:checked").val();
		var sellPeriod = $('#select3').val();
		var clothType = $('#select4').val();
		
		$('#select3_stat').val(sellPeriod);
		$('#select4_stat').val(clothType);
		
		console.log("판매금액/판매수량: " + graphType);
		console.log("판매년도: " + sellPeriod);
		console.log("의류종류: " + clothType);
		
		monthlyChartAJAX(graphType, sellPeriod, clothType);
		
});//
    