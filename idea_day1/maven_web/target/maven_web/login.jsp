<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>持名法州后台管理中心</title>
			
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css" type="text/css"></link>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/login.css" type="text/css"></link>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
	 <style type="text/css">
        .right 
            color: green
        }

        .wrong {
            color: red
        }
    </style>
	
	<script type="text/javascript">
	
		$(function(){
		var flag = false;
			//点击更换验证码：
			$("#captchaImage").click(function(){//点击更换验证码
				$("#captchaImage").attr("src",$("#captchaImage").attr("src")+'?'+new Date());
			});
						
			$("#enCode").blur(function(){
				$("#captchaImage").parent().find("span").remove();
				$.ajax({
					url:"${pageContext.request.contextPath }/admin/code.do",
					type:"post",
					data:"enCode="+$('#enCode').val(),
				/* 	dataType:"JSON", */
					success:function(data){
						if(data == false){
							$("#captchaImage").parent().append("<br/><span id='qew' class='wrong'>验证码有误</span>");
						}else{
							flag = true;
						}
					}
				});
			});
			
			$("#name").validatebox({
				required: true,    
			    validType: 'length[3,8]',
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
			    url:"${pageContext.request.contextPath }/admin/login.do",
			    onSubmit: function(){    
			       var validate = $("#loginForm").form("validate");
			       return   validate&&flag;
			    },    
			    success:function(data){    
			        var a = eval('(' + data + ')');/* 将json转换成javascript对象 */
			        if(a.mess!=null){
			        	alert(a.mess);
			        }else{
			        	location.href="${pageContext.request.contextPath }/main/main.jsp";
			        }
			    }    
			});
			});
			
		});
	</script>
</head>
<body>
	
		<div class="login">
			<form id="loginForm" action="${pageContext.request.contextPath}/admin/login.do" method="post" >
				
				<table>
					<tbody>
						<tr>
							<td width="190" rowspan="2" align="center" valign="bottom">
								<img src="img/header_logo.gif" />
							</td>
							<th>
								用户名:
							</th>
							<td>
								<input id="name" type="text"  name="name" class="text" value="" maxlength="20" placeholder="请输入用户名"/>
							</td>
					  </tr>
					  <tr>
							<th>
								密&nbsp;&nbsp;&nbsp;码:
							</th>
							<td>
								<input id="password" type="password" name="password" class="text" value="" maxlength="20" autocomplete="off" placeholder="请输入密码"/>
							</td>
					  </tr>
					
						<tr>
							<td>&nbsp;</td>
							<th>验证码:</th>
							<td>
								<input type="text" id="enCode" name="enCode" class="text captcha" maxlength="4" autocomplete="off" placeholder="请输入验证码"/>
								<img id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath}/capt/getcapt.do" title="点击更换验证码"/>
							</td>
						</tr>					
					<tr >
						<td>
							&nbsp;
						</td>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<th>&nbsp;</th>
						<td>
							<input type="button" class="homeButton" value="" onclick="location.href='/'">
							<input type="button" class="loginButton" value="登录" id="login">
						</td>
					</tr>
				</tbody></table>
				<div class="powered">COPYRIGHT © 2008-2017.</div>
				<div class="link">
					<a href="http://www.chimingfowang.com/">持名佛网首页</a> 
					<a href="http://www.chimingbbs.com/">交流论坛</a> 
					<a href="">关于我们</a> 
					<a href="">联系我们</a> 
					<a href="">授权查询</a>
				</div>
			</form>
		</div>
</body>
</html>