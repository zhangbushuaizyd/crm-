<script>
    var ctx = "${ctx}";
</script>
<script src="${ctx}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script src="${ctx}/js/echarts.min.js"></script>
 <#--<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM &ndash;&gt;-->
    <div id="main" style="width: 600px;height:400px;">123</div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    $.ajax({
        url:ctx+"/report/queryLevelNums",
        success:function (data) {
            var level=[];
            $(data).each(function () {
                level.push(this.name);
            })
            option = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data:level
                },
                series: [
                    {
                        name:'开户类型',
                        type:'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data:data
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });

</script>