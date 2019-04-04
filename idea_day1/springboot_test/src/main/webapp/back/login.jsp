<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/icon.css">
    <script type="application/javascript" src="../js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <style type="text/css">
/*        .right
        color: green
        }*/

        .wrong {
            color: red
        }
    </style>
    <script type="text/javascript">

    $(function(){
        var flag = false;
        $("#codeImg").click(function(){
            $("#codeImg").attr('src',$('#codeImg').attr("src")+'?'+new Date());
        });
        $("#eCode").blur(function(){
            $("#codeImg").parent().find("span").remove();
            $.ajax({
                url:"${pageContext.request.contextPath }/user/code",
                type:"post",
                data:"eCode="+$('#eCode').val(),
                /* 	dataType:"JSON", */
                success:function(data){
                    if(data == false){
                        $("#codeImg").parent().append("<br/><span id='qew' class='wrong'>验证码有误</span>");
                    }else{
                        flag = true;
                    }
                }
            });
        });

        $("#username").validatebox({
            required: true,
            validType: 'length[3,10]',
            missingMessage:'请输入用户名',
            invalidMessage:'请输入正确的格式'
        });

        $("#password").validatebox({
            required: true,
            validType: 'length[3,8]',
            missingMessage:'请输入密码',
            invalidMessage:'请输入正确的格式'
        });
        $("#login").click(function(){
            $("#loginForm").form('submit', {
                url:"${pageContext.request.contextPath }/user/login",
                onSubmit: function(){
                    var validate = $("#loginForm").form("validate");
                    return   validate&&flag;
                },
                success:function(data){
                    var a = eval('(' + data + ')');/* 将json转换成javascript对象 */
                    if(a.mess!=null){
                        alert(a.mess);
                    }else{
                        location.href="${pageContext.request.contextPath }/back/main/main.jsp";
                    }
                }
            });
        });



    });
    </script>
</head>

<body style="text-align: center;">

<h1>用户登陆</h1>

<form action="./main/main.jsp" method="post" id="loginForm">

    用户名:<input type="text" name="username" id="username"><br/><br/>

    密&nbsp;&nbsp;码:<input type="text" name="password" id="password"><br/><br/>

    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 验证码:<input type="text" name="eCode" id="eCode"><img id="codeImg" src="${pageContext.request.contextPath}/capt/getcapt"/><br/>
    <input type="button" value="提交" id="login"/> &nbsp;&nbsp; <input type="button" value="注册" id="regist"/><br/>



</form>



</body>
</html>
