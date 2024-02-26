<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>书籍借阅页面</title>
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
        <li><a href="#">借书卡</a></li>
        <li><a href="#">借阅记录</a></li>
    </ul>
</div>

<div class="rightinfo">
    <table class="tablelist">
        <thead>
        <tr>
            <th>编号<i class="sort"><img src="../images/px.gif" /></i></th>
            <th>读者姓名</th>
            <th>书籍名称</th>
            <th>借阅时间</th>
            <th>归还时间</th>
            <th>借阅书籍状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.list}" var="borrow">
            <tr>
                <td>${borrow.id}</td>
                <td>${borrow.readerName}</td>
                <td>${borrow.bookName}</td>
                <td>${borrow.borrowTime}</td>
                <td>${borrow.backTime}</td>
                <td>
                    <c:if test="${borrow.borrowState == 0}"><span style="color: red">借阅中</span></c:if>
                    <c:if test="${borrow.borrowState == 1}"><span style="color: green">已归还</span></c:if>
                </td>
                <td>
                    <c:if test="${borrow.borrowState == 0 && sessionScope.loginUser.userName != 'Admin1' && sessionScope.loginUser.userName != 'Admin2'}">
                        <a href="/borrowServlet?type=back&bookName=${borrow.bookName}" class="tablelink">归还</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
