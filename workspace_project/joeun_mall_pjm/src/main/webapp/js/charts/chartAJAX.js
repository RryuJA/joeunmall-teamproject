//명시적 함수 만들기
function chartAJAX(graphType, sellPeriod, clothType){
	
	alert(graphType);
	//ajax
	$.ajax({
		 url : "/joeunmall_pjm/graphJsonVO/priceQuantity",
		 type : 'get',
		 dataType:'text',
		 data : {
			 graphType : $("#" + graphType).val(),
			 sellPeriod: $('#select3').val(),
			 clothType: $('#select4').val()
		 }, // data 
		 success : function(msg) {
//			alert(msg)
			console.log("msg: " + msg);	
			//msg에 배열이 넘어온것 -> 배열에서 key와 값을 구분해줘야 함
			
			var sellPeriod = $('#select3').val();
			var clothType = $('#select4').val();
			
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
			var ct1PriceList =[0];
			var ct2PriceList =[0];
			var ct3PriceList =[0];
			var ct4PriceList =[0];
			var ct5PriceList =[0];
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
			console.log("ct1List: " + ct1PriceList);
			console.log("ct2List: " + ct2PriceList);
			console.log("ct3List: " + ct3PriceList);
			console.log("ct4List: " + ct4PriceList);
			console.log("ct5List: " + ct5PriceList);
			
			if(graphType == "selectQuantity"){//판매수량 선택시 차트
				//스탯 박스
				$('#selectQuantity_stat').val('선택');
				$('#selectPrice_stat').val('미선택');
				//차트이름(아래에 뜨는거)
				price_quantity = "판매 수량";
				unit = "벌";
				
				//chartData(x축 정보)
				if(clothType == "ct-all"){
					chartData = [ct1PriceList.length, ct2PriceList.length, ct3PriceList.length, ct4PriceList.length, ct5PriceList.length];
					console.log("차트 데이터: " + chartData);
				} else if(sellPeriod == "ct-1"){
					
				}//chartData(x축 정보) if문 끝
				
			} else {//판매금액 선택시 차트
				$('#selectQuantity_stat').val('미선택');
				$('#selectPrice_stat').val('선택');
				price_quantity = "판매 금액";
				unit = "원";
				
				//chartData(x축 정보)
				if(clothType == "ct-all"){
					const ct1PriceListSum = ct1PriceList.reduce((a,b) => (a+b));
					const ct2PriceListSum = ct2PriceList.reduce((a,b) => (a+b));
					const ct3PriceListSum = ct3PriceList.reduce((a,b) => (a+b));
					const ct4PriceListSum = ct4PriceList.reduce((a,b) => (a+b));
					const ct5PriceListSum = ct5PriceList.reduce((a,b) => (a+b));
					chartData = [ct1PriceListSum, ct2PriceListSum, ct3PriceListSum, ct4PriceListSum, ct5PriceListSum];
					console.log("차트 데이터: " + chartData);
				}//chartData(x축 정보) if문 끝
			}//graphType if문 끝
			
			//차트 그리기
			chartCreator(chartData, chartCategories, price_quantity, unit);
			
/*			//ct-n(clothType) 포함하는 항목의 Dictionary
			var dict_Chart_Data_ct1 = {};
			var dict_Chart_Data_ct2 = {};
			var dict_Chart_Data_ct3 = {};
			var dict_Chart_Data_ct4 = {};
			var dict_Chart_Data_ct5 = {};
			
			for(var i=0; i<json.length; i++){
				//ct-n을 포함하는 항목 키(판매일자), 값(판매 가격) 쌍으로 dictionary에 넣음
				if(json[i].ct == "ct-1"){
					dict_Chart_Data_ct1[json[i].period] = json[i].price;
				} else if(json[i].ct == "ct-2"){
					dict_Chart_Data_ct2[json[i].period] = json[i].price;
				} else if(json[i].ct == "ct-3"){
					dict_Chart_Data_ct3[json[i].period] = json[i].price;
				} else if(json[i].ct == "ct-4"){
					dict_Chart_Data_ct4[json[i].period] = json[i].price;
				} else if(json[i].ct == "ct-5"){
					dict_Chart_Data_ct5[json[i].period] = json[i].price;
				};
			} //for문 종료
			console.log(dict_Chart_Data_ct1);
			console.log(dict_Chart_Data_ct2);
			console.log(dict_Chart_Data_ct3);
			console.log(dict_Chart_Data_ct4);
			console.log(dict_Chart_Data_ct5);
		
			var chartData;
			
			
			if(graphType == "selectQuantity"){
				$('#selectQuantity_stat').val('선택');
				$('#selectPrice_stat').val('미선택');
				price_quantity = "판매 수량";
				unit = "벌";
				
				//각 dictionary의 key(sellPeriod)들의 개수(= 해당 dictionary의 인자 개수 = 차트의 quantity data)
				chartData = [Object.keys(dict_Chart_Data_ct1).length, Object.keys(dict_Chart_Data_ct2).length, Object.keys(dict_Chart_Data_ct3).length, Object.keys(dict_Chart_Data_ct4).length, Object.keys(dict_Chart_Data_ct5).length];
				
			} else{
				$('#selectQuantity_stat').val('미선택');
				$('#selectPrice_stat').val('선택');
				price_quantity = "판매 금액";
				unit = "원";
				
				let ct_1_price = Object.values(dict_Chart_Data_ct1).reduce(function add(sum, currValue){
					return sum + currValue;
				}, 0);
				
				let ct_2_price = Object.values(dict_Chart_Data_ct2).reduce(function add(sum, currValue){
					return sum + currValue;
				}, 0);
				
				let ct_3_price = Object.values(dict_Chart_Data_ct3).reduce(function add(sum, currValue){
					return sum + currValue;
				}, 0);
				
				let ct_4_price = Object.values(dict_Chart_Data_ct4).reduce(function add(sum, currValue){
					return sum + currValue;
				}, 0);
				
				let ct_5_price = Object.values(dict_Chart_Data_ct5).reduce(function add(sum, currValue){
					return sum + currValue;
				}, 0);
				
				chartData = [ct_1_price, ct_2_price, ct_3_price, ct_4_price, ct_5_price];
				
			}//if문 끝
*/				
			
			
			
//			//차트 그리기
//			Highcharts.chart('container', {
//	            chart: {
//	                type: 'bar'
//	            },
//	                title: {
//	                text:'&nbsp'
//	            },
//	            
//	            xAxis: {
//	            	categories: chart_categories,
//	                title: {
//	                    text: null
//	                }
//	            },
//	            yAxis: {
//	                min: 0,
//	                title: {
//	                    text: unit,
//	                    align: 'high'
//	                },
//	                labels: {
//	                    overflow: 'justify'
//	                }
//	            },
//	            tooltip: {
//	                valueSuffix: unit
//	            },
//	            plotOptions: {
//	                bar: {
//	                    dataLabels: {
//	                        enabled: true
//	                    }
//	                }
//	            },
//	            credits: { //이건 그냥 쓸데없는거 --> 하이차트 워터마크 없애는 부분임
//	                enabled: false
//	            },
//	            series: [{ //-------------------- 나중에 서버에서 데이터 불러오는 값 여기에 넣어야 됨----------------------------
//	                name: price_quantity,
//	                data: chartData
//	            }]
//	        });
	        
	 	} // success
	 	
	 }); // ajax
	
}//function charAJAX() 끝
