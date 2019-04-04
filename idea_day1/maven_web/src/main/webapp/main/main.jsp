<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8"> -->
<title>持名法州主页</title>
<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
<script type="text/javascript">
	<!--菜单处理-->
	$(function(){
	$.ajax({
		type:'POST',
		url:'${pageContext.request.contextPath}/menu/findall.do',
		dataType:'JSON',
		success:function(data){
		$.each(data,function(index,first){
			var title = first.title;
			var menu = "";
			$.each(first.list,function(index,second){
			menu+="<p style='text-align:center'><a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-"+second.iconCls+"'\" onclick=\"addTab('"+second.title+"','"+second.href+"')\">"
			+second.title+"</a></p>";
			});			
			$("#aa").accordion('add',{
			title:title,
			selected:false,	
			content:menu	
			});				
		});
		}
		});
		$("#updatePwd").linkbutton({
				onClick:function(){		
						 $('#updatePwdFom').form("clear")
					$("#updatePwdDG").dialog({
						title:'修改密码',
						iconCls:'icon-edit',
								});
					
							//初始化form
		            		  $('#updatePwdFom').form({
		            		  	url:'${pageContext.request.contextPath}/admin/updatePwd.do',
		            		  	success:function(data){
		            		  		// 关闭dialog
							   			if(data=='ok'){
							   		  $.messager.alert('提示','修改成功','info');
							   		/* $('#updatePwdFom').form("clear"); */
							   		$('#updatePwdDG').dialog('close');
							   				}	
							   					
		            		  	},
		            		  	error:function(){
		            		  	$.messager.alert('提示','修改失败','info');
		            		  	},
		            		  	onSubmit:function(){
		            		  		return $('#updatePwdFom').form('validate');
		            		  	},
		            		  });
					$("#updatePwdDG").dialog("open");					
				}
	
				});
				
			$('#subupdatePwd').click(function(){
	  		$('#updatePwdFom').submit();		
  			});			
		            $("#oldPwd").blur(function(){
					$.ajax({
					url:"${pageContext.request.contextPath }/admin/checkPwd.do",
					type:"post",
					data:"password="+$('#oldPwd').val(),
					dataType:"JSON", 
					success:function(data){
					/* 	var chenkpassword = eval('(' + data + ')'); 将json转换成javascript对象  */
						 if(data.mess!=null){
			        	 $.messager.alert('提示','请输入的密码错误','warning');			        	 
			        }
					}
				});
			});  			
				// 密码的验证
				$('#password').validatebox({
					missingMessage:'请输入内容', 
					invalidMessage:'对不起，您的输入不合法', 
					required:true,
					validType:'length[5,10]',
					
				});
				$('#uppassword').validatebox({
					missingMessage:'请输入内容', 
					required:true,
					validType:'equals["#password"]',
				});
			// 重写验证规则
			$.extend($.fn.validatebox.defaults.rules, {    
			    equals: {   
			    	// 验证规则的实现 
			        validator: function(value,param){ 
			        	// value为用户输入的值     | param[0] 是验证规则中指定的标签对象
			            return value == $(param[0]).val();    
			        },    
			        // 错误提示信息
			        message: '密码不一致!~'   
			    }    
			}); 						
			});
			
			

		function addTab(title,href){	
			var checkTab =$('#tt').tabs('exists',title);
		if(checkTab){
		console.log(title);
		$('#tt').tabs('select',title);
				
		}else{
			$('#tt').tabs('add',{
			 			title:title,    
					    closable:true, 
					    selected:true,
						href:"${pageContext.request.contextPath}"+href,
				});			
				}
			}
	
</script>

</head>
<body class="easyui-layout">   
    <div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >持名法州后台管理系统</div>
    	<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:${sessionScope.admin.name} &nbsp;<a id="updatePwd" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/exit.do" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
    </div>   
    <div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;百知教育 htf@zparkhr.com.cn</div>
    </div>   
       
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    	<div id="aa" class="easyui-accordion" data-options="fit:true">   
		</div>  
    </div>   
    <div data-options="region:'center'">
    	<div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">   
		    <div title="主页" data-options="iconCls:'icon-neighbourhood',"  style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
		</div>  
    </div> 
      	 <div style="display:none;padding:30px " id="updatePwdDG">
       		<h3 align="center">${sessionScope.admin.name}</h3>
       		<form id="updatePwdFom"  method="POST">
       			<table>
<%--        				<tr>
       					<td>
			       			用户名：
       					</td>
       					<td>
       						<input type="text" name="name" value="${sessionScope.admin.name}" readonly>
       					</td>
       				</tr>  --%>      				
       				<tr>
       					<td>
			       			原始密码：
       					</td>
       					<td >
       						<input type="text" id="oldPwd" name="oldPwd" class="easyui-validatebox" data-options="required:true">
       					</td>
       				</tr> 
       				  		<tr>
       					<td>
			       			修改密码：
       					</td>
       					<td >
       						<input type="text" id="password" name="password" class="easyui-validatebox" data-options="required:true">
       					</td>
       				</tr> 
       				   <tr>
       					<td>
			       			确认密码：
       					</td>
       					<td >
       						<input type="text" name="uppassword"  id="uppassword" class="easyui-validatebox" data-options="required:true">
       					</td>
       				</tr>     			      				       				
       				<tr align="center">
       					<td colspan="2">
       						<a  class="easyui-linkbutton" 
       							data-options="iconCls:'icon-ok',text:'提交'" id="subupdatePwd"></a>
       					</td>
       				</tr>
       			</table>
       		</form>
       </div>
      
</body> 
</html>