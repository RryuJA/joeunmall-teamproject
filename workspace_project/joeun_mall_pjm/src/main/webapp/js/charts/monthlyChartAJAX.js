//명시적 함수 만들기
function monthlyChartAJAX(graphType, sellYear, clothType){
	
	//ajax
	$.ajax({
		 url : "/joeunmall_pjm/graphJsonVO/monthlyGraphData",
		 type : 'get',
		 dataType:'text',
		 data : {
			 //graphType: $("#" + graphType).val(),
			 sellPeriod: $('#select3').val(),
			 clothType: $('#select4').val()
		 }, // data 
		 success : function(msg) {
			console.log("msg: " + msg);	
			//msg에 배열이 넘어온것 -> 배열에서 key와 값을 구분해줘야 함(var json = JSON.parse(msg))가 그것
			//controller에서 받는 data정보 
					
			//controller에서 받는 data정보 
			var json = JSON.parse(msg);
			//console.log(json[1].price);
			
			//y축 -> 월별 판매 그래프에선 y축 데이터는 필요없음
			
			//차트 데이터
			var price_quantity;
			var unit;
			var chartData = [];
			
			//ct-n에 해당하는 가격들 ct"n"PriceList에 넣어서 배열 생성
			//이 배열들이 비어있을 경우 reduce해서 배열내 수들의 합을 구할때 오류 발생 -> 그래서 0을 하나씩 넣어줌
			var JanList = [0];
			var FebList = [0];
			var MarList = [0];
			var AprList = [0];
			var MayList = [0];
			var JunList = [0];
			var JulList = [0];
			var AugList = [0];
			var SepList = [0];
			var OctList = [0];
			var NovList = [0];
			var DecList = [0];
			
			console.log(JanList);
			console.log(json.length);
			console.log(parseInt(json[1].period.slice(2)));
			console.log(parseInt(json[0].period.slice(2)));
			
			for(var i=0; i<json.length; i++){
				if(100 < parseInt(json[i].period.slice(2)) <131){
					JanList.push(json[i].price);
				} else if(200 < parseInt(json[i].period.slice(2)) < 231){
					FebList.push(json[i].price);
				} else if(300 < parseInt(json[i].period.slice(2)) < 331){
					MarList.push(json[i].price);
				} else if(400 < parseInt(json[i].period.slice(2)) < 431){
					AprList.push(json[i].price);
				} else if(500 < parseInt(json[i].period.slice(2)) < 531){
					MayList.push(json[i].price)
				} else if(600 < parseInt(json[i].period.slice(2)) < 631){
					JunList.push(json[i].price);
				} else if(700 < parseInt(json[i].period.slice(2)) < 731){
					JulList.push(json[i].price);
				} else if(800 < parseInt(json[i].period.slice(2)) < 831){
					AugList.push(json[i].price);
				} else if(900 < parseInt(json[i].period.slice(2)) < 931){
					SepList.push(json[i].price);
				} else if(1000 < parseInt(json[i].period.slice(2)) < 1031){
					OctList.push(json[i].price);
				} else if(1100 < parseInt(json[i].period.slice(2)) < 1131){
					NovList.push(json[i].price);
				} else if(1200 < parseInt(json[i].period.slice(2)) < 1231){
					decList.push(json[i].price);
				}//if문 끝
			}//for문 끝
			
			console.log("JanList: " + JanList);
			console.log("FebList: " + FebList);
			console.log("MarList: " + MarList);
			console.log("AprList: " + AprList);
			console.log("MayList: " + MayList);
			console.log("JunList: " + JunList);
			console.log("JulList: " + JulList);
			console.log("AugList: " + AugList);
			console.log("SepList: " + SepList);
			console.log("OctList: " + OctList);
			console.log("NovList: " + NovList);
			console.log("DecList: " + DecList);
			
			
			if(graphType == "quantity"){//판매수량 선택시 차트
				//스탯 박스
				$('#selectQuantity_stat').val('선택');
				$('#selectPrice_stat').val('미선택');
				//차트이름(아래에 뜨는거)
				price_quantity = "판매 수량";
				unit = "벌";
				
				//x축 데이터
				chartData = [JanList.length-1, FebList.length-1, MarList.length-1, AprList.length-1, MayList.length-1, JunList.length-1, JulList.length-1, AugList.length-1, SepList.length-1, OctList.length-1, NovList.length-1, DecList.length-1];
				console.log("차트 데이터: "+  chartData);
				//PriceList가 비어있을 경우 아래에 reduce가 작동을 제대로 안함 -> 배열에 기본적으로 0을 넣어줌 -> 0까지 추가되어 길이가 계산되므로 1을 빼줌
				
			} else {//판매금액 선택시 차트
				$('#selectQuantity_stat').val('미선택');
				$('#selectPrice_stat').val('선택');
				price_quantity = "판매 금액";
				unit = "원";
				
				//chartData(x축 정보)
				const JanListSum = JanList.reduce((a,b) => (a+b));
				const FebListSum = FebList.reduce((a,b) => (a+b));
				const MarListSum = MarList.reduce((a,b) => (a+b));
				const AprListSum = AprList.reduce((a,b) => (a+b));
				const MayListSum = MayList.reduce((a,b) => (a+b));
				const JunListSum = JunList.reduce((a,b) => (a+b));
				const JulListSum = JulList.reduce((a,b) => (a+b));
				const AugListSum = AugList.reduce((a,b) => (a+b));
				const SepListSum = SepList.reduce((a,b) => (a+b));
				const OctListSum = OctList.reduce((a,b) => (a+b));
				const NovListSum = NovList.reduce((a,b) => (a+b));
				const DecListSum = DecList.reduce((a,b) => (a+b));
				
				chartData = [JanListSum, FebListSum, MarListSum, AprListSum, MayListSum, JunListSum, JulListSum, AugListSum, SepListSum, OctListSum, NovListSum, DecListSum];
				console.log("차트 데이터: " + chartData);
			}//graphType if문 끝
			
			//차트 그리기
			monthlyChartCreator(chartData, price_quantity, unit);
			
	 	} // success
	 	
	 }); // ajax
	
}//function charAJAX() 끝
