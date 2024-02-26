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
        <li><a href="#">书籍借阅列表</a></li>
    </ul>
</div>
<div class="tools">

    <ul class="toolbar">
        <li>
            <form name="frm" action="../bookServlet" method="post">
                <input type="hidden" name="borrow" value="borrow">
                <input type="hidden" name="type" value="searchbyname">
                <input name="sname" type="search"  class="dfinput"  value="">
                <input type="submit" value="提交">
            </form>
        </li>
        <li>
            <form name="frm" action="../bookServlet" method="post">
                <input type="hidden" name="borrow" value="borrow">
                <input type="hidden" name="type" value="searchbytype">
                <select class="select1" name="stype">
                    <option  value = "0">小说</option>
                    <option  value = "1">科普读物</option>
                    <option  value = "2">科学技术</option>
                    <option  value = "3">医学</option>
                    <option  value = "4">艺术</option>
                    <option  value = "5">军事</option>
                </select>
                <input type="submit" value="确认">
            </form>
        </li>
    </ul>
</div>
<div class="rightinfo">
    <table class="tablelist">
        <thead>
        <tr>
            <th>编号<i class="sort"><img src="../images/px.gif" /></i></th>
            <th>书籍名称</th>
            <th>作者</th>
            <th>出版社</th>
            <th>书籍介绍</th>
            <th>价格</th>
            <th>状态</th>
            <th>类型</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.list}" var="book">
            <tr>
                <td>${book.bId}</td>
                <td>${book.bName}</td>
                <td>${book.author}</td>
                <td>${book.publish}</td>
                <td>
                    <a href="${book.introduction}" class="tablelink">简介</a>
                </td>
                <td>${book.price}</td>
                <td>
                    <c:if test="${book.state == 0}">空 闲</c:if>
                    <c:if test="${book.state == 1}">借 阅</c:if>
                    <c:if test="${book.state == 2}">下 架</c:if>
                    <c:if test="${book.state == 3}">其 他</c:if>
                </td>
                <td>
                    <c:if test="${book.type == 0}">小说网文</c:if>
                    <c:if test="${book.type == 1}">科普读物</c:if>
                    <c:if test="${book.type == 2}">科学技术</c:if>
                    <c:if test="${book.type == 3}">医学</c:if>
                    <c:if test="${book.type == 4}">艺术</c:if>
                    <c:if test="${book.type == 5}">军事</c:if>
                </td>
                <td>
                    <a href="/borrowServlet?type=borrow&bookName=${book.bName}" class="tablelink">借阅</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>