<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>图书管理系统登录页面</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="../js/jquery.js"></script>
    <script src="../js/cloud.js" type="text/javascript"></script>

    <script language="javascript">
        $(function(){
            $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
            $(window).resize(function(){
                $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
            })
        });
    </script>

</head>

<body style="background-color:#1c77ac; background-image:url(../images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">

<div class="logintop">
    <span>欢迎登录图书管理系统</span>
</div>

<div class="loginbody">
    <span class="systemlogo"></span>
    <div class="loginbox">
        <form name="frm" action="/loginServlet" method="post">
        <ul>
            <li>
                <input name="userName" type="text" class="loginuser" value="Admin1"/>
            </li>
            <li>
                <input name="password" type="password" class="loginpwd" value="admin12345"/>
            </li>
            <li>
                <input type="button" class="loginbtn" value="登录" onclick="check()"/>
                <label>
                    <a href="../login/userRegister.jsp">注册用户</a>
                </label>
                <label>
                    <a href="../login/passwordUpdate.jsp">忘记密码？</a>
                </label>
                <label>
                    <c:if test="${not empty sessionScope.massage}">
                        <span style="color:red">${sessionScope.massage}</span>
                    </c:if>
                </label>
            </li>
        </ul>
        </form>
    </div>
</div>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
<script>
    function check(){
        if(frm.userName.value == ""){
            alert("用户名不能为空！")
            frm.userName.focus();
            return ;
        }
        if(frm.password.value == ""){
            alert("密码不能为空！");
            frm.password.focus();
            return ;
        }
        frm.submit();
    }
    <%
        String loginFlag = request.getParameter("loginFlag");
        if(loginFlag == null){ //无登录失败标识，是正常访问登录页面
            loginFlag = "";
        }
        if(loginFlag.equals("0")){
            System.out.println("用户名或密码输入错误，请重试！");
        }
    %>
</script>