//select 3: 기간 선택
//select 4: 의류 선택

$(function(){

    var selectedPeriod = '';    //선택된 기간
    var selectedCloth = '';     //선택된 의류

    /*if($("input[name='selectGraph']:checked").val() == 'quantity'){
            pjm_yAxisName = '판매 수량';
            pjm_yAxisValue = ' 벌';
            pjm_yAxisTitle = '벌';}
        else{
            pjm_yAxisName = '판매 금액';
            pjm_yAxisValue = ' 원';
            pjm_yAxisTitle = '원'}; //왜 이 부분은 전역(?) 변수로 설정하면 안먹고 지역변수로만 설정해야 제대로 먹는가?
        

    /* 기간 선택 값 불러오기 */
    $('#select3').change(function(e){

        console.log("기간 선택");        
        console.log('선택된 기간(값):' + $('#select3').val());
        console.log('선택된 기간(타이틀):' + $('#select3 option').index($('#select3 option:selected')));

        console.log("의류 선택");       
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
        var pjm_data = [212, 28, 45, 2, 3]; 
        
        //기간별 data: DB에서 전송받아야 함(이하는 임시 데이터)
        selectedPeriod = $('#select3').val();
        if(selectedPeriod == '2209'){
            $('#select3_stat').val("2209");
            pjm_data = [12, 128, 145, 12, 13]; 
        } else if(selectedPeriod == '2208'){
            $('#select3_stat').val("2208");
            pjm_data = [12, 18, 15, 2, 14]; 
        } else if(selectedPeriod == '2207'){
            $('#select3_stat').val("2207");
            pjm_data = [112, 8, 5, 2, 1]; 
        } else if(selectedPeriod == '2206'){
            $('#select3_stat').val("2206");
            pjm_data = [1211, 11118, 111115, 112, 1114]; 
        };


        //하이차트
        Highcharts.chart('container', {
            chart: {
                type: 'bar'
            },
                title: {
                text:'&nbsp'
            },
            
            xAxis: {
                categories: ['티셔츠', '팬츠/스커트', '원피스', '니트/가디건', '자켓'],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: pjm_yAxisTitle,
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: pjm_yAxisValue
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            credits: { /*이건 그냥 쓸데없는거 --> 하이차트 워터마크 없애는 부분임*/
                enabled: false
            },
            series: [{ /*-------------------- 나중에 서버에서 데이터 불러오는 값 여기에 넣어야 됨----------------------------*/
                // name: '판매 금액',
                name: pjm_yAxisName,
                // data: [231, 227, 132, 121, 80]
                data: pjm_data
            }]
        })
        
    }); 

    /* 의류 선택 값 불러오기*/
    $('#select4').change(function(e){
        console.log("의류 선택");        
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

        Highcharts.chart('container', {
            chart: {
                type: 'bar'
            },
                title: {
                text:'&nbsp'
            },
            
            xAxis: {    //여기에 항목별 가장 많이 팔린/가장 금액이 많은 옷 top 5 데이터 넣어야 됨 --> 이거도 나중에 변수 선언해서 해야됨(배열??)
                categories: ['티셔츠A', '티셔츠B', '티셔츠C', '티셔츠D', '티셔츠E'],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: pjm_yAxisTitle,
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: pjm_yAxisValue
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            credits: {
                enabled: false
            },
            series: [{ //의류 판매 데이터들 서버에서 불러와서 여기에 넣어야 함
                name: pjm_yAxisName,
                data: [30, 27, 24, 18, 13]
            }]
        });

    }); 
    
    
    
});