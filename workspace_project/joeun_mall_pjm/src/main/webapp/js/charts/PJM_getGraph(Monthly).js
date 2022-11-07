$(function(){
	
	$("input[name=selectGraph]").change(function(e){
		
		var graphType = e.currentTarget.value;
		var sellYear = $('#select3').val();
		var clothType = $('#select4').val();
		
		console.log("판매금액/판매수량: " + graphType);
		console.log("판매년도: " + sellYear);
		console.log("의류종류: " + clothType);
		
		monthlyChartAJAX(graphType, sellYear, clothType);
		
	})//
/*	
    var selectedPeriod = '';    //선택된 기간
    var selectedCloth = '';     //선택된 의류

    // 기간 선택 값 불러오기 
    $('#select3').change(function(e){

        console.log("--------기간 선택--------");        
        console.log('선택된 기간(값):' + $('#select3').val());
        console.log('선택된 기간(타이틀):' + $('#select3 option').index($('#select3 option:selected')));

        console.log("--------의류 선택--------");       
        console.log('선택된 의류(값):' + $('#select4').val());
        console.log('선택된 의류(타이틀):' + $('#select4 option').index($('#select4 option:selected')));

        //대시보드 상태변수
        console.log("판매 금액 선택 여부: " + $('#selectPrice_stat').val());
        console.log("판매 수량 선택 여부: " + $('#selectQuantity_stat').val());
        
        if($("input[name='selectGraph']:checked").val() == 'quantity'){
            pjm_yAxisName = '판매 수량';
            pjm_yAxisValue = ' 벌';
            pjm_yAxisTitle = '벌';}
        else{
            pjm_yAxisName = '판매 금액';
            pjm_yAxisValue = ' 원';
            pjm_yAxisTitle = '원'};

        //차트요소 변수: DB에서 전송받아야 함 AJAX! 
        var pjm_data_monthly = [401, 520, 853, 627, 953, 416, 850, 782, 351, 854, 219, 647]; 
        
        //기간별 data: DB에서 전송받아야 함(이하는 임시 데이터)
        selectedPeriod = $('#select3').val();
        if(selectedPeriod == '2022'){
            $('#select3_stat').val("2022");
            pjm_data_monthly = [4011, 2520, 1853, 627, 953, 416, 850, 782, 351, 854, 219, 647]; 
        } else if(selectedPeriod == '2021'){
            $('#select3_stat').val("2021");
            pjm_data_monthly = [401, 520, 853, 6271, 9533, 416, 850, 782, 351, 854, 219, 647]; 
        } else if(selectedPeriod == '2020'){
            $('#select3_stat').val("2020");
            pjm_data_monthly = [401, 520, 853, 627, 953, 4116, 850, 782, 351, 854, 219, 647]; 
        } else if(selectedPeriod == '2019'){
            $('#select3_stat').val("2019");
            pjm_data_monthly = [401, 520, 853, 6237, 9553, 4116, 850, 782, 351, 854, 219, 647]; 
        };

        //하이차트
        Highcharts.chart('container', {
            chart: {
                type: 'column'
            },
            title: {
                text: '&nbsp'
            },
            subtitle: {
                text: '&nbsp'
            },
            credits:{
                enabled: false
            },
            xAxis: {
                categories: [
                    '1월',
                    '2월',
                    '3월',
                    '4월',
                    '5월',
                    '6월',
                    '7월',
                    '8월',
                    '9월',
                    '10월',
                    '11월',
                    '12월'
                ],
                crosshair: true
            },
            yAxis: {
                title: {
                    text: pjm_yAxisTitle
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: pjm_yAxisName,
                data: pjm_data_monthly
            }]
        });
    });    

    // 의류 선택 값 불러오기
    $('#select4').change(function(e){
        console.log("--------의류 선택--------");        
        console.log('선택된 의류(값):' + $('#select4').val());
        console.log('선택된 의류(타이틀):' + $('#select4 option').index($('#select4 option:selected')));

        selectedCloth = $('#select4').val();
        if($('#select4').val() == 'ct-1'){
            $('#select4_stat').val('티셔츠');
        } else if($('#select4').val() == 'ct-2'){
            $('#select4_stat').val('팬츠/스커트');
        } else if($('#select4').val() == 'ct-3'){
            $('#select4_stat').val('원피스');
        } else if($('#select4').val() == 'ct-4'){
            $('#select4_stat').val('니트/가디건');
        } else if($('#select4').val() == 'ct-5'){
            $('#select4_stat').val('자켓');
        };

        if($("input[name='selectGraph']:checked").val() == 'quantity'){
            pjm_yAxisName = '판매 수량';
            pjm_yAxisValue = ' 벌';
            pjm_yAxisTitle = '벌';}
        else{
            pjm_yAxisName = '판매 금액';
            pjm_yAxisValue = ' 원';
            pjm_yAxisTitle = '원'};

        //하이차트
        Highcharts.chart('container', {
            chart: {
                type: 'column'
            },
            title: {
                text: '&nbsp'
            },
            subtitle: {
                text: '&nbsp'
            },
            credits:{
                enabled: false
            },
            xAxis: {
                categories: [
                    '1월',
                    '2월',
                    '3월',
                    '4월',
                    '5월',
                    '6월',
                    '7월',
                    '8월',
                    '9월',
                    '10월',
                    '11월',
                    '12월'
                ],
                crosshair: true
            },
            yAxis: {
                title: {
                    text: pjm_yAxisTitle
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: pjm_yAxisName,
                data: [401000, 520000, 428000, 1320000, 827000, 721000, 1240000, 998000, 874200, 1009000, 1234000, 647000]
            }]
        });    
    });
    */
});    