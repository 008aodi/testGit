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

<h1>学生信息录入</h1><hr/>


<form action="" method="post">

    学生名称:<input type="text" name=""/><br/>
    学生年龄:<input type="text" name=""/><br/>
    学生生日:<input type="text" name=""/><br/>
    学生班级:
    <select name="">
        <option>120班</option>
        <option>82班</option>
    </select><br/>
    学生分组:
    <select name="">
        <option>1组</option>
        <option>2组</option>
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
    学生备注：
    <textarea name="" style="width:288px;height:172px;">
			<font color="red">
				xxxxxxxxxxxxxxxxxxxxx
			</font>
		</textarea><br/>
    <input type="submit" value="提交"/>
    <input type="button" value="返回上级" onclick="history.go(-1);"/>
</form>


</body>
</html>
