<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="./css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script language="javascript">
        $(function(){
            //导航切换
            $(".imglist li").click(function(){
                $(".imglist li.selected").removeClass("selected")
                $(this).addClass("selected");
            })
        })
    </script>
    <script type="text/javascript">
        $(document).ready(function(){
            $(".click").click(function(){
                $(".tip").fadeIn(200);
            });

            $(".tiptop a").click(function(){
                $(".tip").fadeOut(200);
            });

            $(".sure").click(function(){
                $(".tip").fadeOut(100);
            });

            $(".cancel").click(function(){
                $(".tip").fadeOut(100);
            });

        });
    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li>借书卡</li>
        <li><a href="#">积分商城</a></li>
    </ul>
</div>
    <table class="imgtable">

        <c:if test="${sessionScope.loginUser.userName == 'Admin1' || sessionScope.loginUser.userName == 'Admin2'}">
            <ul class="toolbar">
                <li class="click"><span><img src="./images/t01.png" /></span>添加</li>
            </ul>
        </c:if>
        <thead>
        <tr>
            <th>商品编号<i class="sort"></i></th>
            <th style="width: 100px">缩略图</th>
            <th>简介及上线时间</th>
            <th>所需积分数</th>
            <th>能否购买</th>
            <th>兑换次数</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${requestScope.list}" var="commodity">
        <tr>
            <td>${commodity.cid}</td>
            <td class="imgtd"><img src="../images/img11.png" /></td>
            <td><a href="#">${commodity.cintroduction}</a><p>发布时间：${commodity.cpudate}</p></td>
            <td>${commodity.cprice}</td>
            <td>
                <c:if test="${commodity.cstate == 0}">否</c:if>
                <c:if test="${commodity.cstate == 1}">能</c:if>
            </td>
            <td>${commodity.ccount}</td>
            <td>
                <c:if test="${sessionScope.loginUser.userName != 'Admin1' && sessionScope.loginUser.userName != 'Admin2'}">
                    <a href="${pageContext.request.contextPath}/commodityServlet?type=buy&id=${commodity.cid}" class="tablelink">兑换</a>
                </c:if>
                <c:if test="${sessionScope.loginUser.userName == 'Admin1' || sessionScope.loginUser.userName == 'Admin2'}">
                    <a href="${pageContext.request.contextPath}/commodityServlet?type=querybyid&id=${commodity.cid}" class="tablelink">修改</a>
                    <a href="Javascript:deleteBook(${commodity.cid})" class="tablelink">删除</a>
                </c:if>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>

<%--    <div class="pagin">
        <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
        <ul class="paginList">
            <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
            <li class="paginItem"><a href="javascript:;">1</a></li>
            <li class="paginItem current"><a href="javascript:;">2</a></li>
            <li class="paginItem"><a href="javascript:;">3</a></li>
            <li class="paginItem"><a href="javascript:;">4</a></li>
            <li class="paginItem"><a href="javascript:;">5</a></li>
            <li class="paginItem more"><a href="javascript:;">...</a></li>
            <li class="paginItem"><a href="javascript:;">10</a></li>
            <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div>--%>

<div class="tip">
    <div class="tiptop"><span>提示信息</span><a></a></div>

    <div class="tipinfo">
        <span><img src="./images/ticon.png" /></span>
        <div class="tipright">
            <p>是否确认对信息的修改 ？</p>
            <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
    </div>

    <div class="tipbtn">
        <a href="/commodity/commodityAdd.jsp"><input name="" type="button" class="sure" value="确定" /></a>&nbsp;
        <a href="/user.jsp"><input name="" type="button" class="cancel" value="取消" /></a>
    </div>

</div>


<script type="text/javascript">
    function deleteBook(cId){
        if(window.confirm("确定要删除这条记录吗?"+cId)){
            location.href = "${pageContext.request.contextPath}/commodityServlet?type=delete&id="+cId;
        }
    }
</script>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
