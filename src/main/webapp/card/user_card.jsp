<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>借书卡信息页面</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery.js"></script>

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
        <li>借书卡信息</li>
    </ul>
</div>

<div class="rightinfo">
    <table class="tablelist">
        <thead>
        <tr>
            <th>编号<i class="sort"><img src="../images/px.gif" /></i></th>
            <th>读者姓名</th>
            <th>读书卡状态</th>
            <th>借阅书籍数</th>
            <th>最长借阅时间(天)</th>
            <th>积分</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${card.id}</td>
                <td>${card.readerName}</td>
                <td>${card.cardState==0?'不可以使用':'可以使用'}</td>
                <td>${card.readCount}</td>
                <td>${card.readTime}</td>
                <td>${card.integral}</td>
            </tr>
        </tbody>
    </table>
    <div>
        <label>
            <c:if test="${not empty sessionScope.msg}">
                <span style="color:red">${sessionScope.msg}</span>
            </c:if>
        </label>
    </div>
</div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
