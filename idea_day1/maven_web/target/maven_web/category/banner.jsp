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
  <table id="dg"></table>
<!--   	<table id="bannerEdatagrid">
  	    <thead>   
        <tr>   
            <th field="id" width="100" editor="{type:'validatebox',options:{required:true}}">轮播图编号</th>   
            <th field="picName" width="100" editor="text">轮播图名字</th>   
            <th field="picPath" width="100" align="right" editor="{type:'numberbox',options:{precision:1}}">图片路径</th>   
           <th field="description" width="100" align="right" editor="numberbox">图片描述</th>   
            <th field="status" width="150" editor="text">图片状态</th>   
        	<th field="createTime" width="150" editor="text">创建时间</th>
        	<th field="url" width="150" editor="text">跳转路径</th>
        </tr>   
    </thead> 	
  	</table> -->
  	<!-- 添加轮播图 -->
  	 <div style="display:none;padding:30px " id="addDialog">
       		<form id="addFom" enctype="multipart/form-data" method="POST">
       			<table>
       				<tr>
       					<td>
			       			轮播图名字：
       					</td>
       					<td>
       						<input type="text" name="picName" class="easyui-validatebox" data-options="required:true">
       					</td>
       				</tr>
       				<tr>
       					<td>
			       			图片状态：
       					</td>
       					<td>
       						<input type="radio" name="status" value="1" class="easyui-validatebox" data-options="required:true">上线
     &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="status" value="0" class="easyui-validatebox" data-options="required:true">下线	
       					</td>
       				</tr>
       				<tr>
       					<td>
			       			跳转地址：
       					</td>
       					<td>
       						<input type="text" name="url" class="easyui-validatebox" data-options="required:true">
       					</td>
       				</tr>
       				<tr>
       				<tr>
       					<td>
			       			图片描述：
       					</td>
       					<td>
       						<textarea name="description">
       							请输入图片描述信息!~
       						</textarea>
       					</td>
       				</tr>
       				<tr>
       					<td>
			       			商品图片：
       					</td>
       					<td>
	       						<input type="file" id="upfile" name="upfile" class="easyui-validatebox" data-options="required:true,accept:'image/jpeg'">
       					</td>
       				</tr>      			      				       				
       				<tr align="center">
       					<td colspan="2">
       						<a href="javascript:void(0)" class="easyui-linkbutton" 
       							data-options="iconCls:'icon-ok',text:'提交'" id="subAdd"></a>
       							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       						<a href="javascript:void(0)" class="easyui-linkbutton" 
       							data-options="iconCls:'icon-undo',text:'重置'" id="resetAdd"></a>
       					</td>
       				</tr>
       			</table>
       		</form>
       </div>
  	
  	
  	
  	
  	<script type="text/javascript">	
  		$(function(){

  			$('#dg').edatagrid({
  				url:'${pageContext.request.contextPath}/banner/findall.do',
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
/*   				onSave:function(){
  				 var selRow = $('#dg').edatagrid('getSelected');
  				 var index=$('#dg').edatagrid('getRowIndex',selRow);
  				 
  				}, */				
  				view: detailview, 
				detailFormatter: function(rowIndex, rowData){ 
				return '<table><tr>' + 
				'<td rowspan=2 style="border:0"><img src="${pageConext.request.contextPath}/cmfz' + rowData.picPath + '" style="height:50px;"></td>' + 
				'<td style="border:0">' + 
				'<p>轮播图名字: ' + rowData.picName + '</p>' + 
				'<p>图片状态: ' + rowData.status + '</p>' + 
				'<p>图片描述: ' + rowData.description + '</p>' + 
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
		            		  $('#addDialog').dialog({
		            		  	 title:'添加',
		            		  	 iconCls:'icon-add',
		            		  	 width:380,
		            		  	 height:400,		        		  	 
		            		  });
		            		 //初始化form
		            		  $('#addFom').form({
		            		  	url:'${pageContext.request.contextPath}/banner/add.do',
		            		  	success:function(data){
		            		  		// 关闭dialog
							   			if(data=='ok'){
							   		  $.messager.alert('提示','添加成功','info');
							   		$('#addDialog').dialog('close');
							   				}
							   			// 刷新数据表格
							   		$('#dg').edatagrid('load');
							   					
		            		  	},
		            		  	error:function(){
		            		  	$.messager.alert('提示','添加失败','info');
		            		  	},
		            		  	onSubmit:function(){
		            		  		return $('#addFom').form('validate');
		            		  	},
		            		  });		            		  
		            	  }
		              },
		             //修改
		             	{
		            	  text:'修改',
		            	  iconCls:'icon-edit',
		            	  handler:function(){
		            	   var selRow = $('#dg').edatagrid('getSelected');
		            	   if(selRow==null){
		            	   		  $.messager.alert('提示','请您选中要修改的行','warning');
		            	   		}else{
		            	   	var index=$('#dg').edatagrid('getRowIndex',selRow);
		            	  		/* $('#dg').edatagrid('enableEditing'); */	
		            	   		//指定要编辑的行
		            	   		$('#dg').edatagrid('editRow',index);					  				
		            	   		}						  					
				  			}
		              },
		              //删除
		            	{
		               text:'删除',
		               iconCls:'icon-Empty',
		              	handler:function(){
		              	 var selRow = $('#dg').edatagrid('getSelected');
					   if(selRow==null){// 说明没有选中行， 提示用户一下
						   $.messager.alert('提示','请您选中要删除的行','warning');
					   }else{
					 	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
    							if (r){    
        						$.ajax({
					   			url:'${pageContext.request.contextPath}/banner/delete.do',
					   			type:'post',
					   			data:'id='+selRow.id,
					   			success:function(data){
					   			if(data=='ok'){
					   			$.messager.alert('提示','删除成功','info');
					   			$('#dg').edatagrid('load');
					   			}else{
					   			 $.messager.alert("删除失败");
					   			}					   				
					   			}
					   		});	    
   							}    
							});  
					   }
		              	},
		              },
		              //保存
		              {
		               text:'保存',
		               iconCls:'icon-bullet_disk',
		              	handler:function(){
		              	var selRow = $('#dg').edatagrid('getSelected');
		              	if(selRow!=null){
		              			 $('#dg').edatagrid('saveRow');		              	
  				 		/* var index=$('#dg').edatagrid('getRowIndex',selRow); */
		            		$.ajax({
		            		url:'${pageContext.request.contextPath}/banner/update.do',
					   			type:'post',
					   			data:'id='+selRow.id+'&picName='+selRow.picName+'&description='+selRow.description+'&status='+selRow.status,			   				            				            				            				            		
		            			
		            		}); 
		              	$('#dg').edatagrid('reload'); 
		              	}           
		              	},
		              },
		              ],
		              columns:[[
  				{field:'id',title:'轮播图编号',width:100},
  				{field:'picName',title:'轮播图名字',width:100,editor:'text'},
  				{field:'picPath',title:'图片路径',width:100},
  				{field:'description',title:'图片描述',width:100,editor:'text'},
  				{field:'status',title:'图片状态',width:100,editor:'text'},  
  				{field:'createTime',title:'创建时间',width:100,
  					/* formatter:function(value,row,index){ 	
  					var time = new Date(value)				
  					return time.toLocaleDateString();
  					} */
  				},
  				{field:'url',title:'跳转路径',width:100}, 				
  				]], 				
  			});
  			$('#dg').edatagrid('disableEditing');
  			$('#subAdd').click(function(){
	  			$('#addFom').submit();
  			});
  			$('#resetAdd').click(function(){
  				alert('reset');
  			});
  		});
  		
  	</script>
  	
  	
  	
  </body>
</html>
