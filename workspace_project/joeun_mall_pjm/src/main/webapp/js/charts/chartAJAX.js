//명시적 함수 만들기
function chartAJAX(graphType, sellPeriod, clothType){
	
	//ajax
	$.ajax({
		 url : "/joeunmall_pjm/graphJsonVO/priceQuantity",
		 type : 'get',
		 dataType:'text',
		 data : {
			 graphType: $("#" + graphType).val(),
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
			
			//y축 결정 -> 정렬해서 가장 많이 팔린 상위 5개 항목 y축 이름으로 넣어야 함
			var chartCategories;
			if(clothType == "ct-all"){
				chartCategories = ["티셔츠", "팬츠/스커트", "원피스", "니트/가디건", "자켓"];
			} else if(clothType == "ct-1"){
				chartCategories = ["t1", "t2", "t3", "t4", "t5"];
			} else if(clothType == "ct-2"){
				chartCategories = ["p1", "p2", "p3", "p4", "p5"];
			} else if(clothType == "ct-3"){
				chartCategories = ["o1", "o2", "o3", "o4", "o5"];
			} else if(clothType == "ct-4"){
				chartCategories = ["n1", "n2", "n3", "n4", "n5"];
			} else if(clothType == "ct-5"){
				chartCategories = ["j1", "j2", "j3", "j4", "j5"];
			}
			
			//차트 데이터
			var price_quantity;
			var unit;
			var chartData = [];
			
			//ct-n에 해당하는 가격들 ct"n"PriceList에 넣어서 배열 생성
			//이 배열들이 비어있을 경우 reduce해서 배열내 수들의 합을 구할때 오류 발생 -> 그래서 0을 하나씩 넣어줌
			var ct1PriceList = [0];
			var ct2PriceList = [0];
			var ct3PriceList = [0];
			var ct4PriceList = [0];
			var ct5PriceList = [0];
			
			for(var i=0; i<json.length; i++){
				if(json[i].ct == "ct-1"){
					ct1PriceList.push(json[i].price);
				} else if(json[i].ct == "ct-2"){
					ct2PriceList.push(json[i].price);
				} else if(json[i].ct == "ct-3"){
					ct3PriceList.push(json[i].price);
				} else if(json[i].ct == "ct-4"){
					ct4PriceList.push(json[i].price);
				} else if(json[i].ct == "ct-5"){
					ct5PriceList.push(json[i].price);
				}//if문 끝
			}//for문 끝
			
			
			if(graphType == "selectQuantity"){//판매수량 선택시 차트
				//스탯 박스
				$('#selectQuantity_stat').val('선택');
				$('#selectPrice_stat').val('미선택');
				//차트이름(아래에 뜨는거)
				price_quantity = "판매 수량";
				unit = "벌";
				console.log("ct1List: " + ct1PriceList);
				console.log("ct2List: " + ct2PriceList);
				console.log("ct3List: " + ct3PriceList);
				console.log("ct4List: " + ct4PriceList);
				console.log("ct5List: " + ct5PriceList);
				//x축 데이터
				chartData = [ct1PriceList.length-1, ct2PriceList.length-1, ct3PriceList.length-1, ct4PriceList.length-1, ct5PriceList.length-1];
				//PriceList가 비어있을 경우 아래에 reduce가 작동을 제대로 안함 -> 배열에 기본적으로 0을 넣어줌 -> 0까지 추가되어 길이가 계산되므로 1을 빼줌
				
			} else {//판매금액 선택시 차트
				$('#selectQuantity_stat').val('미선택');
				$('#selectPrice_stat').val('선택');
				price_quantity = "판매 금액";
				unit = "원";
				
				//chartData(x축 정보)
				const ct1PriceListSum = ct1PriceList.reduce((a,b) => (a+b));
				const ct2PriceListSum = ct2PriceList.reduce((a,b) => (a+b));
				const ct3PriceListSum = ct3PriceList.reduce((a,b) => (a+b));
				const ct4PriceListSum = ct4PriceList.reduce((a,b) => (a+b));
				const ct5PriceListSum = ct5PriceList.reduce((a,b) => (a+b));
				chartData = [ct1PriceListSum, ct2PriceListSum, ct3PriceListSum, ct4PriceListSum, ct5PriceListSum];
				console.log("차트 데이터: " + chartData);
			}//graphType if문 끝
			
			//차트 그리기
			chartCreator(chartData, chartCategories, price_quantity, unit);
			
	 	} // success
	 	
	 }); // ajax
	
}//function charAJAX() 끝
