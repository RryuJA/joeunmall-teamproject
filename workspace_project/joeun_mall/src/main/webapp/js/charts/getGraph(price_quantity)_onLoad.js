//페이지 로드 되자마자 그래프 표시되게 하기 위한 javascript

$(function(){
	var graphType = "select" + $("input[name='selectGraph']").val();
	var sellPeriod = $('#select3').val();
	var clothType = $('#select4').val();
	console.log(graphType);
	console.log(sellPeriod);
	console.log(clothType);
	
	chartAJAX(graphType, sellPeriod, clothType);
	
})//$(function) 끝
