$(function(){
	
	$('#select4').change(function(e){
		
		var graphType = $("input[name='selectGraph']:checked").val();
		var sellPeriod = $('#select3').val();
		var clothType = e.currentTarget.value;
		
		$('#select3_stat').val(sellPeriod);
		$('#select4_stat').val(clothType);
		
		console.log("판매금액/판매수량: " + graphType);
		console.log("판매년도: " + sellPeriod);
		console.log("의류종류: " + clothType);
		
		monthlyChartAJAX(graphType, sellPeriod, clothType);
		
	})
});   // 