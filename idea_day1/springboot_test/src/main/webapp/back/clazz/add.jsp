<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
    <style type="text/css">
        body{
            text-align: center;
        }
        select{
            width:173px;
        }
    </style>
</head>

<body >

<h1>班级添加</h1><hr/>


<form action="" method="post">
    班级名称:<input type="text" name=""/><br/>
    选择标签:
    <select name="">
        <option>大班</option>
        <option>美女班</option>
        <option>学渣班</option>
        <option>第一班</option>
    </select><br/><br/>
    <input type="submit" value="提交"/>
    <input type="button" value="返回上级" onclick="history.go(-1);"/>
</form>


</body>
</html>

