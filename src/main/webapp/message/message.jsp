<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.PageInfo" %>
<%@ page import="bean.UserInfo" %>

<% int i=0;%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
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
        function deleteUser(Id){
            if(window.confirm("是否删除记录"+Id)){
                console.log("确定");
                location.href="/messageServlet?type=delete&id="+Id;
            }


        }
        function  searchUser(keyWord){
            location.href="/userServlet?type=search&keyWord="+keyWord;
        }
    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li>信息管理</li>
        <li><a href="../messageServlet">发布消息管理</a></li>
    </ul>
</div>


<div class="rightinfo">
    <table class="tablelist">
        <thead>
        <tr>
            <th>编号<i class="sort"><img src="../images/px.gif" /></i></th>
            <th>消息标题</th>
            <th>消息类型</th>
            <th>备注</th>
            <c:if test="${sessionScope.loginUser.userName == 'Admin1' || sessionScope.loginUser.userName == 'Admin2'}">
            <th>操作</th>
            </c:if>
        </tr>
        </thead>
        <tbody>

        <i >
            <div>
                <c:forEach items="${requestScope.list}" var="message" >
                    <tr>
                        <td>${message. messageId}</td>
                        <td >
                            <a href="${message.messageConnect}">${message.messageTitile}</a>
                        </td>
                        <td>
                            <c:if test="${message.messageState==1}">
                                消息通知
                            </c:if>

                            <c:if test="${message.messageState==0}">
                                新书发布
                            </c:if>
                        </td>
                        <td>
                                ${message.messageContent}
                        </td>
                        <c:if test="${sessionScope.loginUser.userName == 'Admin1' || sessionScope.loginUser.userName == 'Admin2'}">
                        <td>
                            <a href="JavaScript:void deleteUser(${message.messageId})"  class="tablelink"> 删除</a>
                        </td>
                        </c:if>

                    </tr>
                </c:forEach>
            </div>
        </i>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
