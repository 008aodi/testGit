<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head >

    <meta http-equiv="content-type" content="text/html;charset=UTF-8" >

</head>
<body>


<h1>添加商品</h1>
<form action="${pageContext.request.contextPath}/product/insert" method="POST" enctype="multipart/form-data">
    商品名称:<input type="text" name="name"><br/>
    商品价格:<input type="text" name="price"><br/>
    商品描述:<input type="text" name="context"><br/>
    商品图片:<input type="file" name="upfile"><br/>
    商品状态:<input type="text" name="status"><br/>
    商品上产日期:<input type="text" name="dateString"><br/>
    商品产地:<input type="text" name="produce"><br/>
    <input type="submit" value="提交"><br/>
</form>


</body>
</html>
