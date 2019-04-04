<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>   
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>

  <head>

  </head>
  
  <body>
  <table id="gurudg"></table>

  	<!-- 添加上师 -->
  	 <div style="display:none;padding:30px " id="addguruDialog">
       		<form id="addguruFom" enctype="multipart/form-data" method="POST">
       			<table>
       				<tr>
       					<td>
			       			上师法名：
       					</td>
       					<td>
       						<input type="text" name="dharnaName" class="easyui-validatebox" data-options="required:true">
       					</td>
       				</tr>
       				<tr>
       					<td>
			       			上师状态：
       					</td>
       					<td>
       						<input type="radio" name="status" value="1" class="easyui-validatebox" data-options="required:true">激活
     &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="status" value="0" class="easyui-validatebox" data-options="required:true">未激活	
       					</td>
       				</tr>
       				<tr>
       					<td>
			       			上师头像：
       					</td>
       					<td>
	       						<input type="file" id="upfile" name="upfile" class="easyui-validatebox" data-options="required:true,accept:'image/jpeg'">
       					</td>
       				</tr>      			      				       				
       				<tr align="center">
       					<td colspan="2">
       						<a href="javascript:void(0)" class="easyui-linkbutton" 
       							data-options="iconCls:'icon-ok',text:'提交'" id="subAddguru"></a>
       					</td>
       				</tr>
       			</table>
       		</form>
       </div>
  	 	 	
  	<script type="text/javascript">	
  		$(function(){

  			$('#gurudg').edatagrid({
  				url:'${pageContext.request.contextPath}/guru/findall.do',
/*   				updateUrl:'${pageContext.request.contextPath}/banner/update.do', */
  				fit:true,
  				striped:true,
  				singleSelect:true,
  				rownumbers:true,
  				pagination:true,
  				fitColumns:true,
  				nowrap:false,
  				pagination:true,
  				pageList:[5,10,15,20], 
  				pageSize:5, 
  				pageNumber:1, 
  				pagePosition:'both',				
  				view: detailview, 
				detailFormatter: function(rowIndex, rowData){ 
				return '<table><tr>' + 
				'<td rowspan=2 style="border:0"><img src="${pageConext.request.contextPath}/cmfz' + rowData.photo + '" style="height:50px;"></td>' + 
				'<td style="border:0">' + 
				'<p>大师状态: ' + rowData.status + '</p>' + 
				'<p>大师法名: ' + rowData.dharnaName + '</p>' + 
				'<p>创建时间: ' +rowData.createTime  + '</p>' + 
				'</td>' + 
				'</tr></table>'; 
				},  								
  				toolbar:[
  						//添加
  						{
		            	  text:'添加',
		            	  iconCls:'icon-add',
		            	  handler:function(){
		            		  //初始化diglog
		            		  $('#addguruDialog').dialog({
		            		  	 title:'添加',
		            		  	 iconCls:'icon-add',
		            		  	 width:380,
		            		  	 height:400,		        		  	 
		            		  });
		            		 //初始化form
		            		  $('#addguruFom').form({
		            		  	url:'${pageContext.request.contextPath}/guru/add.do',
		            		  	success:function(data){
		            		  		// 关闭dialog
							   			if(data=='ok'){
							   		  $.messager.alert('提示','添加成功','info');
							   		$('#addguruDialog').dialog('close');
							   				}
							   			// 刷新数据表格
							   		$('#gurudg').edatagrid('load');
							   					
		            		  	},
		            		  	error:function(){
		            		  	$.messager.alert('提示','添加失败','info');
		            		  	},
		            		  	onSubmit:function(){
		            		  		return $('#addguruFom').form('validate');
		            		  	},
		            		  });		            		  
		            	  }
		              },
		             //修改
		             	{
		            	  text:'修改',
		            	  iconCls:'icon-edit',
		            	  handler:function(){
		            	   var selRow = $('#gurudg').edatagrid('getSelected');
		            	   if(selRow==null){
		            	   		  $.messager.alert('提示','请您选中要修改的行','warning');
		            	   		}else{
		            	   	var index=$('#gurudg').edatagrid('getRowIndex',selRow);
		            	  		/* $('#dg').edatagrid('enableEditing'); */	
		            	   		//指定要编辑的行
		            	   		$('#gurudg').edatagrid('editRow',index);					  				
		            	   		}						  					
				  			}
		              },
		              //删除
/* 		            	{
		               text:'删除',
		               iconCls:'icon-Empty',
		              	handler:function(){
		              	 var selRow = $('#gurudg').edatagrid('getSelected');
					   if(selRow==null){// 说明没有选中行， 提示用户一下
						   $.messager.alert('提示','请您选中要删除的行','warning');
					   }else{
					 	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
    							if (r){    
        						$.ajax({
					   			url:'${pageContext.request.contextPath}/guru/delete.do',
					   			type:'post',
					   			data:'id='+selRow.id,
					   			success:function(data){
					   			if(data=='ok'){
					   			$.messager.alert('提示','删除成功','info');
					   			$('#gurudg').edatagrid('load');
					   			}else{
					   			 $.messager.alert("删除失败");
					   			}					   				
					   			}
					   		});	    
   							}    
							});  
					   }
		              	},
		              }, */
		              //保存
		              {
		               text:'保存',
		               iconCls:'icon-bullet_disk',
		              	handler:function(){
		              	var selRow = $('#gurudg').edatagrid('getSelected');
		              	if(selRow!=null){
		              			 $('#gurudg').edatagrid('saveRow');		              	
  				 		/* var index=$('#dg').edatagrid('getRowIndex',selRow); */
		            		$.ajax({
		            		url:'${pageContext.request.contextPath}/guru/update.do',
					   			type:'post',
					   			data:'id='+selRow.id+'&dharnaName='+selRow.dharnaName+'&status='+selRow.status,			   				            				            				            				            		
		            			
		            		}); 
		              	$('#gurudg').edatagrid('reload'); 
		              	}           
		              	},
		              },
		              ],
		              columns:[[
  				{field:'id',title:'大师编号',width:100},
  				{field:'dharnaName',title:'大师法名',width:100,editor:'text'},
  				{field:'photo',title:'大师头像',width:100},
  				{field:'status',title:'大师状态(0,1)',width:100,editor:'text'},  
  				{field:'createTime',title:'创建时间',width:100,},			
  				]], 				
  			});
  			$('#gurudg').edatagrid('disableEditing');
  			$('#subAddguru').click(function(){
	  			$('#addguruFom').submit();
  			});

  		});
  		
  	</script>
  	
  	
  	
  </body>
</html>
