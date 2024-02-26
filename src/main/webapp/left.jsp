<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="js/jquery.js"></script>

    <script type="text/javascript">
        $(function(){
            //导航切换
            $(".menuson li").click(function(){
                $(".menuson li.active").removeClass("active")
                $(this).addClass("active");
            });

            $('.title').click(function(){
                var $ul = $(this).next('ul');
                $('dd').find('ul').slideUp();
                if($ul.is(':visible')){
                    $(this).next('ul').slideUp();
                }else{
                    $(this).next('ul').slideDown();
                }
            });
        })
    </script>


</head>

<body style="background:#f0f9fd;">
<div class="lefttop"><span></span>我的功能</div>

<dl class="leftmenu">
    <dd>
        <div class="title">
            <span><img src="images/leftico01.png" /></span>信息管理
        </div>
        <ul class="menuson">
            <li class="active"><cite></cite><a href="../indexServlet" target="rightFrame">首页</a></li>
            <li><cite></cite><a href="../discussServlet?type=list" target="rightFrame">评论区</a></li>
            <c:if test="${sessionScope.loginUser.userName != 'Admin1' && sessionScope.loginUser.userName != 'Admin2'}">
            <li><cite></cite><a href="/userServlet?type=self&id=${sessionScope.loginUser.id}&userName=${sessionScope.loginUser.userName}" target="rightFrame">个人信息</a></li>
            </c:if>
            <c:if test="${sessionScope.loginUser.userName == 'Admin1' || sessionScope.loginUser.userName == 'Admin2'}">
                <li><cite></cite><a href="../pageServlet" target="rightFrame">用户管理</a></li>
                <li><cite></cite><a href="user/userUpdate.jsp" target="rightFrame">添加用户</a></li>
                <li><cite></cite><a href="../messageServlet" target="rightFrame">消息管理</a></li>
                <li><cite></cite><a href="message/messageAdd.jsp" target="rightFrame">发布消息</a></li>
                <li><cite></cite><a href="../bookServlet" target="rightFrame">图书管理</a></li>
            </c:if>
        </ul>
    </dd>


    <dd>
        <div class="title">
            <span><img src="images/leftico02.png" /></span>借书卡
        </div>
        <ul class="menuson">
            <c:if test="${sessionScope.loginUser.userName != 'Admin1' && sessionScope.loginUser.userName != 'Admin2'}">
                <li><cite></cite><a href="../readerCardServlet?type=list" target="rightFrame">我的借书卡</a></li>
                <li><cite></cite><a href="../borrowServlet?type=query" target="rightFrame">书籍借阅</a></li>
                <li><cite></cite><a href="../borrowServlet?type=list" target="rightFrame">借阅记录</a></li>
            </c:if>
            <c:if test="${sessionScope.loginUser.userName == 'Admin1' || sessionScope.loginUser.userName == 'Admin2'}">
                <li><cite></cite><a href="../readerCardServlet?type=list" target="rightFrame">借书卡管理</a></li>
                <li><cite></cite><a href="../borrowServlet?type=list" target="rightFrame">借阅记录</a></li>
            </c:if>
                <li><cite></cite><a href="../commodityServlet" target="rightFrame">积分商城</a></li>
        </ul>
    </dd>

</dl>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
