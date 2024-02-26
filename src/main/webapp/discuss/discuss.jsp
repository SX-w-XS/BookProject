<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>评论区页面</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery.js"></script>
</head>

<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li>信息管理</li>
        <li><a href="../discussServlet?type=list">评论区</a></li>
    </ul>
</div>
<div class="formbody">
    <c:if test="${sessionScope.loginUser.userName != 'Admin1' && sessionScope.loginUser.userName != 'Admin2'}">
    <div class="formtitle" style="font-size: 20px; color: #1c77ac">写评论</div>

        <div class="forminfo" style="position: relative; min-height: 1px; padding-right: 15px; padding-top: 10px; padding-left: 15px;" colspan="7">
            <form action="../discussServlet?type=save" method="post" id="frm">
                <textarea cols="30" rows="5" id="comment" name="comment" style="display: inline; width: 100%; resize: none" maxlength="90" placeholder="请输入评论内容...(最多90个字哦)" ></textarea>
                <input style="float: right" id="btn" name="btn" type="button" class="btn" value="发布评论"/>
            </form>
        </div>
    </c:if>
</div>
<div class="forminfo">
    <div class="formtitle" style="font-size: 20px; color: #1c77ac">看评论</div>
    <table class="tablelist">
        <tbody>
        <c:forEach items="${requestScope.list}" var="discuss">
            <tr>
                <td hidden="hidden">${discuss.id}</td>
                <td>${discuss.name}</td>
                <td>${discuss.content}</td>
                <td>${discuss.releaseTime}</td>
                <td>
                    <c:if test="${sessionScope.loginUser.userName == 'Admin1' || sessionScope.loginUser.userName == 'Admin2'}">
                        <a href="/discussServlet?type=delete&id=${discuss.id}" class="tablelink">删除</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script type="application/javascript" >
    var flagComment = false;
    $(function (){
        $("#comment").blur(function(){
            var str = /^\S$/;
            var comment = $("#comment").val();
            if(str.test(comment)||comment !== null){
                flagComment = true;
            } else{
                flagComment = false;
            }
        })

        $("#btn").click(function (){
            if (flagComment === false){
                alert("评论不能为空！")
            }else{
                $("#frm").submit();
            }
        })
    })
</script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

