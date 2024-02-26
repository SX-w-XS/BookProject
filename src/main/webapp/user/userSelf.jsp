<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="pageBean" class="bean.PageInfo" scope="request"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户信息添加修改页面</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <script src="../js/jquery.js" ></script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li>信息管理</li>
        <li><a href="../userServlet">添加用户</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>用户基本信息</span></div>
    <form action="../userServlet" method="post"  id="frm" name="frm">
        <ul class="forminfo">
            <input type="hidden" name="type" value="save">
            <c:if test="${not empty user}">
                <input type="hidden" id="userId" name="id" value="${user.id}">
            </c:if>
            <li>
                <label  style="font-family:'黑体';font-size: 15px">账号 :</label>
               <li>${user.userName}</li>
            </li>
            <li>
                <label  style="font-family:'黑体';font-size: 15px">性别 :</label>
                <li>${user.userSex}</li>
            </li>
            <li>
                <label  style="font-family:'黑体';font-size: 15px">出生日期:</label>
                <li>${user.birthday}</li>
            </li>

            <li>
                <label  style="font-family:'黑体';font-size: 15px">身份号码:</label>
                <li>${user.identify}</li>
            </li>
            <li>
                <label  style="font-family:'黑体';font-size: 15px">积分 :</label>
                <li>${user.integral}</li>
            </li>

            <li>
                <label  style="font-family:'黑体';font-size: 15px">手机 :</label>
                <li>${user.tel}</li>
            </li>
            <li>
                <label  style="font-family:'黑体';font-size: 15px">邮箱 :</label>
                <li>${user.email}</li>
            </li>


        </ul>
    </form>
</div>
<div  style="position:absolute;top: 100px;right: 420px;width: 500px;height: 250px;border: 0px solid #73AD21;font-family:'黑体';font-size: 20px" >
    猜你喜欢
    <table style="border: 1px solid black;">
        <tr>
            书籍
        </tr>

        <tr>
          <c:forEach items="${requestScope.list1}" var="list1">
             <a href="../borrowServlet?type=query">
              <li style="font-family:'宋体';font-size: 15px;height: 30px">${list1}</li></a>
              <li> </li>
              <li></li>

          </c:forEach>
        </tr>
    </table>
</div>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>

</body>
</html>