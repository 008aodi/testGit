<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
    <style>
        body{ text-align:center}
        #dd{margin:0 auto;border:1px solid #fff; width:340px;height:600px}
    </style>
</head>

<body >

<h1>用户注册</h1>

<div id="dd">
    <form action="" method="post">
        <div style="margin-top:15px;">
            <div style="float: left; width:100px;">
                用户名:
            </div>
            <div style="float: left; width:120px;">
                <input type="text" name="" />
            </div>
        </div><br/>
        <div style="margin-top:15px;">
            <div style="float: left; width:100px;">
                密&nbsp;&nbsp;码:
            </div>
            <div style="float: left;width:120px;">
                <input type="text" name="" />
            </div>
        </div><br/>
        <div style="margin-top:15px;">
            <div style="float: left; width:100px;">
                用户角色:
            </div>
            <div style="float: left;width:120px;">
                <select name="user.role">
                    <option value="student">学生</option>
                    <option value="admin">管理员</option>
                </select>
            </div>
        </div><br/>
        <div style="margin-top:15px;">
            <div style="float: left; width:100px;">
                手机号:
            </div>
            <div style="float: left;width:120px;">
                <input type="text" name="" />
            </div>
        </div><br/>
        <div style="margin-top:30px;">
            <div style="float: left; width:400px;">
                <input type="submit" value="提交" />&nbsp;&nbsp;
                <input type="reset" value="重置" />&nbsp;&nbsp;
                <input type="button" value="登陆" />&nbsp;&nbsp;
            </div>
        </div><br/>
    </form>
</div>

</body>
</html>
