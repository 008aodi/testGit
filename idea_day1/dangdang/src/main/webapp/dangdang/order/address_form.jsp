<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>生成订单 - 当当网</title>
		<link href="${pageContext.request.contextPath}/dangdang/css/login.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/dangdang/css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/dangdang/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/dangdang/js/jquery.validate.min.js"></script>
			<script type="text/javascript">
			function changaddr(id){
			/* window.location.href="${pageContext.request.contextPath}/addr/selelctAddress?addressId="+id; */
			var xhr;
			if(window.ActiveXObject){
			xhr = new ActiveXObject('Microsoft.xmlhttp');
			}else{
			xhr = new XMLHttpRequest();
			}
			xhr.open('POST','${pageContext.request.contextPath}/addr/selelctAddress');
			xhr.setRequestHeader('content-type', 'application/x-www-form-urlencoded');
			xhr.send('addressId='+id);
			xhr.onreadystatechange=function(){
			if(xhr.readyState==4&&xhr.status==200){
			var addrStr = xhr.responseText;
			console.log(addrStr);
			if(addrStr!='no'){
			var id = document.getElementById('addressId');
			var receive_name = document.getElementById('receiveName');
			var addr = document.getElementById('fullAddress');
			var zip_code = document.getElementById('postalCode');
			var tel = document.getElementById('mobile');
			var phone = document.getElementById('phone');
			var user_id = document.getElementById('addressUserId');
				var arr = addrStr.split('/');
				console.log(arr);
				for(var i=0;i<arr.length;i++){
					arr[i]=arr[i].split(':');
				}
				for(var i=0;i<arr.length;i++){
				console.log(arr);
				if(arr[i][0]=='id'){
				id.value=arr[i][1];
				}
				if(arr[i][0]=='receive_name'){
				receive_name.value=arr[i][1];
				}
					if(arr[i][0]=='addr'){
				addr.value=arr[i][1];
				}
					if(arr[i][0]=='zip_code'){
				zip_code.value=arr[i][1];
				}
					if(arr[i][0]=='phone'){
				phone.value=arr[i][1];
				}
					if(arr[i][0]=='tel'){
				tel.value=arr[i][1];
				}
					if(arr[i][0]=='user_id'){
				user_id.value=arr[i][1];
				}
				}
				
			}else{
			alert('您未选择地址，请编辑新地址');
			}
			}
			}
			}	
			</script>
			 <script type="text/javascript">
		$(document).ready(function() {
			// 在键盘按下并释放及提交后验证提交表单
			$("#f").validate({
			rules: {
			"address.receive_name": {
			required: true,
			rangelength:[2,10]
			},
			"address.addr": {
			required: true,
			rangelength:[6,30],
			},
			
			"address.zip_code": {
			required: true,
			digits: true,
			rangelength:[6,6],
			},
			
			"address.phone": {
			digits: true,
			rangelength:[11,11],
			},
			
			"address.tel": {
			required: true,
			digits: true,
			rangelength:[11,11],
			},
			},
			messages: {
			"address.receive_name": {
       		required: "*必填！",
			rangelength:"*长度为2-10位！"
			},
			"address.addr": {
			required: "*必填！",
			rangelength: "*地址长度位最低6位！"
			},
			
			"address.zip_code": {
					required: "*必填！",
					digits: "*必填！",
					rangelength: "*邮编长度位6位数字！"
			},
			"address.phone": {
			digits: "*必填！",
			rangelength: "*电话位数为11位数字！"
			},
			"address.tel": {
			required: "*必填！",
			digits: "*必填！",
			rangelength: "*手机位数为11位数字！"
			},
			},	
				
			focusInvalid:true,
			focusCleanup:true,
			});		
			});
		
		</script>
			<style type="text/css">
		.error{
		color:red;
		}
		}
/* 		.onCorrect{
		background:#E9FFEB url(../images/label3.gif)no-repeat;
		padding-left:25px;
		font-size:15px;
		line-height:22px;
		vertical-align:middle;
		} */
		</style>
			
			
<%-- 			<script type="text/javascript">
			$(function(){
			function checkName(){
			var name = $("#receiveName").val();
			var pattern =/^[\u4e00-\u9fa5]{2,10}$/;
			if(pattern.test(name)){
			 $("#nameInfo").html("格式正确");
			 return true;
			}else{$("#nameInfo").html("格式错误");return false;}
			}
			});	
			
			$(function(){
			function checkAddr(){
			var name = $("#fullAddress").val();
			var pattern =/^[\u4e00-\u9fa5]{3,40}$/;
			if(pattern.test(name)){
			 $("#addrInfo").html("格式正确");
			 return true;
			}else{$("#addrInfo").html("格式错误");return false;}
			}
			});
			
			
				$(function(){
			function checkZip(){
			var name = $("#postalCode").val();
			var pattern =/^[1-9]{6}$/;
			if(pattern.test(name)){
			 $("#codeInfo").html("格式正确");
			 return true;
			}else{$("#codeInfo").html("格式错误");return false;}
			}
			});
			
			
			$(function(){
			function checkPhone(){
			var name = $("#phone").val();
			var pattern =/^[1][0-9]{10}$/;
			if(pattern.test(name)){
			 $("#phoneInfo").html("格式正确");
			 return true;
			}else{$("#phoneInfo").html("格式错误");return false;}
			}
			});
			
			
			$(function(){
			function checkTel(){
			var name = $("#mobile").val();
			var pattern =/^[0-9]{7,10}$/;
			if(pattern.test(name)){
			 $("#telInfo").html("格式正确");
			 return true;
			}else{$("#telInfo").html("格式错误");return false;}
			}
			});
			
			
		$(function(){function fun(){
			if(checkName()&&checkAddr()&&checkZip()&&checkPhone()&&checkTel()){
			return true;
			}else{
			return false;}
			}
			});	
			</script> --%>
	</head>
	<body>
		<%@include file="../common/head1.jsp"%>
		<div class="login_step">
			生成订单骤: 1.确认订单 >
			<span class="red_bold"> 2.填写送货地址</span> > 3.订单成功
		</div>
		<div class="fill_message">
			<p>
				选择地址：
				<select id="address" onchange="changaddr(this.value)">
					<option>
						填写新地址
					</option>
					<s:iterator value="list" var="addr">
						
						<s:if test="#addr.id==addressId">
						<option value="<s:property value='#addr.id'/>" selected="selected">											
							<s:property value="#addr.addr"/>
						</option>
						</s:if><s:else>
						<option value="<s:property value='#addr.id'/>">											
							<s:property value="#addr.addr"/>
						</option>
						</s:else>	
								
					</s:iterator>
				</select>
			</p>

			<form name="ctl00" method="post" action="${pageContext.request.contextPath}/order/addOrder" id="f" >
				<input type="hidden" name="address.id" id="addressId" value="<s:property value="#request.address.id"/>"/>
				<input type="hidden" name="address.user_id" id="addressUserId" value="<s:property value="#session.user.id"/>"/>
				<table class="tab_login">
					<tr>
						<td valign="top" class="w1">
							收件人姓名：
						</td>
						<td> 
							<input type="text" class="text_input" name="address.receive_name" value="<s:property value="#request.address.receive_name"/>"
								id="receiveName" placeholder="收件人长度2-10位汉字或字符"/>
							<div class="text_left" id="nameValidMsg">
								<p>
									请填写有效的收件人姓名
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							收件人详细地址：
						</td>
						<td>
							<input type="text" name="address.addr" value="<s:property value="#request.address.addr"/>" class="text_input"
								id="fullAddress" placeholder="收件地址长度6-30位汉字或字符" />
							<div class="text_left" id="addressValidMsg">
								<p>
									请填写有效的收件人的详细地址
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							邮政编码
						</td>
						<td>
							<input type="text" class="text_input" name="address.zip_code" value="<s:property value="#request.address.zip_code"/>"
								id="postalCode" placeholder="六位数字的邮政编码"/>
							<div class="text_left" id="codeValidMsg">
								<p>
									请填写有效的收件人的邮政编码
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							电话
						</td>
						<td>
							<input type="text" class="text_input" name="address.phone" value="<s:property value="#request.address.phone"/>"
								id="phone" placeholder="收件人电话，请输入11位数字"/>
							<div class="text_left" id="phoneValidMsg">
								<p>
									请填写有效的收件人的电话
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							手机
						</td>
						<td>
							<input type="text" class="text_input" name="address.tel" value="<s:property value="#request.address.tel"/>"
								id="mobile" placeholder="收件人手机，请输入11位数字"/>
							<div class="text_left" id="mobileValidMsg">
								<p>
									请填写有效的收件人的手机
								</p>
							</div>
						</td>
					</tr>
				</table>

				<div class="login_in">

					<a href="${pageContext.request.contextPath}/dangdang/order/order_info.jsp"><input id="btnClientRegister" class="button_1" name="submit"
					type="button" value="取消" /></a>
				<input id="btnClientRegister" class="button_1" name="submit"
					type="submit" value="下一步" />
				</div>
			</form>
		</div>
		<%@include file="../common/foot1.jsp"%>
	</body>
</html>

					