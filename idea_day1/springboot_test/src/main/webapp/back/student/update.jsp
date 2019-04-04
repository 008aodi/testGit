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

<h1>学生信息修改</h1><hr/>


<form action="" method="post">

    学生名称:<input type="text" name=""/><br/>
    学生年龄:<input type="text" name=""/><br/>
    学生生日:<input type="text" name=""/><br/>
    学生班级与小组:
    <select name="">
        <option>80班-1组</option>
        <option>81班-2组</option>
        <option>82班-3组</option>
    </select><br/>
    学生就业城市:
    <select name="">
        <option>北京</option>
        <option>上海</option>
        <option>广州</option>
    </select><br/>
    学生标签:
    <input type="checkbox" name="">帅哥
    <input type="checkbox" name="">学渣
    <input type="checkbox" name="">学霸<br/><br/>
    备注：
    <textarea name="" style="width:288px;height:172px;">
			<font color='red'>xxxxxxxxxxxxxxxxxx</font>
		</textarea><br/>
    <input type="submit" value="提交"/>
</form>


</body>
</html>

