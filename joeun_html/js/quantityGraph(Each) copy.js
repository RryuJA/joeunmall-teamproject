$(function(){
    Highcharts.chart('container', {
        chart: {
            type: 'bar'
        },
            title: {
            text:'&nbsp'
        },
        
        xAxis: {
            categories: ['티셔츠A', '티셔츠B', '티셔츠C', '티셔츠D', '티셔츠E'],
            title: {
                text: null
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '벌',
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ' 벌'
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
        series: [{
                name: '판매 수량',
            data: [30, 27, 24, 18, 13]
        }]
    });
});
