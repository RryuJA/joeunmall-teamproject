$("input[name='selectGraph']").change(function(){ /*라디오 박스 선택 항목에 따라 바뀌게 설정*/
    if($("input[name='selectGraph']:checked").val() == 'quantity'){ /*판매 수량(quantity) 선택시 만들어지는 그래프*/
    Highcharts.chart('container', {
        chart: {
            type: 'bar'
        },
            title: {
            text:'&nbsp'
        },
        
        xAxis: {
            categories: ['티셔츠', '셔츠/블라우스', '원피스', '팬츠/스커트', '니트/가디건', '자켓', '점퍼/다운', '코트', 'ACC'],
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
        credits: { /*이건 그냥 쓸데없는거 --> 하이차트 워터마크 없애는 부분임*/
            enabled: false
        },
        series: [{ /*-------------------- 나중에 서버에서 데이터 불러오는 값 여기에 넣어야 됨----------------------------*/
                name: '판매 수량',
            data: [231, 227, 132, 121, 80, 86, 87, 224, 48]
        }]
    });}

    else/*판매 금액(price) 선택시 만들어지는 그래프*/
    {
        Highcharts.chart('container', {
            chart: {
                type: 'bar'
            },
                title: {
                text:'&nbsp'
            },
            
            xAxis: {
                categories: ['티셔츠', '셔츠/블라우스', '원피스', '팬츠/스커트', '니트/가디건', '자켓', '점퍼/다운', '코트', 'ACC'],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '원',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' 원'
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
                    name: '판매 금액',/*-------------------- 나중에 서버에서 데이터 불러오는 값 여기에 넣어야 됨----------------------------*/
                data: [1211000, 3211000, 1412000, 1821000, 5483000, 744000, 1123000, 2241000, 481000]
            }]
        });
    }


});


