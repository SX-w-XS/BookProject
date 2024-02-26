<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        function deleteUser(userName){
           if(window.confirm("是否删除记录"+userName)){
               console.log("确定");
               location.href="/userServlet?type=delete&name="+userName;
           }
           else{
               console.log("111");
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
        <li><a href="../pageServlet">用户管理</a></li>
    </ul>
</div>

<div class="rightinfo">
    <div class="tools"  style="height: auto">
        <ul class="toolbar">
            <li class="click">
                <span><img src="../images/t01.png" />
                </span>添加
            </li>
        </ul>
    </div>
    <div >
        <form method="post" action="../userServlet?type=search" id="searchform">
        <li >
        <input type="search"  placeholder="请输入关键字" id="keyWord" name="keyWord" class="input" size="20" style="width: 30%;heigh: 10x 150% ">
        <input type="submit" value="搜索"   class="button color-rect-border">
        </li>
        </form>
    </div>
    <table class="tablelist">
        <thead>
        <tr>
            <th>编号<i class="sort"><img src="../images/px.gif" /></i></th>
            <th>用户名</th>
            <th>密码</th>
            <th>性别</th>
            <th>出生日期</th>
            <th>身份证</th>
            <th>积分</th>
            <th>手机号</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>

        <i>
            <div>
            <c:forEach items="${pageInfo.list}" var="user" >
            <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.password}</td>
                <td>${user.userSex}</td>
                <td>${user.birthday}</td>
                <td>${user.identify}</td>
                <td>${user.integral}</td>
                <td>${user.tel}</td>
                <td>${user.email}</td>
                <td>
                    <a href="/userServlet?type=queryById&id=${user.id}" class="tablelink">修改</a>
                    <a href="JavaScript: deleteUser('${user.userName}')" class="tablelink"> 删除</a>
                </td>
            </tr>
            </c:forEach>
            </div>
        </i>
        <c:forEach items="${requestScope.list}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.password}</td>
                <td>${user.userSex}</td>
                <td>${user.birthday}</td>
                <td>${user.identify}</td>
                <td>${user.integral}</td>
                <td>${user.tel}</td>
                <td>${user.email}</td>

                <td>
                    <a href="/userServlet?type=queryById&id=${user.id}" class="tablelink">修改</a>
                    <a href="JavaScript: deleteUser(${user.userName})" class="tablelink"> 删除</a>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="fenye">
        第${pageInfo.currentPage}/${pageInfo.totalPage}页
        总记录数:${pageInfo.totalSize}条
        每页${pageInfo.pageSize}条
        <c:if test="${pageInfo.currentPage != 1}">
            <a href="${pageContext.request.contextPath}/pageServlet?currentPage=1">
               <button  >[首页]</button>
            </a>
            <a href="${pageContext.request.contextPath}/pageServlet?currentPage=${pageInfo.currentPage-1}">
               <button >[上一页]</button>
            </a>
        </c:if>
        <c:if test="${pageInfo.currentPage != pageInfo.totalPage}">
            <a href="${pageContext.request.contextPath}/pageServlet?currentPage=${pageInfo.currentPage+1}">
              <button > [下一页]</button>
            </a>
            <a href="${pageContext.request.contextPath}/pageServlet?currentPage=${pageInfo.totalPage}">
               <button> [尾页]</button>
            </a>
        </c:if>
    </div>
</div>
    <div class="tip">
        <div class="tiptop"><span>提示信息</span><a></a></div>
        <div class="tipinfo">
            <span><img src="../images/t01.png" /></span>
            <div class="tipright">
                <p>是否要添加用户信息 ？</p>
                <cite>如果是请点击确定按钮，否则请点取消。</cite>
            </div>
        </div>
        <div class="tipbtn">
            <a href="../user/userUpdate.jsp" ><input name="" type="button"  class="sure" value="确定" />&nbsp;</a>
            <a href="../userServlet"> <input name="" type="button"  class="cancel" value="取消" /> </a>
        </div>
    </div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>