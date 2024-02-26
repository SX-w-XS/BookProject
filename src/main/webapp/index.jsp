<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Date"%>
<script src="https://cdn.bootcss.com/echarts/4.2.1-rc1/echarts.min.js" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-1.8.0.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>首页</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jsapi.js"></script>
    <script type="text/javascript" src="js/format+zh_CN,default,corechart.I.js"></script>
    <script type="text/javascript" src="js/jquery.gvChart-1.0.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.ba-resize.min.js"></script>
    <script type="text/javascript">
        gvChartInit();
        jQuery(document).ready(function(){

            jQuery('#myTable5').gvChart({
                chartType: 'PieChart',
                gvSettings: {
                    vAxis: {title: 'No of players'},
                    hAxis: {title: 'Month'},
                    width: 400,
                    height: 250
                }
            });
        });
    </script>
</head>


<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="../indexServlet">首页</a></li>
    </ul>
</div>

<div class="mainbox">
    <div class="mainleft">
        <div id="main" style="position:absolute;top: 40px;right: 320px;width: 500px;height: 250px;border: 0px solid #73AD21;"></div>
        <div class="leftinfo">
            <div class="listtitle">数据统计</div>
                <table id='myTable5'>
                    <caption style="align: center">书籍类型统计</caption>
                    <thead>
                    <tr>
                        <th></th>
                        <th>小说</th>
                        <th>科普读物</th>
                        <th>科学技术</th>
                        <th>医学</th>
                        <th>艺术</th>
                        <th>军事</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:forEach items="${requestScope.count}" var="count">
                            <td>${count.count}</td>
                        </c:forEach>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!--leftinfo end-->

        <div class="leftinfos">
            <div class="infoleft">
                <div class="listtitle"><a href="../discussServlet?type=list" class="more1">更多</a>评论区</div>
                <table>
                    <c:forEach items="${requestScope.comment}" var="comment">
                        <tr>
                            <td>${comment.content}</td>
                            <td>${comment.releaseTime}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div class="inforight">
                <div class="mainindex">

                    <div class="welinfo">
                        <span><img src="images/sun.png" alt="天气" /></span>
                        <%
                            String date = new java.text.SimpleDateFormat("HH").format(new Date());
                            Integer datetime = Integer.parseInt(date);

                            if (datetime > 12 && datetime < 18){
                                out.println("下午好");
                            }
                            else if(datetime >= 18){
                                out.println("晚上好");
                            }else {
                                out.println("早上好");
                            }
                        %>
                        <b>${sessionScope.loginUser.userName} *欢迎使用信息管理系统*</b>
                    </div>
                    <div class="welinfo">
                    <span><img src="images/time.png" alt="时间" /></span>
                        现在是
                        <i id = "getTime">
                            <%
                                out.println(new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));	//输出系统时间
                            %>
                        </i>
                    </div>
                </div>
            </div>
        </div>
    <!--mainleft end-->

    <div class="mainright">
        <div class="dflist">
            <div class="listtitle"><a href="../messageServlet" class="more1">更多</a>最新信息</div>
            <c:forEach items="${requestScope.message}" var="message">
                <li style="font-family:'宋体';font-size: 20px"  >
                <a href="${message.messageConnect}" style="font-family:'宋体';font-size: 15px"> ${message.messageTitile}</a></li>
            </c:forEach>
        </div>

        <div class="dflist1">
            <div class="listtitle"><a href="#" class="more1"></a>信息统计</div>
            <ul class="newlist">
                <c:forEach items="${requestScope.list}" var="count">
                    <li><i>${count.name}</i>${count.count}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!--mainright end-->
</div>
</body>
<script type="text/javascript">
    setWidth();
    $(window).resize(function(){
        setWidth();
    });
    function setWidth(){
        var width = ($('.leftinfos').width()-12)/2;
        $('.infoleft,.inforight').width(width);
    }
    setInterval(function () {
        $("#getTime").html(new Date().toLocaleString());
    },1000);
</script>
<script type="text/javascript">
    // 折线图
    $.ajax({
        url: "/userServlet?type=page&userName=${sessionScope.loginUser.userName}",
        data: {},
        type: 'GET',
        success: function(data) {
            var  json1=JSON.parse(data);
            console.log(json1)
            console.log(json1.echatY);
            console.log(json1.echatX);
            bloodFun(json1.echatX, json1.echatY);
        },
    });

    // 基于准备好的dom，初始化echarts实例
    var bloodChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    function bloodFun(x_data, y_data) {

        bloodChart.setOption({
            title: {
                text: ''
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },
            legend: {},
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            grid: {
                left: '2%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                boundaryGap: false,
                data: x_data,
            }],
            yAxis: [{
                type: 'value'
            }],
            series: [{
                name: '借阅数量统计',
                type: 'line',

                areaStyle: {
                    normal: {
                        color: '#fff' //改变区域颜色
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#8cd5c2', //改变折线点的颜色
                        lineStyle: {
                            color: '#8cd5c2' //改变折线颜色
                        }
                    }
                },
                data: y_data
            }
            ]
        });
    }
</script>
</html>