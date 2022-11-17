//명시적 함수 만들기
function chartAJAX(graphType, sellPeriod, clothType){
	
	//ajax
	$.ajax({
		 url : "/joeunmall/graphJsonVO/priceQuantity",
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
			
			//차트 데이터
			var price_quantity;
			var unit;
			var chartData = [];
			
			//ct-n에 해당하는 가격들 ct"n"PriceList에 넣어서 배열 생성
			//이 배열들이 비어있을 경우 reduce해서 배열내 수들의 합을 구할때 오류 발생 -> 그래서 0을 하나씩 넣어줌
			var ct1AmountSum = 0;
			var ct2AmountSum = 0;
			var ct3AmountSum = 0;
			var ct4AmountSum = 0;
			var ct5AmountSum = 0;
			
			var ct1PriceSum = 0;
			var ct2PriceSum = 0;
			var ct3PriceSum = 0;
			var ct4PriceSum = 0;
			var ct5PriceSum = 0;
			
			for(var i=0; i<json.length; i++){
				if(json[i].ct == "01"){
					ct1AmountSum += json[i].amount;
				} else if(json[i].ct == "02"){
					ct2AmountSum += json[i].amount;
				} else if(json[i].ct == "03"){
					ct3AmountSum += json[i].amount;
				} else if(json[i].ct == "04"){
					ct4AmountSum += json[i].amount;
				} else if(json[i].ct == "05"){
					ct5AmountSum += json[i].amount;
				}//if문 끝
			}//for문 끝
			
			
			if(graphType == "selectQuantity"){//판매수량 선택시 차트
				//스탯 박스
				$('#selectQuantity_stat').val('선택');
				$('#selectPrice_stat').val('미선택');
				//차트이름(아래에 뜨는거)
				price_quantity = "판매 수량";
				unit = "벌";
				console.log("ct1AmountSum: " + ct1AmountSum);
				console.log("ct2AmountSum: " + ct2AmountSum);
				console.log("ct3AmountSum: " + ct3AmountSum);
				console.log("ct4AmountSum: " + ct4AmountSum);
				console.log("ct5AmountSum: " + ct5AmountSum);
				
				//x축 데이터
				chartData = [ct1AmountSum, ct2AmountSum, ct3AmountSum, ct4AmountSum, ct5AmountSum];
				//PriceList가 비어있을 경우 아래에 reduce가 작동을 제대로 안함 -> 배열에 기본적으로 0을 넣어줌 -> 0까지 추가되어 길이가 계산되므로 1을 빼줌
				
			} else {//판매금액 선택시 차트
				$('#selectQuantity_stat').val('미선택');
				$('#selectPrice_stat').val('선택');
				price_quantity = "판매 금액";
				unit = "원";
				
				for(var i=0; i<json.length; i++){
					if(json[i].ct == "01"){
						ct1PriceSum += json[i].price;
					} else if(json[i].ct == "02"){
						ct2PriceSum += json[i].price;
					} else if(json[i].ct == "03"){
						ct3PriceSum += json[i].price;
					} else if(json[i].ct == "04"){
						ct4PriceSum += json[i].price;
					} else if(json[i].ct == "05"){
						ct5PriceSum += json[i].price;
					}//if문 끝
				}//for문 끝
				
				chartData = [ct1PriceSum, ct2PriceSum, ct3PriceSum, ct4PriceSum, ct5PriceSum];
				console.log("차트 데이터: " + chartData);
			}//graphType if문 끝
			
			
			//y축 결정 -> 정렬해서 가장 많이 팔린 상위 5개 항목 y축 이름으로 넣어야 함
			var chartCategories;
			if(clothType == "ct-all"){
				chartCategories = ["티셔츠", "팬츠/스커트", "원피스", "니트/가디건", "자켓"];
			} else {
				if(json.length != 0){
					const groupByName = json.reduce((acc, curr) => {	//json배열 pn(제품명)에 따라 그룹화 작업
						  const {pn} = curr;
						  // acc[name]이 있으면 acc[name]의 값을 할당 없으면, 빈 배열을 할당
						  acc[pn] = acc[pn] ?? []; 
						  // acc[name]에 객체를 추가
						  acc[pn].push(curr);
						  return acc;
						}, {});
					
					console.log(Object.keys(groupByName));
					console.log(Object.values(groupByName));
					console.log(Object.values(groupByName)[0]);
	//				console.log(Object.values(groupByName)[0][0]);
					console.log(Object.values(groupByName)[0][0].amount);
					
					for(var i = 0; i<Object.values(groupByName).length; i++){	//이름에 따라 그룹화된 객체들 각각 amount값 합하기
						var sum = 0;
						for(var j = 0; j<Object.values(groupByName)[i].length; j++){
							var x = Object.values(groupByName)[i][j].amount;
							sum += x; 
						}//inner for문 끝
						window['sum_'+i] = {
							name: Object.keys(groupByName)[i], price: Object.values(groupByName)[i][0].price, amount: sum, total: sum*Object.values(groupByName)[i][0].price
						};//동적변수 sum_n에 합한 값들 넣어서 객체화
					}//outer for문 끝
	//				console.log(sum_0);
	//				console.log(sum_1);
	//				console.log(sum_2);
	//				console.log(sum_3);
	//				console.log(sum_4);
	//				console.log(sum_19);
					
					var allTSum = [];
					for(var i = 0; i<Object.values(groupByName).length; i ++){
						allTSum.push(window['sum_'+i])
					}	//sum_1, sum_2, .... , sum_n 배열화
					
					console.log(allTSum);
					
					if(graphType == "selectQuantity"){
						//배열 정렬(수량)
						allTSum.sort(function(a, b){
							if(a.amount < b.amount){
								return 1;
							}
							if(a.amount > b.amount){
								return -1;
							}
							return 0;
						})//정렬 끝
						console.log(allTSum);
						chartData = [allTSum[0].amount, allTSum[1].amount, allTSum[2].amount, allTSum[3].amount, allTSum[4].amount]
					} else {
						//배열 정렬(총액)
						allTSum.sort(function(a, b){
							if(a.total < b.total){
								return 1;
							}
							if(a.total > b.total){
								return -1;
							}
							return 0;
						})
						console.log(allTSum);
						chartData = [allTSum[0].total, allTSum[1].total, allTSum[2].total, allTSum[3].total, allTSum[4].total]
					}
					chartCategories = [allTSum[0].name, allTSum[1].name, allTSum[2].name, allTSum[3].name, allTSum[4].name]
				} else {
					chartData = [0, 0, 0, 0, 0];
					chartCategories = ["정보없음", "정보없음", "정보없음", "정보없음", "정보없음"];
				}//if, else문 끝		
			}//if, else문 끝 
			
			//차트 그리기
			chartCreator(chartData, chartCategories, price_quantity, unit);
			
	 	} // success
	 	
	}); // ajax
	
}//function charAJAX() 끝
