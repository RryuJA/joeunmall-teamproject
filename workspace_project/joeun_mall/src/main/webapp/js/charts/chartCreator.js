function chartCreator(chartData, chartCategories, price_quantity, unit){
	//차트 그리기
	Highcharts.chart('container', {
        chart: {
            type: 'bar'
        },
            title: {
            text:'&nbsp'
        },
        
        xAxis: {
        	categories: chartCategories,
            title: {
                text: null
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: unit,
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: unit
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        credits: { //이건 그냥 쓸데없는거 --> 하이차트 워터마크 없애는 부분임
            enabled: false
        },
        series: [{ //-------------------- 나중에 서버에서 데이터 불러오는 값 여기에 넣어야 됨----------------------------
            name: price_quantity,
            data: chartData
        }]
    });

}