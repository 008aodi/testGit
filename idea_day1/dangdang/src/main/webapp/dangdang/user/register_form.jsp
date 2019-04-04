<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户注册 - 当当网</title>
		<link href="${pageContext.request.contextPath}/dangdang/css/login.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/dangdang/css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/dangdang/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/dangdang/js/jquery.validate.min.js"></script>		
		<script type="text/javascript">
			function changePrcture(){
			/* $(document.getElementById("#imgVcode").src="${pageContext.request.contextPath}/capcher/capcher?t="+Math.random()); */
			$("#imgVcode").attr("src",$("#imgVcode").attr("src")+Math.random());
			}				
        </script>   
        
         <script type="text/javascript">
			function checkEmail(){
			var email = $("#txtEmail").val();
			var pattern =/(^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,10}\.[c][o][m]$)/;
			if(pattern.test(email)){
			 $("#emailInfo").html("格式正确");
			 return true;
			}else{
			$("#emailInfo").html("邮箱格式错误");
			return false;
			}
			}
			
			function checkNikeName(NickName){
			/* var NickName = $("#txtNickName").val(); */
			var pattern =/^[a-zA-Z0-9]{4,20}$/;
			if(pattern.test(NickName)){
			$("#nameInfo").html("格式正确");
			return true;
			}else{
			$("#nameInfo").html("昵称格式错误");
			return false;}
			}
			
			function checkPass(password){
			/* var password = $("#txtPassword").val(); */
			var pattern =/^[a-zA-Z0-9]{6,20}$/;
			if(pattern.test(password)){
			$("#passwordInfo").html("格式正确");
			return true;
			}else{
			$("#passwordInfo").html("密码格式错误");
			return false;}
			}
			
			function checkAgain(pass2){
			/* var pass2 = $("#txtRepeatPass").val(); */
			var password = $("#txtPassword").val();
			if(pass2==password){
			$("#password1Info").html("密码一致");
			return true;
			}else{
			$("#password1Info").html("密码不一致!!!");
			return false;}
			}
			
			function fun(){
				if(checkEmail()&&checkNickName()&&checkPass()&&checkAgain()){
					return true;
				}else{
					return false;
				}
			} 			 
		</script>    
        <script type="text/javascript">
		$(document).ready(function() {
			// 在键盘按下并释放及提交后验证提交表单
			$("#f").validate({
			rules: {
			"user.nickname": {
			required: true,
			rangelength:[2,10]
			},
			"user.password": {
			required: true,
			rangelength:[6,16],
			},
			"password1": {
			required: true,
			equalTo: "#txtPassword"
			},
			/* "user.email": {
			required: true,
			email: true,
			}, */
			},
			messages: {
			"user.nickname": {
       		required: "*必填！",
			rangelength:"*长度为2-10位！"
			},
			"user.password": {
			required: "*必填！",
			rangelength: "*密码长度位6-16位！"
			},
			"password1": {
			required: "*必填！",
			equalTo: "*两次密码输入不一致!"
			},
			"user.email": {
					required: "*必填！",
					errorClass:"error",
					validClass:"success",
					errorElement:"label",
					email:"*请输入一个正确的邮箱！"
			},
			},		
			focusInvalid:true,
			focusCleanup:true,
			success:function(){
			}
			});		
			});	
		</script>  							
		<style type="text/css">
		.error{
		color:red;
		}
		</style> 
		<script type="text/javascript">
       function removeNode(){
       document.getElementById('checkEmail').remove();
       document.getElementById('emailMessage').remove();
       }
		function checkemail(emailName){	
		if(checkEmail()){
			var xhr;
			var email = document.getElementById('emailValidMsg');
			if(window.ActiveXObject){
				xhr = new ActiveXObject('Microsoft.xmlhttp');
			}else{
				xhr = new XMLHttpRequest();			
			}
			xhr.open('GET','${pageContext.request.contextPath}/user/checkEmail?emailName='+emailName);
			xhr.send();
			xhr.onreadystatechange=function(){
			if(xhr.readyState==4&&xhr.status==200){
			var txt = xhr.responseText;	
			if('用户名可用'==txt){
			var span = document.createElement('span');
			var img = document.createElement('img');
			img.src='${pageContext.request.contextPath}/dangdang/images/right.gif';
			span.id='checkEmail';
			span.style='color:green';
			span.innerHTML=txt;
			span.appendChild(img);
			email.appendChild(span);
			}else{
			var span = document.createElement('span');
			var img = document.createElement('img');
			img.src='${pageContext.request.contextPath}/dangdang/images/wrong.gif';
			span.id='checkEmail';
			span.style='color:red';
			span.innerHTML=txt;		
			span.appendChild(img);
			email.appendChild(span);
			}	
			}
			}
		}
		

			}	
</script>
		
		
		<%-- <script type="text/javascript">
			$(function() {
			$("#f").validate({
				debug:true,
				rules:{
				}


		</script> --%>
		
		
		
	</head>
	<body>
		<%@include file="../common/head1.jsp"%>
		<div class="login_step">
			注册步骤:
			<span class="red_bold">1.填写信息</span> > 2.验证邮箱 > 3.注册成功
		</div>		
		
		<div class="fill_message">
			<form name="ctl00" method="post" action="${pageContext.request.contextPath}/user/regist" id="f">
				<h2>
					以下均为必填项<div style="height: 30px; padding: 5px; color: red" id="divErrorMssage">
					<s:if test="message!=null"><s:property value="message"/></s:if>
					</div>
				</h2>
				<table class="tab_login" >
					<tr>
						<td valign="top" class="w1">
							请填写您的Email地址：
						</td>
						<td>
							<input name="user.email" type="text" id="txtEmail" placeholder="请输入邮箱如：**@**.com" class="text_input" required="true" validType="email" 
							onblur="checkemail(this.value);" onfocus="removeNode();"/>
							<div class="text_left" id="emailValidMsg">
								<p id="emailMessage">
									 
									请填写有效的Email地址，在下一步中您将用此邮箱接收验证邮件。
								</p>
									<span id="emailInfo" style="color:red"><label for="user.email"></label></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							设置您在当当网的昵称：
						</td>
						<td>
							<input name="user.nickname" type="text" id="txtNickName" class="text_input" placeholder="当当网的昵称长度为2-10位" onblur="checkNikeName(this.value)"/>
							<div class="text_left" id="nickNameValidMsg" >
								<p>
									<label for="user.nickname"></label>
									您的昵称可以由小写英文字母、中文、数字组成，
								</p>
								<p>
									
								</p>
								<span id="nameinfo" style="color:red"><!-- 长度4－20个字符，一个汉字为两个字符。 --></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							设置密码：
						</td>
						<td>
							<input name="user.password" type="password" id="txtPassword"
								class="text_input" placeholder="设置您的密码长度为6-16位" onblur="checkPass(this.value)"/>
							<div class="text_left" id="passwordValidMsg">
								<p>
									<label for="user.password" ></label>
									您的密码可以由大小写英文字母、数字组成，长度6－16位。
								</p>
								<span id="passwordinfo" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							再次输入您设置的密码：
						</td>
						<td>
							<input name="password1" type="password" id="txtRepeatPass"
								class="text_input" placeholder="请确认您的密码" onblur="checkAgain(this.value)"/>
							<div class="text_left" id="repeatPassValidMsg" >
							<label for="password1"></label>
							两次输入的密码必须一致
							<span id="password1info" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							验证码：
						</td>
						<td>
							<img class="yzm_img" id='imgVcode' src="${pageContext.request.contextPath}/capcher/capcher?s=s" onclick="changePrcture()"/>
							<input name="code" type="text" id="txtVerifyCode"
								class="yzm_input" placeholder="验证码"/>
							<div class="text_left t1">
								<p class="t1">
									<span id="vcodeValidMsg">请输入图片中的四个字母。</span>
									
									<span id="number.info" style="color:red"></span>
									<a href="javascript:void(0)" id="refresh" onclick="changePrcture()">看不清楚？换个图片</a>
								</p>
							</div>
						</td>
					</tr>
				</table>

				<div class="login_in">
																										
					<input id="btnClientRegister" class="button_1" name="submit"  type="submit" value="注 册" onclick="fun()"/>
				</div>
			</form>
		</div>
		<%@include file="../common/foot1.jsp"%>
	</body>
</html>

