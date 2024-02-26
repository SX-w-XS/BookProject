<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>修改密码页面</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div class="place">
    <ul class="placeul">
        <li><a href="../login/login.jsp">返回登录页面</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>用户密码修改，*为必填项</span></div>
    <form action="../loginServlet" method="post">
        <ul class="forminfo">
            <input type="hidden" name="type" value="update">
            <li>
                <label>用户名</label>
                <input name="userName" type="text" maxlength="10" class="dfinput" /><i>*</i>
            </li>
            <li>
                <label>身份证</label>
                <input name="identify" type="text" maxlength="18" class="dfinput" /><i>*</i>
            </li>
            <li>
                <label>密码</label>
                <input name="password" type="password"maxlength="15" class="dfinput" /><i>*请输入修改后的密码，密码为6~15位数字、大小写字母组成</i>
            </li>
            <li>
                <label>确认密码</label>
                <input name="re_password" type="password" maxlength="15" class="dfinput" /><i>*请再次确认密码</i>
            </li>
            <li>
                <label>&nbsp</label><input name="" type="submit" class="btn" value="确认保存"/>
                <c:if test="${empty sessionScope.massage2}">
                </c:if>
                <c:if test="${not empty sessionScope.massage2}">
                    <span style="color:red">${sessionScope.massage2}</span>
                </c:if>
            </li>
        </ul>
    </form>
</div>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
