<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>书籍信息添加修改页面</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <script src="/js/jquery.js" ></script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li>首页</li>
        <li>添加书籍</li>
    </ul>
</div>

<div class="formbody">

<div class="formtitle"><span>书籍基本信息填写，*为必填项</span></div>
<form id="frm" name="frm" action="/bookServlet" method="post">
    <ul class="forminfo">
        <c:if test="${empty book}">
            <input type = "hidden" name="id" value="-1">
            <input type = "hidden" name="type" value="save">
        </c:if>
        <c:if test="${not empty book}">
            <input type = "hidden" name="type" value="update">
            <input type = "hidden" name="id" value="${book.bId}">
        </c:if>
        <li>
            <label>书名</label>
            <input id="bookName" name="bookName" type="text" class="dfinput" value="${book.bName}"/><i id="checkname">*</i>
        </li>
        <li>
            <label>作者</label>
            <input id="author" name="author" type="text" class="dfinput" value="${book.author}"/><i id="checkauthor">*</i>
        </li>
        <li>
            <label>出版社</label>
            <input id="publish" name="publish" type="text" class="dfinput" value="${book.publish}"/><i id="checkpublish">*</i>
        </li>
        <li>
            <label>书籍编码</label>
            <input id="isbn" name="isbn" type="text" class="dfinput"  value="${book.isbn}"/><i id="checkisbn">*</i>
        </li>
        <li>
            <label>价格</label>
            <input id="price" name="price" type="text" class="dfinput"  value="${book.price}"/><i id="checkprice">*</i>
        </li>
        <li>
            <label>书架号</label>
            <input id="pressMark" name="pressMark" type="text" class="dfinput"  value="${book.pressmark}"/><i id="checkpressmark">*</i>
        </li>
        <li>
            <label>出版日期</label>
            <input id="puDate" name="puDate" type="date" class="dfinput"  value="${book.pudate}"/><i id="checkpudate">*</i>
        </li>
        <li>
            <label>书籍类型</label>
            <div class="vocation">
                <select class="select1" name="bookType">
                    <option ${requestScope.book.type == "0" ? 'selected':''} value = "0">小说</option>
                    <option ${requestScope.book.type == "1" ? 'selected':''} value = "1">科普读物</option>
                    <option ${requestScope.book.type == "2" ? 'selected':''} value = "2">科学技术</option>
                    <option ${requestScope.book.type == "3" ? 'selected':''} value = "3">医学</option>
                    <option ${requestScope.book.type == "4" ? 'selected':''} value = "4">艺术</option>
                    <option ${requestScope.book.type == "5" ? 'selected':''} value = "5">军事</option>
                </select>
            </div>
        </li>
        <li>
            <label>书籍介绍</label>
            <label>
                <textarea name="introduction" cols="" rows="" class="textinput">${book.introduction}></textarea>
            </label>
        </li>
        <li>
            <label>&nbsp</label>
            <input id="btn" name="" type="button" class="btn" value="确认保存"/>
        </li>
    </ul>
</form>
</div>

<script type="application/javascript">
    var flagBookName = false;
    var flagAuthor = false;
    var flagPublish = false;
    var flagIsbn = false;
    var flagPrice = false;
    var flagPressMark =false;
    var flagPuDate = false;

    $(function (){
        $("#bookName").blur(function() {
            re = /^\S{2,15}$/;
            var cname = $("#bookName").val();
            if (re.test(cname)) {
                flagBookName = true
                flag = true
                $("#checkname").html("<span style = 'color:green'>输入正确</span>")
            } else {
                flagBookName = false
                $("#checkname").html("<span style = 'color:red'>请输入2~15位字符</span>")
            }
        });

        $("#author").blur(function(){
            re=/^\S{2,10}$/;
            var author = $("#author").val();
            if(re.test(author)){
                flagAuthor = true
                $("#checkauthor").html("<span style = 'color:green'>输入正确</span>")
            }else{
                flagAuthor = false
                $("#checkauthor").html("<span style = 'color:red'>请输入2~10位字符</span>")
            }
        })

        $("#publish").blur(function(){
            re=/^\S{2,10}$/;
            var publish = $("#publish").val();
            if(re.test(publish)){
                flagPublish = true
                $("#checkpublish").html("<span style = 'color:green'>输入正确</span>")
            }else{
                flagPublish = false
                $("#checkpublish").html("<span style = 'color:red'>请输入2~10位字符</span>")
            }
        })

        $("#isbn").blur(function(){
            re=/^978[-]7[-]\d{2}[-]\d{6}[-]\d$/;
            var isbn = $("#isbn").val();
            if(re.test(isbn)){
                flagIsbn = true
                $("#checkisbn").html("<span style = 'color:green'>输入正确</span>")
            }else{
                flagIsbn = false
                $("#checkisbn").html("<span style = 'color:red'>请按照978-7-XX-XXXXXX-X的格式输入书籍编码</span>")
            }
        })

        $("#price").blur(function(){
            re=/^([1-9][0-9]*)+(.[0-9]{1,2})?$/;
            var price = $("#price").val();
            if(re.test(price)){
                flagPrice = true
                $("#checkprice").html("<span style = 'color:green'>输入正确</span>")
            }else{
                flagPrice = false
                $("#checkprice").html("<span style = 'color:red'>请正确输入价格</span>")
            }
        })

        $("#pressMark").blur(function(){
            re=/^[1-2]{0,1}[0-9]{0,1}$/;
            var pressMark = $("#pressMark").val();
            if(re.test(pressMark) && pressMark !== "" && pressMark !== 0){
                flagPressMark = true
                $("#checkpressmark").html("<span style = 'color:green'>输入正确</span>")
            }else{
                flagPressMark = false
                $("#checkpressmark").html("<span style = 'color:red'>请输入书架号1~29</span>")
            }
        })


        $("#puDate").blur(function() {
            re = /^\d{4}[-|/]?\d{2}[-|/]?\d{2}$/;
            var puDate = $("#puDate").val();
            if (re.test(puDate)) {
                flagPuDate = true
                $("#checkpudate").html("<span style = 'color:green'>输入正确</span>")
            } else {
                flagPuDate = false
                $("#checkpudate").html("<span style = 'color:red'>请按照XXXX/XX/XX或XXXX-XX-XX格式正确输入</span>")
            }
        });



        $("#btn").click(function () {
            if(flagBookName === false){
                alert("书籍名称输入错误");
            }else if(flagAuthor === false){
                alert("作者输入错误");
            }else if(flagPublish === false){
                alert("出版社输入错误");
            }else if(flagIsbn === false){
                alert("书籍编码输入错误");
            }else if(flagPrice === false){
                alert("书籍价格输入错误")
            } else if(flagPressMark === false){
                alert("书架号输入错误");
            }else if(flagPuDate === false){
                alert("出版日期输入错误")
            }else {
                $("#frm").submit();
            }
        })
    })
</script>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>

</body>
</html>
