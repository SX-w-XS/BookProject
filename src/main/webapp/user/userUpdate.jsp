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
             <label>账号</label>
             <input id="uname" name="userName"  type="text" class="dfinput" value="${user.userName}"/><i id="userNameI">*输入4-10位字符</i>
         </li>
         <li>
             <label>性别</label>
             <input id="usex0" name="userSex"  type="radio" class="dfinput" value="男" style="zoom: 50%" checked="true"/>男<input id="usex1" name="userSex"  type="radio" class="dfinput" value="女" style="zoom: 50%"/> 女 <i>*</i>
         </li>
         <li>
             <label>出生日期</label>
             <input id="birth" name="birth" type="date" class="dfinput" value="${user.birthday}" /><i>*</i>
         </li>
         <li>
             <label>身份号码</label>
             <input id="idNumber" name="idNumber" type="text" class="dfinput"  value="${user.identify}" /><i id="idNumberI" >*</i>
         </li>
         <li>
             <label>积分</label>
             <input id="bookInt" name="bookInt" type="text" class="dfinput" value="${user.integral}" /><i id="bookIntI">*输入非负整数</i>
         </li>
         <li>
             <label>密码</label>
             <input id="upassword" name="password" type="text" class="dfinput" value="${user.password}" /><i id="upasswordI">*6-15位数字或者字母</i>
         </li>
         <li>
             <label>确认密码</label>
             <input id="upassword1" name="password1" type="text" class="dfinput" /><i id="upassword1I">*</i>
         </li>
         <li>
             <label>手机</label>
             <input id="phoneNum" name="phoneNum" type="text" class="dfinput" value="${user.tel}" /><i id="phoneNumI">*</i>
         </li>
         <li>
             <label>邮箱</label>
             <input id="email" name="email" type="email" class="dfinput"value="${user.email}" /><i id="emailI">*</i>
         </li>
         <li>
             <label>&nbsp;</label>
             <input name="" type="button" id="btn" class="btn" value="确认保存"/>
         </li>
        </ul>
    </form>

</div>
<script type="application/javascript" >
    var userId=$("#userId").val();
    var re;
    if(userId===null){
        var flagName=false;
        var flagPassWord=false;
        var flagBirth=false;
        var flagIdNumber=false;
        var flagBookInt=false;
        var flagTel=false;
        var flagEmail=false;}
    else{
        flagName=true;
        flagPassWord=true;
        flagBirth=true;
        flagIdNumber=true;
        flagBookInt=true;
        flagTel=true;
    }
  $(function (){
      $("#uname").blur(function (){

          var userName=$(this).val();
          if(userId ===null){
              //正则表达式判断账户
              re = /^\S{4,10}$/;

              //Ajax 判断账户
              if(re.test(userName)) {
                  $.get("/userServlet?type=check&userName="+userName,function (data){
                      //alert(data);
                      if(data.trim()=== 'success'){
                          //可用
                          flagName=true;
                          $("#userNameI").html("<span style='color:green'> 账号可用</span>")
                      }
                  })}
              else{
                  //已存在
                  flagName=false;
                  $("#userNameI").html("<span style='color:red'> 账号不可用，请更改</span>")
              }
          }else{
              re = /^\S{4,10}$/;

              //Ajax 判断账户
              if(re.test(userName)) {  flagName=true;
                  $("#userNameI").html("<span style='color:green'> 账号可用</span>")}else {
                  flagName=false;
                  $("#userNameI").html("<span style='color:red'> 账号不可用，请更改</span>")
              }

          }
      });

      function  IdnumberCheck(){
          var Idnumber=$("#idNumber").val();
          if(Idnumber===""){
              flagIdNumber=false
          }else{
              flagIdNumber=true
          }
      }
      function  birthCheck(){
          var birth=$("#birth").val();
          if(birth===""){
              flagBirth=false
          }else{
              flagBirth=true
          }
      }
      $("#idNumber").blur(function (){
          var idNumber=$("#idNumber").val();
          re= /^[1-9][0-9]{16}[0-9xX]$/;
        if(re.test(idNumber) &&idNumber!==""){
            flagIdNumber=true
            $("#idNumberI").html("<span style='color:green'> 输入正确</span>")
        }else{
            flagIdNumber=false
            $("#idNumberI").html("<span style='color:red'> 输入错误</span>")
        }
     })

      $("#bookInt").blur(function (){
          re= /^([1-9][0-9]*|0)$/;
          var bookInt = $("#bookInt").val();
          if(re.test(bookInt)&&bookInt !== ""){
              flagBookInt=true
              $("#bookIntI").html("<span style='color:green'> 输入正确</span>")
          }else{
              flagBookInt=false
              $("#bookIntI").html("<span style='color:red'> 请输入正整数</span>")
          }
      })

      $("#upassword").blur(function (){
          re = /^[0-9a-zA-Z]{6,15}$/
          var password = $("#upassword").val();
          if(re.test(password)){
              flagPassWord=true
              $("#upasswordI").html("<span style='color:green'> 密码可用</span>")
          }else{
              flagPassWord=false
              $("#upasswordI").html("<span style='color:red'> 密码不可用</span>")
          }

      })

      $("#upassword1").blur(function (){

          var password = $("#upassword").val();
          var password1 = $("#upassword1").val();
          if (password === password1){
              flagPassWord=true
              $("#upassword1I").html("<span style='color:green'> 密码正确</span>")
          }else{
              flagPassWord=false
              $("#upassword1I").html("<span style='color:red'> 密码错误</span>")
          }
      })
      $("#phoneNum").blur(function (){
        re = /^[1-9][0-9]{10}$/
          var phoneNum = $("#phoneNum").val()
          if(re.test(phoneNum)){
              flagTel=true
              $("#phoneNumI").html("<span style='color:green'> 输入正确</span>")
          }else{
              flagTel=false
              $("#phoneNumI").html("<span style='color:red'> 输入错误</span>")
          }
      })

      $("#email").blur(function (){
          re = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.(com|cn|net)$/
          var email = $("#email").val()
          if(re.test(email)){
              flagEmail=true
              $("#emailI").html("<span style='color:green'> 输入正确</span>")
          }else{
              flagEmail=false
              $("#emailI").html("<span style='color:red'> 输入错误</span>")
          }
      })

      $("#btn").click(function (){
          birthCheck()
          IdnumberCheck()
          if (flagName===false){
              alert("账号错误，请更改")
          }else if (flagPassWord===false){
              alert("确认密码错误")
          }else if(flagBirth===false){
              alert("请输入出生日期")
          }else if(flagIdNumber===false){
              alert("身份证输入错误")
          }else if(flagBookInt===false){
              alert("积分请输入非负整数")
          }else if(flagTel===false){
              alert("电话输入错误")
          }else if(flagEmail===false){
              alert("邮箱格式错误")
          }
          else{
          $("#frm").submit();}
      })
  })
</script>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>

</body>
</html>