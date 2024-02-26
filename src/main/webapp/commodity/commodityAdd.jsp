<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>积分商品添加页面</title>
  <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
</head>

<body>

<div class="place">
  <span>位置：</span>
  <ul class="placeul">
    <li><a href="../commodityServlet">积分商城</a></li>
    <li><a href="../commodity/commodityAdd.jsp">添加商品</a></li>
  </ul>
</div>

<div class="formbody">

  <div class="formtitle"><span>基本信息填写，*为必填项</span></div>
  <form id="frm" name="frm" action="<c:url value="/commodityServlet"/>" method="post">
    <ul class="forminfo">
      <c:if test="${empty commodity}">
        <input type = "hidden" name="id" value="-1">
        <input type = "hidden" name="type" value="save">
      </c:if>
      <c:if test="${not empty commodity}">
        <input type = "hidden" name="type" value="update">
        <input type = "hidden" name="id" value="${requestScope.commodity.cid}">
      </c:if>
      <li>
        <label>商品名</label>
        <input id="cname" name="cname" type="text" class="dfinput" value="${requestScope.commodity.cname}"/><i id="checkcname"></i>
      </li>
      <li>
        <label>价格</label>
        <input id="cprice" name="cprice" type="text" class="dfinput" value="${requestScope.commodity.cprice}" /><i id="checkprice"price></i>
      </li>
      <li>
        <label>库存数</label>
        <input id="cnumber" name="cnumber" type="text" class="dfinput" value="${requestScope.commodity.cnumber}" /><i id="checkcnumber"price></i>
      </li>
      <li>
        <label>上架日期</label>
        <input id="cpudate" name="cpudate" type="date" class="dfinput"  value="${requestScope.commodity.cpudate}" /><i id="checkcpudate"price></i>
      </li>
      <li><label>能否购买<b>*</b></label>
        <div class="vocation">
          <select class="select1" name="cstates">
            <option ${requestScope.commodity.cstate == "0" ? 'selected':''} value = "0">否</option>
            <option ${requestScope.commodity.cstate == "1" ? 'selected':''} value = "1">能</option>
          </select>
        </div>
      </li>
      <li><label>商品介绍</label>
        <textarea name="cintroduction" cols="" rows="" class="textinput">${requestScope.commodity.cintroduction}</textarea>
      </li>
      <li>
      <li>
        <label>&nbsp</label>
        <input id="btn" name="" type="button" class="btn" value="确认保存"/>
      </li>
    </ul>
  </form>
</div>

<script type="application/javascript">
  var flagCname = false;
  var flagCprice = false;
  var flagCnumber = false;
  var flagCpudate = false;

  $(function (){
    $("#cname").blur(function() {
      re = /^\S{2,15}$/;
      var cname = $("#cname").val();
      if (re.test(cname)) {
        flagCname = true
        $("#checkcname").html("<span style = 'color:green'>输入正确</span>")
      } else {
        flagCname = false
        $("#checkcname").html("<span style = 'color:red'>请输入2~15位字符</span>")
      }
    });

    $("#cprice").blur(function() {
      re = /^([1-9][0-9]*)?$/;
      var cprice = $("#cprice").val();
      if (re.test(cprice)) {
        flagCprice = true
        $("#checkprice").html("<span style = 'color:green'>输入正确</span>")
      } else {
        flagCprice = false
        $("#checkprice").html("<span style = 'color:red'>请输入整数</span>")
      }
    });

    $("#cnumber").blur(function() {
      re = /^(([1-9]\d*)|0)$/;
      var cnumber = $("#cnumber").val();
      if (re.test(cnumber)) {
        flagCnumber = true
        $("#checkcnumber").html("<span style = 'color:green'>输入正确</span>")
      } else {
        flagCnumber = false
        $("#checkcnumber").html("<span style = 'color:red'>请输入2~15位整数</span>")
      }
    });

    $("#cpudate").blur(function() {
      re = /^\d{4}[-|/]?\d{2}[-|/]?\d{2}$/;
      var cname = $("#cpudate").val();
      if (re.test(cname)) {
        flagCpudate = true
        $("#checkcpudate").html("<span style = 'color:green'>输入正确</span>")
      } else {
        flagCpudate = false
        $("#checkcpudate").html("<span style = 'color:red'>请按照XXXX/XX/XX或XXXX-XX-XX格式正确输入</span>")
      }
    });

    $("#btn").click(function () {
         if(flagCname === false){
      alert("商品名输入错误");
    }else if(flagCprice === false){
      alert("价格输入错误，价格不能为0");
    }else if(flagCnumber === false){
      alert("库存数量为整数");
    }else if(flagCpudate === false){
      alert("上架日期格式错误");
    }else{
        $("#frm").submit();
      }
    })
  })
</script>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
