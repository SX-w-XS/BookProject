<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>读书卡信息修改页面</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li>借书卡管理</li>
        <li><a href="/readerCardServlet?type=list">借书卡信息</a> </li>
        <li><a href="#">修改借书卡信息</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>修改信息填写</span></div>
    <form action="/readerCardServlet" method="post">
        <ul class="forminfo">
            <input type="hidden" name="type" value="update">
            <input type="hidden" name="id" value="${card.id}">
            <li>
                <label>读书卡状态</label>
                <div class="vocation">
                    <select class="dfinput" name="state">
                        <option ${sessionScope.card.cardstate == '0'? 'selected':''} value="0">不可以使用</option>
                        <option ${sessionScope.card.cardstate == '1'? 'selected':''} value="1">可以使用</option>
                    </select>
                </div>
            </li>
            <li>
                <label>最长借阅时间</label>
                <input name="time" type="text" maxlength="2" class="dfinput" value="${card.readTime}"/>
            </li>
            <li>
                <label>&nbsp</label><input name="" type="submit" class="btn" value="确认保存"/>
            </li>
        </ul>
    </form>
</div>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
