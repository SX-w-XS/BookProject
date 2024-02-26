<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <script src="../js/jquery.js" ></script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li>信息管理</li>
        <li><a href="../message/messageAdd.jsp">发布消息</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="../messageServlet" method="post" id="frm" >

        <ul class="forminfo">
            <input type="hidden" name="type" value="save">
            <li>
                <label>文章标题</label>
                <input name="messageTitile" id="messageTitile" type="text" class="dfinput" /><i id="ImessageTitile"> 标题不能超过30个字符</i>
            </li>
            <li>
                <label>类型</label>
                <cite><input name="messageState" type="radio" value="1" checked="checked" />通知&nbsp;&nbsp;&nbsp;&nbsp;<input name="messageState" type="radio" value="0" />新书发布</cite>
            </li>
            <li>
                <label>引用地址</label>
                <input name="messageConnect" type="text" class="dfinput" value="http://localhost:8080/hello-servlet" />
            </li>
            <li>
                <label>文章内容</label>
                <textarea name="messageContent" cols="" rows="" class="textinput"></textarea>
            </li>
            <li>
                <label>&nbsp;</label>
                <input name="" type="button" id="btn" class="btn" value="确认保存"/>
            </li>
        </ul>

    </form>
</div>
<script type="application/javascript" >
    var re;
    var FlagTitile=false;
    $(function (){
        $("#messageTitile").blur(function (){
            re = /^\S{4,20}$/;
            var mTitile=$("#messageTitile").val();
            if(re.test(mTitile)){
                FlagTitile=true
                $("#ImessageTitile").html("<span style='color:green'>格式正确 </span>")
            }else {
                FlagTitile=false
                $("#ImessageTitile").html("<span style='color:red'>格式错误 </span>")
            }
        })
        $("#btn").click(function (){
            if(FlagTitile===false){
                alert("消息题目格式错误")
            }
            else {
                $("#frm").submit();}
        })
    })
</script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>