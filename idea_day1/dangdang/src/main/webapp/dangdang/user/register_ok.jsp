<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户注册 - 当当网</title>
		<link href="${pageContext.request.contextPath}/dangdang/css/login.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/dangdang/css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/dangdang/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
				onload=function(){
		setInterval(go,1000);
		};
		var x=3;
		function go(){
		x--;
		if(x>0){
		$("#tiaozhuan").text(x);
		}else{
		location.href="${pageContext.request.contextPath}/dangdang/main/main.jsp";
		}
		}
		</script>
	
	</head>
	<body>
		<%@include file="../common/head1.jsp"%>
		<div class="login_step">
			注册步骤: 1.填写信息 > 2.验证邮箱 >
			<span class="red_bold">3.注册成功</span>
		</div>


		<div class="login_success">
			<div class="login_bj">
				<div class="succ">
					<img src="${pageContext.request.contextPath}/images/login_success.jpg" />
				</div>
				<h5>
					<font style='color: #cc3300'><strong><s:property value="#session.user.nickname"/></strong> </font>，欢迎加入当当网
				</h5>
				<h6>
					请牢记您的登录邮件地址：<font style='color: #cc3300'><strong><s:property value="#session.user.email"/></strong> </font>
				</h6>
				<h3>页面将在	<font color="red"><span id="tiaozhuan">3</span></font> 秒后跳转到首页 </h3>
				<ul>
					<li class="nobj">
						您现在可以：
					</li>
					<li>
						进入“
						<a href="#">我的当当</a>”查看并管理您的个人信息
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/dangdang/main/main.jsp">浏览并选购商品</a>
					</li>
				</ul>
			</div>
		</div>

		<%@include file="../common/foot1.jsp"%>
	</body>
</html>

