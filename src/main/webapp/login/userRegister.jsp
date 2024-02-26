<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户注册页面</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <script src="../js/jquery.js"></script>
</head>

<body>

<div class="place">
    <ul class="placeul">
        <li><a href="../login/login.jsp">返回登录页面</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>用户基本信息填写，*为必填项</span></div>
    <form action="../loginServlet" method="post" id="frm" name="frm">
    <ul class="forminfo">
        <input type="hidden" name="type" value="signup">
        <li>
            <label>用户名</label>
            <input id="userName" name="userName" type="text" maxlength="10" class="dfinput" value=""/><i>*用户名为4~10个字符</i><i id="userNameI"></i>
        </li>
        <li>
            <div class="vocation">
            <label>性别</label>
                <select class="dfinput" name="sex">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select><i>*</i>
            </div>
        </li>
        <li>
            <label>生日</label>
            <input id="birthday" name="birthday" type="date" class="dfinput" value=""/><i>*</i>
        </li>
        <li>
            <label>身份证</label>
            <input id="identify" name="identify" type="text" maxlength="18" class="dfinput" value=""/><i>*身份证号为18位字符</i><i id="identifyI"></i>
        </li>
        <li>
            <label>电话号码</label>
            <input id="tel" name="tel" type="text" maxlength="11" class="dfinput" value=""/><i>*电话号码为11位数字</i><i id="telI"></i>
        </li>
        <li>
            <label>电子邮箱</label>
            <input id="email" name="email" type="email" maxlength="20" class="dfinput" value=""/><i>*邮箱长度为6~20位字符</i><i id="emailI"></i>
        </li>
        <li>
            <label>密码</label>
            <input id="password" name="password" type="password" maxlength="15" class="dfinput" value=""/><i>*密码为6~15位数字、大小写字母组成</i><i id="passwordI"></i>
        </li>
        <li>
            <label>确认密码</label>
            <input id="rePassword" name="rePassword" type="password" maxlength="15" class="dfinput" value=""/><i>*请再次输入密码</i><i id="rePasswordI"></i>
        </li>
        <li>
            <label>&nbsp;</label>
            <input id="btn" name="" type="button" class="btn" value="确认保存"/>
            <c:if test="${not empty sessionScope.massage1}">
                <span style="color:red">${sessionScope.massage1}</span>
            </c:if>
        </li>
    </ul>
    </form>
</div>
<script type="application/javascript" >
    var re;
    var flagName = false;
    var flagBirthday = false;
    var flagIdentify = false;
    var flagTel = false;
    var flagEmail = false;
    var flagPassword = false;
    var flagRePassword = false;
    $(function (){
        $("#userName").blur(function (){
            var userName = $(this).val();
            re = /^\S{4,10}$/;
            if(re.test(userName)) {
                $.get("/userServlet?type=check&userName="+userName,function (data){
                    if(data.trim() === 'success'){//用户名可用
                        flagName = true;
                        $("#userNameI").html("<span style='color:green'> 用户名可用</span>");
                    }
                    else{//用户已存在
                        flagName = false;
                        $("#userNameI").html("<span style='color:red'> 用户名已存在或格式错误，请更改</span>");
                    }
                })
            } else{//用户已存在
                flagName = false;
                $("#userNameI").html("<span style='color:red'> 用户名已存在或格式错误，请更改</span>");
            }
        });

        function birthdayCheck(){
            var birthday = $("#birthday").val();
            flagBirthday = birthday !== "";
        }

        $("#identify").blur(function (){
            var identify = $("#identify").val();
            re = /^[1-9][0-9]{16}[0-9xX]$/;
            if(re.test(identify) && identify !== ""){
                flagIdentify = true;
                $("#identifyI").html("<span style='color:green'>输入格式正确</span>");
            }else{
                flagIdentify = false;
                $("#identifyI").html("<span style='color:red'>输入格式错误，请更改</span>");
            }
        })

        $("#tel").blur(function (){
            re = /^[1-9][0-9]{10}$/;
            var tel = $("#tel").val()
            if(re.test(tel)){
                flagTel = true;
                $("#telI").html("<span style='color:green'>输入格式正确</span>");
            }else{
                flagTel = false;
                $("#telI").html("<span style='color:red'>输入格式错误，请更改</span>");
            }
        })

        $("#email").blur(function (){
            re = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.(com|cn|net)$/;
            var email = $("#email").val()
            if(re.test(email)){
                flagEmail = true;
                $("#emailI").html("<span style='color:green'>输入格式正确</span>");
            }else{
                flagEmail = false;
                $("#emailI").html("<span style='color:red'>输入格式错误，请更改</span>");
            }
        })

        $("#password").blur(function (){
            re = /^[0-9a-zA-Z]{6,15}$/
            var password = $("#password").val();
            if(re.test(password)){
                flagPassword = true;
                $("#passwordI").html("<span style='color:green'>输入格式正确</span>");
            }else{
                flagPassword = false;
                $("#passwordI").html("<span style='color:red'>输入格式错误，请更改</span>");
            }

        })

        $("#rePassword").blur(function (){
            var password = $("#password").val();
            var rePassword = $("#rePassword").val();
            if (password === rePassword){
                flagRePassword = true;
                $("#rePasswordI").html("<span style='color:green'>输入密码正确</span>");
            }else{
                flagRePassword = false;
                $("#rePasswordI").html("<span style='color:red'>输入两次密码不一致，请重试</span>");
            }
        })

        $("#btn").click(function (){
            birthdayCheck()
            if (flagName === false){
                alert("用户名重复，请更改！");
            }else if(flagBirthday === false){
                alert("请输入出生日期！");
            }else if(flagIdentify === false){
                alert("身份证格式错误！");
            }else if(flagTel === false){
                alert("电话格式错误！");
            }else if(flagEmail === false){
                alert("邮箱格式错误！");
            }else if (flagPassword === false){
                alert("密码格式错误！");
            }else if(flagRePassword === false){
                alert("确认密码输入错误！");
            }else{
                $("#frm").submit();
            }
        })
    })
</script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>