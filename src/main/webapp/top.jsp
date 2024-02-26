<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="js/jquery.js"></script>
    <script type="text/javascript">
        $(function(){
            //顶部导航切换
            $(".nav li a").click(function(){
                $(".nav li a.selected").removeClass("selected")
                $(this).addClass("selected");
            })
        })
    </script>


</head>

<body style="background:url(images/topbg.gif) repeat-x;">

<div class="topleft">
    <a href="main.jsp" target="_parent"><img src="images/login_logo.png" title="系统首页" /></a>
</div>

<div class="topright">
    <ul>
        <li><span><img src="images/help.png" title="帮助" class="helpimg"/></span><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
        <li><a href="../logoutServlet" target="_parent">退出</a></li>
    </ul>

    <div class="user">
        <span>${sessionScope.loginUser.userName}</span>
        <i>欢迎登录</i>
    </div>

</div>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>